# Liskov Substitution Principle (LSP)

## 1. Definition
> **"Objects of a superclass should be replaceable with objects of its subclasses without breaking the correctness or behavior of the application."**

If `Class B` is a subtype of `Class A`, any client expecting an instance of `A` must be able to accept an instance of `B` without needing `instanceof` checks, without encountering unexpected exceptions (`UnsupportedOperationException` / `AssertionError`), and without unexpected behavioral degradation.

---

## 2. Why It Exists (The Fragility of Broken Hierarchies)
When inheritance is used solely for code reuse rather than genuine behavioral compatibility:
- Subclasses override methods and throw exceptions because they don't support the parent's feature (e.g. `Bicycle` throwing error on `turnOnEngine()`).
- Polymorphic client loops break violently at runtime with unexpected errors or `NullPointerException`.
- Developers are forced to clutter business code with defensive `if (vehicle instanceof Bicycle)` checks, violating OCP.

---

## 3. Real-World Analogy
Think of an **Electrical Power Socket (Parent)** and appliances (Subtypes):
- Any standard 3-pin appliance (Laptop charger, Fridge, Lamp) can plug into the wall socket and operate safely.
- If an appliance requires a specialized high-voltage diesel generator and explodes when plugged into a normal wall socket, it **violates the wall socket contract**.

---

## 4. Code Examples

### Case Study 1: The Bike & Engine Hierarchy

#### ❌ Violating LSP
```java
public interface Bike {
    void turnOnEngine();
    void accelerate();
    void applyBrakes();
}

public class MotorCycle implements Bike {
    public void turnOnEngine() { System.out.println("Engine ON"); }
    public void accelerate()   { System.out.println("Speed + 10"); }
    public void applyBrakes()  { System.out.println("Speed - 5"); }
}

public class Bicycle implements Bike {
    // ❌ VIOLATION: Bicycle has no engine, so it throws an exception!
    public void turnOnEngine() {
        throw new AssertionError("Bicycle has no engine!");
    }
    public void accelerate()   { System.out.println("Pedaling faster"); }
    public void applyBrakes()  { System.out.println("Hand brakes applied"); }
}
```
**Why this breaks:** A client calling `bike.turnOnEngine()` crashes when handed a `Bicycle`.

#### ✅ Following LSP
Segregate common vehicle capabilities from engine-specific capabilities:
```java
public abstract class Bike {
    public abstract void accelerate();
    public abstract void applyBrakes();
}

public interface Engine {
    void turnOnEngine();
    void turnOffEngine();
}

public class MotorCycle extends Bike implements Engine {
    public void turnOnEngine() { System.out.println("Engine ON"); }
    public void turnOffEngine() { System.out.println("Engine OFF"); }
    public void accelerate()   { System.out.println("Motorcycle accelerating"); }
    public void applyBrakes()  { System.out.println("Motorcycle braking"); }
}

public class Bicycle extends Bike {
    // No Engine interface -> clean contract!
    public void accelerate()  { System.out.println("Bicycle pedaling"); }
    public void applyBrakes() { System.out.println("Bicycle braking"); }
}
```

---

### Case Study 2: Vehicle `hasEngine()` Return Contract

#### ❌ Violating LSP (Returning `null` or breaking pre/post-conditions)
```java
public class Vehicle {
    public Boolean hasEngine() { return true; }
}

public class Bicycle extends Vehicle {
    @Override
    public Boolean hasEngine() { return null; } // ❌ Throws NPE when client calls .toString()!
}
```

#### ✅ Following LSP
Create an intermediate abstraction `EngineVehicle`:
```java
public class Vehicle {
    public Integer getNumberOfWheels() { return 2; }
}

public class EngineVehicle extends Vehicle {
    public Boolean hasEngine() { return true; }
}

public class Bicycle extends Vehicle { }
public class MotorCycle extends EngineVehicle { }
public class Car extends EngineVehicle {
    @Override public Integer getNumberOfWheels() { return 4; }
}
```

---

## 5. Formal Rules of LSP (Subtyping Contracts)
1. **No Throwing `UnsupportedOperationException`:** If a subtype cannot perform a parent method, the hierarchy is wrong.
2. **Preconditions Cannot Be Strengthened:** Subtypes cannot demand stricter input conditions than the parent.
3. **Postconditions Cannot Be Weakened:** Subtypes must guarantee at least what the parent promised.
4. **Invariants Must Be Preserved:** Superclass invariants (e.g. `Square extends Rectangle` violating width/height independence) must hold true in subclasses.

---

## 6. Common Interview Questions
> **Q1: Why is "Square extends Rectangle" the classic LSP violation?**
> **A:** A Rectangle allows setting `width` and `height` independently. A Square forces `width == height`. If client code changes a Rectangle's width and expects height to remain constant, passing a Square breaks that expectation, violating LSP.

> **Q2: How do you fix an LSP violation?**
> **A:**
> 1. Restructure the hierarchy with finer-grained base abstractions (e.g. `EngineVehicle` vs `Vehicle`).
> 2. Use **Composition over Inheritance** instead of forcing a parent-child relationship.
