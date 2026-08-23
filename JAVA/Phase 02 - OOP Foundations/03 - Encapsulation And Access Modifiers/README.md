# Topic 2.3 — Encapsulation & Access Modifiers (Part 1)

## 1. Encapsulation: The Data Protection Shield
Encapsulation wraps state and methods inside a class and **restricts direct access** to fields to enforce business invariants (e.g. balance cannot be negative).
- **Analogy:** An ATM machine. You interact through validated buttons (methods), not by reaching into the cash vault (direct field mutation).

---

## 2. The 4 Access Modifiers

| Modifier | Same Class | Same Package | Subclass (diff package) | Global (Anywhere) |
|:---|:---:|:---:|:---:|:---:|
| `private` | ✅ | ❌ | ❌ | ❌ |
| *(default)* | ✅ | ✅ | ❌ | ❌ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `public` | ✅ | ✅ | ✅ | ✅ |

### Golden Rule (Effective Java Item 15)
> **"Make each class or member as inaccessible as possible."**
> Start with `private`, expose via accessors only where necessary. Top-level classes can only be `public` or `package-private` (default).

---

## 3. Real-World Proof: `ArrayList` Internal Source Code
Inside `java.util.ArrayList`:
```java
private Object[] elementData; // The backing storage array
private int size;             // Count of active elements
```
If `elementData` and `size` were `public`, clients could modify `size = 1000` while `elementData` has length 10, causing catastrophic crashes across `get()` and `iterator()`. Encapsulation guarantees `size` and `elementData` stay synchronized via methods like `add()` and `remove()`.

---

## 4. The Getter Mutation Leak (Effective Java Item 15 & 50)
Returning a reference to a mutable private field (like an array) allows callers to modify private state from outside:
```java
public class LeakyClass {
    private String[] roles = {"USER", "ADMIN"};
    public String[] getRoles() { return roles; } // ⚠️ Security hole: caller can do getRoles()[1] = "HACKED"!
    public String[] getRolesSafe() { return roles.clone(); } // ✅ Defensive copy
}
```
