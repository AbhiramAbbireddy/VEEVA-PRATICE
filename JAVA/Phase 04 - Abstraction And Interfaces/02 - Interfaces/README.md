# Topic 4.2 — Interfaces: The Pure Capability Contract

## 1. Pure Capability Contracts
An interface defines **WHAT** a class can do without prescribing **HOW**.
- **Analogy:** A Driver's License. It requires `steer()`, `brake()`, `accelerate()`. Any human (regardless of age, gender, or family hierarchy) who provides those actions qualifies as a driver.

---

## 2. Implicit Modifiers in Interfaces

| Member Type | What You Write | What Java Silently Injects |
|:---|:---|:---|
| **Fields** | `int MAX_SPEED = 120;` | `public static final int MAX_SPEED = 120;` |
| **Methods** (pre-Java 8) | `void accelerate();` | `public abstract void accelerate();` |

### ⚠️ Consequence: Implementations Must Be Public
Because interface methods are implicitly `public`, every implementing class **must declare its implementation method as `public`**. Omitting `public` (default access) causes a compile error for reducing visibility (Liskov Substitution Principle).

---

## 3. Multiple Interfaces & Mixins (Effective Java Item 20)
Java allows a class to implement multiple interfaces (`class Bird implements Flyable, Swimmable`).
- Enables **nonhierarchical type frameworks**: avoids exponential class proliferation.
- Enables **Mixins**: Optional behaviors added to primary types (e.g. `Comparable`).

---

## 4. Abstract Class vs Interface Comparison

| Dimension | Abstract Class | Interface |
|:---|:---|:---|
| **Purpose** | Partial blueprint with shared state/code | Pure capability contract / mixin |
| **State / Fields** | Can hold mutable instance fields | Only `public static final` constants |
| **Constructors** | ✅ Yes | ❌ No |
| **Multiple Inheritance** | ❌ Single inheritance (`extends`) | ✅ Multiple implementation (`implements`) |
| **Retrofitting** | Difficult (disrupts whole tree) | Easy (add `implements X`) |
