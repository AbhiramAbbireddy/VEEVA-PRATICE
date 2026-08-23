# Topic 3.1 — Inheritance Basics & The `super` Keyword

## 1. Intuition: The DRY Principle & Hierarchies
Instead of copy-pasting an existing `Box` class to build a `BoxWeight` class, `BoxWeight` **extends** `Box`.
- **Inherited:** Public and protected fields/methods.
- **Not Inherited:** Constructors (must be invoked via `super()`) and `private` members.

---

## 2. The `super` Keyword & Construction Rules

```java
class BoxWeight extends Box {
    double weight;
    BoxWeight(double l, double h, double w, double weight) {
        super(l, h, w); // 🔴 MUST be the very first statement!
        this.weight = weight;
    }
}
```

### The Golden Rule of `super()`
> **A call to `super(...)` MUST be the very first statement in a child constructor.**
> **WHY?** The foundation/parent state must be completely initialized and allocated in memory before the child is permitted to configure its specialized properties.

---

## 3. Heap Memory Layout of Derived Objects
When `new BoxWeight(2, 3, 4, 8)` is executed, Java allocates **one single contiguous heap block**. 
- Even though `l`, `h`, and `w` were declared in `Box`, they physically live inside the `BoxWeight` object in heap memory.

---

## 4. Why Multiple Inheritance is Banned for Classes: The Diamond Problem

```
       [Class A: fun()]
          /        \
   [Class B: fun()] [Class C: fun()]
          \        /
       [Class D: extends B, C] ❌ AMBIGUITY!
```
If Class D invokes `d.fun()`, the JVM cannot resolve whether to execute B's or C's implementation. Java forbids multiple class inheritance to prevent this architectural flaw. (Solved via Interfaces).
