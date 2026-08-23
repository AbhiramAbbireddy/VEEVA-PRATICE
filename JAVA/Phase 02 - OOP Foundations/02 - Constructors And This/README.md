# Topic 2.2 — Constructors & The `this` Keyword

## 1. Why Constructors Exist
To guarantee that newly created objects are initialized into a **valid, fully formed state** immediately upon birth, preventing half-initialized instances.

### Constructor Rules
1. Name **must match the class name exactly**.
2. Must have **NO return type** (not even `void`).
3. Invoked automatically when `new` is executed.

---

## 2. Constructor Overloading & The `this` Keyword

- `this`: Reference variable pointing to the **current invoking heap object**.
- **Resolving Shadowing:** Disambiguates between parameter names and instance variable names (`this.rno = rno;`).
- **Constructor Chaining (`this()`):** Invokes another constructor in the same class.

### The Golden Rule of `this()` Chaining
> **A call to `this(...)` MUST be the very FIRST statement in the constructor.**
> You cannot execute code before delegating to the foundational constructor.

---

## 3. The 3 Flavors of Constructors

1. **Default Constructor (Injected by Java):** No-arg constructor injected automatically *only if no constructors are written by the developer*.
2. **Custom No-Arg Constructor:** Explicit zero-parameter constructor.
3. **Parameterized Constructor:** Accepts values to configure instance state directly.

### ⚠️ The Default Constructor Suppression Trap
If you define **ANY** constructor with parameters, Java **immediately stops providing the default no-arg constructor**. Calling `new ClassName()` without explicitly declaring a no-arg constructor will trigger a compile-time error.
