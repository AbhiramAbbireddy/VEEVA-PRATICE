# Topic 2.5 — `final` Keyword & Initialization Order

## 1. The `final` Keyword

| Target | Effect |
|:---|:---|
| `final` **Variable** | Value assigned once; cannot be reassigned. |
| `final` **Method** | Cannot be overridden by subclasses (Phase 3). |
| `final` **Class** | Cannot be subclassed / extended (Phase 3). |

### ⚠️ Final Reference vs Final Content
```java
final int[] arr = {1, 2, 3};
arr[0] = 99; // ✅ Allowed! Modifying internal heap object state.
// arr = new int[5]; // ❌ Compile Error! Cannot point reference to another object.
```
- **Rule:** `final` locks the **pointer reference**, NOT the mutability of the object in the heap.

---

## 2. Blank Final Variables
A `final` variable declared without an initial value is a **blank final**. It must be initialized exactly once in one of three places:
1. At declaration.
2. In an **instance initializer block** (`{ ... }`).
3. Inside **all constructors**.

---

## 3. Strict Initialization Order in Java

When `new MyClass()` is executed, Java follows an unshakeable sequence:

```
[CLASS-LEVEL (Executed ONCE when class is loaded into JVM)]
   1. Static Variables & Static Initializer Blocks (in textual order)

[OBJECT-LEVEL (Executed EVERY time 'new' is called)]
   2. Instance Variables & Instance Initializer Blocks (in textual order)
   3. Constructor Body
```

### Trace Example
```java
class InitOrder {
    static { System.out.println("1. Static Block"); }
    { System.out.println("2. Instance Block"); }
    InitOrder() { System.out.println("3. Constructor"); }

    public static void main(String[] args) {
        System.out.println("Main starts");
        new InitOrder();
        new InitOrder();
    }
}
```
**Output:**
```
1. Static Block
Main starts
2. Instance Block
3. Constructor
2. Instance Block
3. Constructor
```
