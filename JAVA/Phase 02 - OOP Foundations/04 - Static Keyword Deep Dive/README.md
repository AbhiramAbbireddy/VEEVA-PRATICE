# Topic 2.4 — `static` Keyword Deep Dive

## 1. Intuition: Category Properties vs Individual Properties
- Individual Human properties: `name`, `age`, `salary` (Instance variables - each object gets a separate copy).
- Total Human population: `Human.population` (Belongs to the whole category/class, not one individual).

---

## 2. The 4 Forms of `static`

1. **Static Variable:** One shared copy allocated at class-loading time in method area/heap. Accessible via `ClassName.var`.
2. **Static Method:** Executable without creating any instance (`Math.max()`).
3. **Static Block:** Code block that executes **EXACTLY ONCE** when the class is first loaded by the JVM.
4. **Static Nested Class:** Inner class that does not require an instance of the outer enclosing class to be instantiated.

---

## 3. The Golden Rule of `static` Methods

> **A `static` method CANNOT directly access non-static (instance) variables or methods, and cannot use the `this` keyword.**

### WHY? (The Compiler's "Whose?" Confusion)
A `static` method runs at the class level with **zero objects existing**. Non-static members require an instance in the heap. When code inside a static method references `name`, the compiler asks: *"Whose name? No object was passed or created to own this variable!"*
- `this` represents the current instance. Since there is no instance in a static context, `this` is illegal.

---

## 4. Static Blocks Mechanics

```java
public class StaticBlockDemo {
    static int initializedValue;

    static {
        // Runs ONCE when class is loaded into memory
        System.out.println("Static block executed.");
        initializedValue = 42 * 10;
    }
}
```
If you instantiate 100 objects of `StaticBlockDemo`, the `static` block executes **only once** on the very first load.
