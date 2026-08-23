# Topic 2.6 — Singleton Pattern & Noninstantiable Utility Classes

## 1. The Singleton Pattern
Ensures that **exactly ONE instance** of a class exists across the entire JVM lifecycle, providing a single global access point (e.g. Connection pool, Configuration manager).

### The Classic Singleton Anatomy
1. `private` constructor: Blocks external `new` calls.
2. `private static final` instance variable: Stores the unique instance.
3. `public static` accessor (`getInstance()`): Returns the instance.

---

## 2. The 3 Implementations (Effective Java Item 3)

### Implementation 1: Public Final Field (Eager)
```java
public class Elvis {
    public static final Elvis INSTANCE = new Elvis();
    private Elvis() { }
}
```

### Implementation 2: Static Factory Method
```java
public class Elvis {
    private static final Elvis INSTANCE = new Elvis();
    private Elvis() { }
    public static Elvis getInstance() { return INSTANCE; }
}
```

### Implementation 3: The Enum Singleton (Best Practice 🏆)
```java
public enum Elvis {
    INSTANCE;
    public void leaveTheBuilding() { ... }
}
```

---

## 3. How to Break a Singleton & Defenses

| Attack Vector | How it Breaks Singleton | Defense for Classic Singleton | Enum Singleton |
|:---|:---|:---|:---|
| **Reflection** | `setAccessible(true)` forces private constructor open | Throw `AssertionError` in constructor if `instance != null` | **Immune** (JVM rejects reflection on enums) |
| **Serialization** | `readObject()` creates a duplicate heap instance | Implement `private Object readResolve() { return INSTANCE; }` | **Immune** (JVM returns constant instance) |
| **Cloning** | `clone()` manufactures duplicate | Override `clone()` to throw `CloneNotSupportedException` | **Immune** (`clone()` is final/disabled in Enum) |
| **Multithreading** | Lazy `if (instance == null)` can create 2 instances if called concurrently | Double-checked locking / Eager init / Holder idiom | **Immune** (JVM guarantees thread-safe class load) |

---

## 4. Noninstantiable Utility Classes (Effective Java Item 4)
For pure static helper classes (like `java.lang.Math` or `java.util.Arrays`), enforce noninstantiability with a **private constructor that throws**:

```java
public class MathUtils {
    // Suppress default constructor for noninstantiability
    private MathUtils() {
        throw new AssertionError("Cannot instantiate utility class.");
    }

    public static int max(int a, int b) { return (a > b) ? a : b; }
}
```
- **Why NOT make it `abstract`?** Making a utility class `abstract` fails because subclasses can still be created and instantiated (`class Sub extends MathUtils {}`).
