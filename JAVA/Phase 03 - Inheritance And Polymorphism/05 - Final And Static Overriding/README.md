# Topic 3.5 — `final` & `static` Overriding Interactions

## 1. Why `static` Methods Cannot Be Overridden: Method Hiding

1. Overriding requires **Dynamic Method Dispatch** at runtime.
2. Dynamic Method Dispatch inspects the **actual instance object** in the heap.
3. `static` methods belong to the **class template**, not to any heap object.
4. With no object to inspect, the JVM **cannot perform dynamic dispatch**.

### Method Hiding Behavior
When a subclass declares a `static` method with the same signature as a superclass `static` method, it is **hidden**, not overridden. The version that runs is determined at **compile-time by the Reference Type**:

```java
class Parent { static void show() { System.out.println("Parent"); } }
class Child extends Parent { static void show() { System.out.println("Child"); } }

Parent p = new Child();
p.show(); // ⚠️ Prints "Parent"! Reference type decides static calls.
```

---

## 2. The Danger of Constructors Calling Overridable Methods (Effective Java Item 19)

> **"Constructors must NOT invoke overridable methods, directly or indirectly."**

### Why? (The Half-Initialized Child Trap)
When constructing a subclass, the superclass constructor runs **BEFORE** the subclass instance variables are initialized. If the superclass constructor calls an overridable method that the child overrides, **the child's overridden method executes on a half-initialized object**:

```java
class Super {
    Super() { overrideMe(); }
    void overrideMe() { }
}

final class Sub extends Super {
    private final Instant instant; // Blank final

    Sub() { instant = Instant.now(); }

    @Override
    void overrideMe() {
        System.out.println(instant.toString()); // 💥 NullPointerException! instant is null during Super() execution!
    }
}
```

### Safe Constructor Invocations
It is safe to call **`private` methods, `final` methods, and `static` methods** from constructors because none of them can be overridden by a subclass.
