# Topic 1.4 — Operators, Arithmetic Promotion & Type Casting

## 1. Type Casting: Widening vs Narrowing

```
byte ──► short ──► int ──► long ──► float ──► double
char ──────────────┘
```

- **Widening (Implicit):** Small cup -> Big cup. Safe, no data loss. Performed automatically.
  ```java
  int a = 10;
  double d = a; // 10.0
  ```
- **Narrowing (Explicit Cast Required):** Big cup -> Small cup. May truncate or overflow.
  ```java
  double d = 9.99;
  int i = (int) d; // 9 (fractional part is truncated, not rounded!)

  int x = 130;
  byte b = (byte) x; // -126 (wraps around via two's complement: 130 - 256 = -126)
  ```

---

## 2. Integer Division Truncation

```java
int a = 7;
double y = a / 2; // Produces 3.0, NOT 3.5!
```
**WHY?** Java evaluates the right side `a / 2` first using integer types: `7 / 2` is integer division resulting in `3`. That `3` is only then widened to `double` (`3.0`).
- **Fix:** Force floating-point evaluation before division: `double y = a / 2.0;` or `(double) a / 2;`.

---

## 3. Arithmetic Expression Promotion

In Java, any arithmetic operation on operands smaller than `int` (`byte`, `short`, `char`) **automatically promotes both operands to `int` first**.

```java
byte x = 10;
byte y = 20;
byte z = x + y;       // ❌ Compile Error: x + y produces an int!
byte z = (byte)(x + y); // ✅ Correct: explicit cast

byte b = 1;
b = b + 1;  // ❌ Compile Error: (b + 1) is int
b += 1;     // ✅ Compiles! Compound assignment hides an implicit cast: (byte)(b + 1)
```

---

## 4. Increment Traps: Pre vs Post

- `i++` (Post-increment): Evaluates to the **current value first**, then increments `i`.
- `++i` (Pre-increment): Increments `i` **first**, then evaluates to the new value.

```java
int x = 5;
System.out.println(x++); // prints 5, x becomes 6
System.out.println(++x); // x becomes 7, prints 7
```

---

## 5. Short-Circuit Logical Operators (`&&`, `||` vs `&`, `|`)

- `&&` and `||` **short-circuit**: if the left operand determines the outcome, the right operand is **never evaluated**.
  ```java
  if (s != null && s.length() > 0) // SAFE: if s is null, s.length() is never called
  if (s != null & s.length() > 0)  // DANGEROUS: both sides evaluated -> NullPointerException!
  ```
