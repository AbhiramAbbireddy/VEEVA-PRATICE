# Topic 1.6 — Arrays in Java

## 1. Nature of Arrays in Java
An array is a **fixed-size, contiguous container of homogeneous elements**.
- In Java, **arrays are full objects allocated on the heap**.
- Default values are initialized automatically for all slots when allocated (`new int[5]` -> all `0`).

---

## 2. Zero-Based Indexing & Memory Math
- **Why index from 0?** Index represents the **offset from base memory address**: `Address(i) = BaseAddress + (i * ElementSize)`.
- Valid bounds: `0` to `array.length - 1`. Accessing `array.length` throws runtime `ArrayIndexOutOfBoundsException`.
- `length` is a `final` **field**, not a method (`arr.length`, not `arr.length()`).

---

## 3. The Array Aliasing Trap

```java
int[] a = {1, 2, 3};
int[] b = a; // Copies the heap reference address, NOT the array!
b[0] = 99;
System.out.println(a[0]); // Prints 99! Both references point to the same heap object.
```

---

## 4. Special Printing Overload: `char[]` vs `Object`

```java
char[] c = {'J', 'a', 'v', 'a'};
System.out.println(c);          // Prints: Java (picks special PrintStream.println(char[]))
System.out.println((Object) c); // Prints: [C@hash (picks PrintStream.println(Object), invoking default Object.toString())
```
- `[C`: JVM internal type descriptor for `char[]` (`[` = 1D array, `C` = `char`).

---

## 5. Arrays Utility Class (`java.util.Arrays`)
- `Arrays.toString(arr)`: Formats array into readable string `[1, 2, 3]`.
- `Arrays.sort(arr)`: Dual-pivot quicksort for primitives; TimSort for objects.
- `Arrays.copyOf(arr, newLength)`: Creates a new resized array copy.
