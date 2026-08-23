# Interface Segregation Principle (ISP)

## 1. Definition
> **"Clients should not be forced to depend upon interfaces that they do not use."**

Instead of creating one large, "fat" interface containing dozens of methods for different consumers, it is far better to create **multiple smaller, role-specific, focused interfaces**. Interfaces should be lean so that implementing classes only implement methods relevant to their actual role.

---

## 2. Why It Exists (The Problem of Fat Interfaces)
When an interface attempts to cover every conceivable action across an entire domain:
- Implementing classes are forced to implement irrelevant methods with dummy bodies or `throw new UnsupportedOperationException()`.
- Changes to one method in the fat interface force recompilation and re-deployment of all implementing classes, even those that never use that method.
- Code becomes polluted with dead, misleading boilerplate.

---

## 3. Real-World Analogy
Think of a **Multi-Function Tool vs a Specialized Toolset**:
- A giant Swiss Army Knife with 50 tools is bulky, heavy, and dangerous if you only need a standard pen.
- In a professional kitchen, a Waiter doesn't carry a chef's cleaver or dishwasher chemicals; they carry an order pad. The Chef uses knives; the Janitor uses cleaning supplies.

---

## 4. Code Examples

### ❌ Violating ISP (The "Fat" Interface)
```java
public interface RestaurantEmployee {
    void prepareFood();
    void decideMenu();
    void serveFoodAndDrinks();
    void takeOrder();
    void cleanTheKitchen();
}

public class Waiter implements RestaurantEmployee {
    public void takeOrder()           { System.out.println("Taking order..."); }
    public void serveFoodAndDrinks()  { System.out.println("Serving food..."); }

    // ❌ VIOLATION: Waiter is forced to implement irrelevant chef and janitor methods!
    public void prepareFood() {
        throw new AssertionError("Waiter cannot prepare food!");
    }
    public void decideMenu() {
        throw new AssertionError("Waiter cannot decide menu!");
    }
    public void cleanTheKitchen() {
        throw new AssertionError("Waiter cannot clean kitchen!");
    }
}
```

---

### ✅ Following ISP (Segregated Role Interfaces)

```java
// Role 1: Kitchen Operations
public interface ChefTasks {
    void prepareFood();
    void decideMenu();
}

// Role 2: Dining Room Operations
public interface WaiterTasks {
    void serveFoodAndDrinks();
    void takeOrder();
}

// Role 3: Maintenance Operations
public interface MaintenanceTasks {
    void cleanTheKitchen();
    void restockGroceries();
}

// Implementation: Waiter implements ONLY WaiterTasks
public class Waiter implements WaiterTasks {
    @Override
    public void takeOrder() {
        System.out.println("Waiter taking order...");
    }
    @Override
    public void serveFoodAndDrinks() {
        System.out.println("Waiter serving food and drinks...");
    }
}

// Implementation: Chef implements ONLY ChefTasks
public class Chef implements ChefTasks {
    @Override
    public void prepareFood() {
        System.out.println("Chef cooking gourmet meal...");
    }
    @Override
    public void decideMenu() {
        System.out.println("Chef planning weekly menu specials...");
    }
}
```

---

## 5. Benefits of ISP
1. **Zero Dummy/Exception Methods:** Implementing classes contain only meaningful, operational code.
2. **High Role Cohesion:** Interfaces clearly represent single capabilities or roles.
3. **Flexible Composition:** Classes can implement multiple small interfaces as needed (`class HeadChef implements ChefTasks, MaintenanceTasks`).
4. **Isolated Impact:** Modifying `MaintenanceTasks` does not trigger re-testing or re-compilation of `Waiter`.

---

## 6. Signs of ISP Violations in Code Reviews
- Methods with empty bodies `{}` or `throw new UnsupportedOperationException()`.
- Classes that implement an interface where 50%+ of the methods are irrelevant.
- Frequent comments like `// Not supported in this class`.

---

## 7. Common Interview Questions
> **Q1: How do Single Responsibility Principle (SRP) and Interface Segregation Principle (ISP) relate?**
> **A:** SRP is about **cohesion within classes** (one reason to change), whereas ISP is about **cohesion within interfaces** (clients should not see methods they don't call). ISP prevents fat interfaces that force classes to violate SRP.

> **Q2: Does ISP mean every interface should have only 1 method?**
> **A:** No (single-method interfaces are SAM / Functional Interfaces). ISP means an interface should contain only methods that **belong together from the client's perspective**.
