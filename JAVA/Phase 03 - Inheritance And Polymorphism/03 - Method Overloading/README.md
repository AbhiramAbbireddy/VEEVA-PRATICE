# Topic 3.3 — Polymorphism: Method Overloading (Compile-Time)

## 1. Method Overloading Principles
Method overloading occurs when methods share the **same name** but have **different parameter lists** (different count, types, or order of parameters).
- Known as **Compile-Time / Static Polymorphism**.
- The compiler binds the call to the matching method signature at **compile-time** based on the declared argument types.

---

## 2. Signature Rules

1. Parameter list **MUST differ**.
2. Return type is **NOT** part of the method signature. Changing only the return type triggers a compile error:
   ```java
   void calculate(int x) { }
   int  calculate(int x) { return x * 2; } // ❌ Compile Error: method is already defined
   ```

---

## 3. The Static Argument Type Binding Trap (Effective Java Item 52)

Unlike overriding, which inspects the dynamic runtime object in the heap, **overloading selection is entirely static**:

```java
public class OverloadTrap {
    static void classify(Set<?> s) { System.out.println("Set"); }
    static void classify(List<?> l) { System.out.println("List"); }
    static void classify(Collection<?> c) { System.out.println("Unknown Collection"); }

    public static void main(String[] args) {
        Collection<?>[] collections = {
            new HashSet<String>(),
            new ArrayList<String>(),
            new HashMap<String, String>().values()
        };

        for (Collection<?> c : collections) {
            classify(c); // ⚠️ Prints "Unknown Collection" 3 times!
        }
    }
}
```
**WHY?** The compile-time type of parameter `c` is `Collection<?>`. The compiler binds to `classify(Collection<?>)` at compile-time and never considers the runtime `HashSet` or `ArrayList` type.
