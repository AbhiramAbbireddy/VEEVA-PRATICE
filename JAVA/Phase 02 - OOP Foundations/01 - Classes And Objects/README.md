# Topic 2.1 — Classes, Objects & Memory Allocation

## 1. Why OOP? The Real-World Modeling Problem
Primitives and arrays can only store disconnected chunks of data. To represent an entity with mixed properties (e.g. Student with `rollNo`, `name`, `marks`), we bundle state and behavior into a **Class**.

---

## 2. Class vs Object

| Concept | Meaning | Memory Footprint | Analogy |
|:---|:---|:---|:---|
| **Class** | Named template / blueprint defining state & methods | **No instance data memory** allocated | Architecture Blueprint |
| **Object** | Concrete instance created from the blueprint | **Allocated dynamically in heap** | Physical Building |

### The 3 Properties of Every Object
1. **State:** Current values stored in its instance variables.
2. **Identity:** Unique memory address/reference distinguishing it from all other objects.
3. **Behavior:** Actions exposed by its methods.

---

## 3. The `new` Keyword & Memory Walkthrough

```java
Student s;           // Step 1: Declaration (Reference created on STACK, points to null)
s = new Student();   // Step 2: Initialization (Dynamic allocation in HEAP, returns reference)
```

- **Stack:** Holds reference variable `s`.
- **Heap:** Holds actual `Student` object containing fields initialized with defaults (`0`, `null`, `0.0`).
- **Dot Operator (`.`):** Dereferences the stack variable to access the heap object.

---

## 4. Edge Cases & Traps

```java
Student s1;
// System.out.println(s1.name); // ❌ Compile Error: s1 might not have been initialized

Student s2 = null;
// System.out.println(s2.name); // 💥 Runtime Error: NullPointerException (NPE)
```
