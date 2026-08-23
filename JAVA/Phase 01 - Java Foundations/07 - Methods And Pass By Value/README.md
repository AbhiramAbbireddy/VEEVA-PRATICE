# Topic 1.7 — Methods, Pass-by-Value & Recursion

## 1. Method Signatures
A method's **signature** consists solely of its **method name + parameter types list**.
- Return type is **NOT** part of the method signature. Two methods in the same class differing only by return type trigger a compile error: `method is already defined`.

---

## 2. The Definitive Law: Java is 100% Pass-By-Value

> **"Java is ALWAYS pass-by-value. There are no exceptions."**

### Case 1: Primitives (Value Copy)
The method receives a pure copy of the primitive value. Modifying it inside the method has zero effect on the caller.
```java
static void change(int n) { n = 100; }
int x = 5;
change(x);
System.out.println(x); // 5 (untouched)
```

### Case 2: Objects (Reference Copy)
The variable holding an object holds an address/pointer reference. When passed, **a copy of that reference is passed by value**.
- Modifying object contents modifies the shared heap object:
  ```java
  static void mutate(int[] arr) { arr[0] = 99; }
  int[] nums = {1, 2, 3};
  mutate(nums);
  System.out.println(nums[0]); // 99 (shared object was modified)
  ```
- Reassigning the parameter reference points the copy elsewhere and leaves the caller untouched:
  ```java
  static void replace(int[] arr) { arr = new int[]{7, 7, 7}; }
  int[] nums = {1, 2, 3};
  replace(nums);
  System.out.println(nums[0]); // 1 (caller still points to original array)
  ```

---

## 3. Recursion
A method that invokes itself must provide:
1. **Base Case:** Halting condition that returns without recursing.
2. **Recursive Case:** Progress towards the base condition.
- Missing base case leads to stack frame exhaustion -> `StackOverflowError`.
