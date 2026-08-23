# Topic 3.2 — Reference Type vs Object Type

## 1. Intuition: The Remote Control vs The TV
- **Reference Type (Left of `=`):** The **Remote Control**. Dictates *which buttons are available to press* at compile-time.
- **Object Type (Right of `new`):** The **Physical TV in Heap**. Dictates *what the hardware actually does* at runtime.

---

## 2. Parent Reference -> Child Object (`Parent p = new Child();`)

```java
Box b = new BoxWeight(2, 3, 4, 8);
System.out.println(b.l);      // ✅ Works: Box has field 'l'
// System.out.println(b.weight); // ❌ Compile Error: 'Box' remote has no 'weight' button!
```

### The Law of Access vs Behavior
- **Access (Fields & Method Call Validity):** Decided by the **Reference Type** at **compile-time**.
- **Behavior (Which Method Body Executes):** Decided by the **Object Type** at **runtime** (via Dynamic Method Dispatch).

---

## 3. Why Child Reference -> Parent Object is Strictly Forbidden

```java
BoxWeight bw = new Box(2, 3, 4); // ❌ Compile Error: Incompatible types
```
**WHY?** In the heap, a standard `Box` has memory allocated ONLY for `l, h, w`. It has **no memory for `weight`**. If Java allowed a `BoxWeight` reference to point to a plain `Box`, calling `bw.weight` would read non-existent memory, causing catastrophic crashes. Java forbids this at compile-time.

---

## 4. Summary Matrix

| Scenario | Code | Compiles? | Runtime Reality |
|:---|:---|:---:|:---|
| **Parent Ref -> Child Obj** | `Parent p = new Child();` | ✅ Yes | Safe (IS-A). Can only access Parent-declared members. |
| **Child Ref -> Parent Obj** | `Child c = new Parent();` | ❌ No | Forbidden (Parent lacks Child-specific memory). |
| **Child Ref -> Child Obj** | `Child c = new Child();` | ✅ Yes | Full access to all Parent + Child members. |
| **Parent Ref -> Parent Obj** | `Parent p = new Parent();` | ✅ Yes | Standard base object access. |
