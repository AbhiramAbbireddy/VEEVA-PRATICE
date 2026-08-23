# Topic 3.4 — Method Overriding & Dynamic Method Dispatch

## 1. Method Overriding (Run-Time Polymorphism)
Method overriding occurs when a subclass provides its **own specific implementation** of a method that is already declared in its superclass with the exact same name, return type (or covariant return), and parameter list.

---

## 2. Dynamic Method Dispatch (Late Binding)

When `parentRef.method()` is executed:
1. **Compile Time:** The compiler checks the **Reference Type** to ensure `method()` exists and is accessible.
2. **Run Time:** The JVM inspects the **actual Object Type** in the heap and dynamically routes execution to that object's overridden method body.

```java
Shape shape = new Circle(); // Reference: Shape, Object: Circle
shape.area();               // Executes Circle's area() implementation dynamically!
```

---

## 3. Strict Rules of Overriding

1. **Exact Signature:** Name and parameter types must match.
2. **Covariant Return Type:** Return type can be the same or a **subtype** of the superclass method's return type (e.g. `Animal` -> `Dog`).
3. **Cannot Reduce Visibility:** An overriding method cannot have a more restrictive access modifier than the overridden method (`public` cannot become `default` or `private`).
   - **Why?** Violates the **Liskov Substitution Principle (LSP)**: a subclass must be substitutable anywhere its superclass is expected.
4. **Cannot Be Overridden:** `private`, `static`, or `final` methods.
5. **Always Use `@Override` (Effective Java Item 40):** Protects against subtle signature typos that accidentally overload instead of override.

---

## 4. Comparison: Overloading vs Overriding

| Dimension | Method Overloading | Method Overriding |
|:---|:---|:---|
| **Polymorphism** | Compile-Time (Static) | Run-Time (Dynamic) |
| **Binding** | Early / Static binding | Late / Dynamic dispatch |
| **Decided by** | Compiler based on **Reference/Argument Types** | JVM based on **Heap Object Type** |
| **Signature** | Same name, **different parameters** | Same name, **identical parameters** |
| **Return Type** | Can be anything | Same or Covariant subtype |
| **Access Modifiers**| Flexible | Cannot reduce visibility |
| **Keywords** | Can overload `static`/`final`/`private` | Cannot override `static`/`final`/`private` |
