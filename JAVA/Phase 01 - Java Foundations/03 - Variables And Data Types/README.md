# Topic 1.3 — Variables, Data Types & Literals

## 1. Statically Typed Philosophy
Java is **statically typed**: every variable's type must be declared before use and is checked at compile-time.
- **Goal:** Catch errors early (fail-fast) and guarantee identical memory sizing across all operating systems.

---

## 2. The 8 Primitive Data Types

| Type | Size | Range | Default (Fields) | Notes |
|:---|:---|:---|:---|:---|
| `byte` | 1 byte (8 bits) | -128 to 127 | `0` | Small integers |
| `short` | 2 bytes (16 bits) | -32,768 to 32,767 | `0` | Medium integers |
| `int` | 4 bytes (32 bits) | ~-2.14B to 2.14B | `0` | Default whole number type |
| `long` | 8 bytes (64 bits) | ~-9x10^18 to 9x10^18 | `0L` | Requires `L` suffix for large literals |
| `float` | 4 bytes (32 bits) | ~7 decimal digits | `0.0f` | Requires `f` or `F` suffix |
| `double` | 8 bytes (64 bits) | ~15 decimal digits | `0.0d` | Default floating-point type |
| `char` | 2 bytes (16 bits) | 0 to 65,535 (`\u0000` to `\uffff`) | `\u0000` | Stores Unicode code points, single quotes `'A'` |
| `boolean` | JVM-dependent | `true` / `false` | `false` | Not precisely sized in JVM spec (usually 1 byte) |

---

## 3. Literals & Traps

1. **Floating Point Literals:** Decimals default to `double`.
   ```java
   float f = 3.14;   // ❌ Compile Error: possible lossy conversion from double to float
   float f = 3.14f;  // ✅ Correct
   ```
2. **Integer Literals:** Whole numbers default to `int`.
   ```java
   long val = 10000000000;   // ❌ Compile Error: integer number too large
   long val = 10000000000L;  // ✅ Correct
   ```
3. **Readable Numeric Literals (Java 7+):**
   ```java
   int million = 1_000_000;
   long creditCard = 1234_5678_9012_3456L;
   ```
4. **Money / Exact Arithmetic:** Never use `float` or `double` for currency because binary floating-point cannot precisely represent numbers like `0.1` (`0.1 + 0.2 != 0.3`). Use `BigDecimal` or smallest units in integer (`cents`/`paise`).

---

## 4. Local Variables vs Instance Variables Defaults

- **Instance/Static Fields:** Allocated in heap/method area; initialized automatically to defaults (`0`, `0.0`, `false`, `null`).
- **Local Variables:** Allocated on stack frames; **have NO default values**. Attempting to read an uninitialized local variable causes a compile-time error: `variable might not have been initialized`.
