# Dependency Inversion Principle (DIP)

## 1. Definition
> **"1. High-level modules should not depend on low-level modules. Both should depend on ABSTRACTIONS."**
> **"2. Abstractions should not depend on details. Details should depend on ABSTRACTIONS."**

In simple terms: **Classes should depend on Interfaces/Abstract Classes rather than concrete classes.**

---

## 2. Why It Exists (The Trap of Direct Concrete Coupling)
When a high-level business entity (e.g. `MacBook` or `PaymentService`) directly instantiates or references a specific concrete utility (e.g. `new WiredKeyboard()`):
- You cannot swap components (e.g. use a `BluetoothKeyboard`) without editing the `MacBook` class.
- Unit testing becomes nearly impossible because you cannot inject mocks or test doubles.
- The high-level module becomes brittle and tied directly to the low-level vendor implementation.

---

## 3. Real-World Analogy
Think of an **Electrical Wall Plug and Appliance**:
- Your home electrical socket does not require a wire to be directly soldered into your specific brand of lamp.
- Both the Wall Socket (High-level power provider) and Lamp (Low-level power consumer) depend on the **Universal Plug Specification Interface**.
- You can plug in a hair dryer, a charger, or a TV seamlessly.

---

## 4. Code Examples

### ❌ Violating DIP (Direct Concrete Coupling)
```java
public class MacBook {
    private final WiredKeyboard keyboard; // Direct dependency on concrete class
    private final WiredMouse mouse;       // Direct dependency on concrete class

    public MacBook(WiredKeyboard keyboard, WiredMouse mouse) {
        this.keyboard = keyboard; // Tightly coupled!
        this.mouse = mouse;
    }
}
```
**Why this breaks:** You cannot assemble a `MacBook` with a `BluetoothKeyboard` or `BluetoothMouse` without rewriting `MacBook.java`.

---

### ✅ Following DIP (Dependency Injection via Abstractions)

```java
// Abstraction 1: Keyboard interface
public interface Keyboard {
    void getSpecifications();
}

// Abstraction 2: Mouse interface
public interface Mouse {
    void getSpecifications();
}

// Low-level implementations
public class WiredKeyboard implements Keyboard { ... }
public class BluetoothKeyboard implements Keyboard { ... }
public class WiredMouse implements Mouse { ... }
public class BluetoothMouse implements Mouse { ... }

// High-level module depends ONLY on abstractions
public class MacBook {
    private final Keyboard keyboard;
    private final Mouse mouse;

    // Inversion of Control via Constructor Injection
    public MacBook(Keyboard keyboard, Mouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }

    public void printSpecs() {
        keyboard.getSpecifications();
        mouse.getSpecifications();
    }
}
```

Now you can create any combination effortlessly:
```java
MacBook wiredMac = new MacBook(new WiredKeyboard(), new WiredMouse());
MacBook wirelessMac = new MacBook(new BluetoothKeyboard(), new BluetoothMouse());
MacBook hybridMac = new MacBook(new BluetoothKeyboard(), new WiredMouse());
```

---

## 5. Benefits of DIP
1. **Extreme Flexibility:** Interchangeable implementations without touching business logic.
2. **Effortless Unit Testing:** Inject mock interfaces (`MockKeyboard`, `MockPaymentGateway`) into unit tests.
3. **Decoupled Teams:** High-level architectural teams and low-level hardware/driver teams work in parallel against agreed interfaces.
4. **Foundation of Modern Frameworks:** Powers Spring's Inversion of Control (IoC) and Dependency Injection (DI) containers.

---

## 6. DIP vs Dependency Injection (DI) vs Inversion of Control (IoC)
- **DIP:** The **high-level design principle** (depend on abstractions).
- **IoC:** The **architectural pattern** of inverting control flow (framework calls you, instead of you calling framework).
- **DI:** The **concrete technique** used to supply dependencies (via constructor, setter, or field).

---

## 7. Common Interview Questions
> **Q1: How do you identify a DIP violation?**
> **A:** Look for `new ConcreteClass()` inside the constructor or business methods of a high-level service class.

> **Q2: Does DIP mean we should never use `new` anywhere?**
> **A:** No. `new` is appropriate for value objects, data models, or inside dedicated creational factories / configuration classes. Business logic services should receive dependencies via injection.
