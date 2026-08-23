# Topic 1.5 — Control Flow & Loops

## 1. Decision Structures: `if / else` & The Braces Trap

Without braces `{}`, an `if` block binds to **only the immediate next statement**.

```java
if (marks > 90)
    System.out.println("Distinction");
    System.out.println("Well done!"); // ⚠️ ALWAYS runs, regardless of marks!
```
- **Rule:** Always use braces `{}` even for single-line bodies.

---

## 2. Multi-Way Branching: `switch` & Fall-Through

```java
int day = 2;
switch (day) {
    case 1: System.out.println("A");
    case 2: System.out.println("B"); // runs
    case 3: System.out.println("C"); // ALSO runs (no break!)
    default: System.out.println("D"); // ALSO runs!
}
// Output: B C D
```
- **Fall-through:** Without `break`, execution continues down into subsequent cases regardless of their condition.

---

## 3. Loops: When to Pick Which

1. **`for` Loop:** Pick when the **iteration count is known**.
   - Anatomy: `for (INIT ; CONDITION ; UPDATE)`
   - Execution: (1) Init once -> (2) Condition check -> (3) Body -> (4) Update -> repeat step 2.
2. **`while` Loop:** Pick when **condition-driven** and count is unknown (may execute **0 times**).
3. **`do-while` Loop:** Pick when the body **must run at least once** before the first condition check (e.g. user input prompts, menus).

---

## 4. Classic Traps: Stray Semicolon & Break vs Continue

### The Stray Semicolon Trap
```java
for (int i = 0; i < 3; i++); // ⚠️ The semicolon terminates the loop with an EMPTY body!
{
    System.out.println("Hi"); // Independent block: runs ONCE!
}
```

### Break vs Continue
- `continue`: Skips the remainder of the **current iteration** and jumps to the update step.
- `break`: Terminates the **innermost enclosing loop** immediately.
