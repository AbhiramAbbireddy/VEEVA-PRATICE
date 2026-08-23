# Topic 1.2 — Anatomy of a Java Program

## 1. Structure of a Java Program

In Java, **everything must reside inside a class**. Java does not allow free-floating functions or global variables.

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

---

## 2. Dissection of the `main` Signature

```java
public static void main(String[] args)
```

| Keyword | Purpose | WHY? |
|:---|:---|:---|
| `public` | Access modifier | The JVM executes outside your package; `main` must be globally accessible. |
| `static` | Class-level modifier | Allows the JVM to invoke `Hello.main(...)` directly **without creating an instance of `Hello`** first. Avoids constructor chicken-and-egg bootstrap problems. |
| `void` | Return type | The entry point does not return a value back to the operating system/JVM. |
| `main` | Method identifier | The hard-coded method name the JVM launcher looks for. |
| `String[] args` | Array of Strings | Captures arguments passed from the command line at startup (`java Hello arg1 arg2`). |

---

## 3. Dissection of `System.out.println`

- `System`: A `final` utility class in `java.lang`.
- `out`: A `public static final PrintStream` variable inside `System`.
- `println()`: An overloaded method in `PrintStream` that prints data followed by a platform-specific newline.

---

## 4. Fundamental Rules

1. **One public class per file:** A `.java` file can contain at most one `public` class, and the file name **must exactly match** that public class name (case-sensitive).
2. **Naming Conventions:**
   - Classes: `PascalCase` (`StudentManager`)
   - Methods & Variables: `camelCase` (`calculateAverageMarks`)
   - Constants: `UPPER_SNAKE_CASE` (`MAX_CAPACITY`)
