# Topic 1.8 — Input/Output: Scanner & Command-Line Arguments

## 1. Scanner Reading Methods
`java.util.Scanner` parses primitive types and strings using regular expressions from an `InputStream` (like `System.in`).
- `nextInt()`: Reads the next integer token.
- `nextDouble()`: Reads the next double token.
- `next()`: Reads the next word (stops at any whitespace).
- `nextLine()`: Reads everything up to the next newline character `\n`.

---

## 2. The Classic Scanner Buffer Trap: `nextInt()` + `nextLine()`

```java
Scanner sc = new Scanner("25\nKunal");
int age = sc.nextInt();      // Reads 25, but LEAVES the newline '\n' in the input stream!
String name = sc.nextLine(); // Immediately consumes the leftover '\n' and returns ""!
```

### The Fix
Always insert a dummy `sc.nextLine()` call to clear the newline buffer before reading text after reading numbers:
```java
int age = sc.nextInt();
sc.nextLine(); // "Eats" the leftover newline
String name = sc.nextLine(); // Now reads "Kunal" correctly
```

---

## 3. Command-Line Arguments (`String[] args`)
- When running `java App arg1 arg2`, the arguments are captured as an array of `String`s in `args`.
- If no arguments are passed, `args` is **an empty array (`args.length == 0`)**, **NOT null**.
- Numeric arguments must be parsed explicitly: `Integer.parseInt(args[0])`.
