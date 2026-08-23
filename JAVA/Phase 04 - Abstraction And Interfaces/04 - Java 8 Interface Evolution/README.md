# Topic 4.4 — Java 8 Interface Evolution: Default & Static Methods

## 1. Why Default Methods Were Added
Prior to Java 8, adding a new abstract method to an interface (like adding `forEach` to `Collection`) **broke every implementing class in the world**.
- **Default methods** provide a default method body, enabling **non-breaking interface evolution**.

---

## 2. The 3 Diamond Conflict Resolution Rules

When a class inherits competing default method implementations:

1. **Rule 1: Class Always Wins:** A method declared in a class or superclass **always overrides** any interface default method.
2. **Rule 2: Sub-Interface Wins:** If no class declares the method, the **most specific sub-interface** default implementation wins.
3. **Rule 3: Unrelated Conflict Requires Manual Resolution:** If two unrelated interfaces provide conflicting defaults, Java throws a compile error. The implementing class must explicitly override the method and resolve the conflict (e.g. via `InterfaceName.super.method()`).

---

## 3. Concurrency Hazard of Retrofitting Default Methods (Effective Java Item 21)

Adding default methods to existing interfaces can silently break third-party wrappers:
- Java 8 added `default boolean removeIf(...)` to `Collection`.
- Apache Commons `SynchronizedCollection` wrapper did not know about `removeIf`, so it inherited the default unsynchronized implementation.
- Calling `removeIf()` concurrently bypassed synchronization locks -> **Race conditions / data corruption**.

> **Lesson:** Think carefully before adding default methods to existing public interfaces.
