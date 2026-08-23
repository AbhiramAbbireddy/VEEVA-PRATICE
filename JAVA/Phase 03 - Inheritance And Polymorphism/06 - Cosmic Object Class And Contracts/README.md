# Topic 3.6 — The Cosmic `Object` Class & Its Contracts

## 1. The Universal Root: `java.lang.Object`
Every class in Java directly or indirectly inherits from `Object`. It provides:
`equals()`, `hashCode()`, `toString()`, `getClass()`, `clone()`, `wait()`, `notify()`, `notifyAll()`, `finalize()`.

---

## 2. `==` vs `equals()`

| Operator / Method | What It Compares | Overridable? |
|:---|:---|:---:|
| `==` (Reference equality) | Memory identity: Do both references point to the exact same heap object? | ❌ No |
| `.equals()` (Logical equality) | Object state/contents according to domain rules. Default implementation delegates to `this == obj`. | ✅ Yes |

---

## 3. The `equals()` & `hashCode()` Contract (Effective Java Item 10 & 11)

> **"If two objects are equal according to `equals(Object)`, they MUST produce the exact same integer from `hashCode()`."**

### What Breaks in `HashMap` if hashCode() is not overridden?
`HashMap` uses `hashCode()` to calculate the bucket index, then uses `equals()` to find the matching key inside that bucket.
- If two logically equal objects have different hash codes, `map.get(key)` searches the **wrong bucket** and returns `null` even though the key is present.

---

## 4. The 4-Step `equals()` Recipe (Effective Java Item 10)

```java
@Override
public boolean equals(Object obj) {
    // 1. Identity shortcut
    if (this == obj) return true;

    // 2. Null and Type Check
    if (obj == null || getClass() != obj.getClass()) return false;

    // 3. Cast to specific type
    Employee other = (Employee) obj;

    // 4. Compare all significant fields
    return this.id == other.id &&
           Double.compare(this.salary, other.salary) == 0 &&
           Objects.equals(this.name, other.name);
}

@Override
public int hashCode() {
    return Objects.hash(id, name, salary);
}
```

---

## 5. Subclassing Value Classes Breaks the Contract (ColorPoint Problem)
Attempting to extend an instantiable value class (e.g. `ColorPoint extends Point`) and adding a new value component (`Color`) **violates either Symmetry or Transitivity**.
- **The Solution:** **Favor Composition over Inheritance** (Effective Java Item 10 & 18). Give `ColorPoint` a private `Point` field instead of extending `Point`.
