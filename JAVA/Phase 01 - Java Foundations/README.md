# Phase 01 — Java Language Foundations

## 🎯 Phase Objective
To understand how Java source code becomes a running program, master the execution model of the JVM, build intuition for primitive types, memory allocations, arithmetic promotion rules, control flow, array structures, method call stacks, pass-by-value mechanics, and package namespaces.

---

## 📑 Topics Covered

1. **[01 - Basics And JVM](./01%20-%20Basics%20And%20JVM/)**: The JVM/JRE/JDK distinction, platform independence, and the two-stage compilation pipeline (`javac` -> bytecode -> `java` -> JVM).
2. **[02 - Program Anatomy](./02%20-%20Program%20Anatomy/)**: Class containers, dissection of `public static void main(String[] args)`, and `System.out.println`.
3. **[03 - Variables And Data Types](./03%20-%20Variables%20And%20Data%20Types/)**: The 8 primitive data types, memory footprints, default initialization values, literals, and why local variables must be initialized.
4. **[04 - Operators And Type Casting](./04%20-%20Operators%20And%20Type%20Casting/)**: Integer division truncation, expression promotion (byte + byte = int), increment/decrement pre/post evaluation, short-circuit evaluation (`&&` vs `&`).
5. **[05 - Control Flow And Loops](./05%20-%20Control%20Flow%20And%20Loops/)**: Decision branching with `if/else`, switch fall-through mechanics, loop types (`for`, `while`, `do-while`), loop execution order, and the stray semicolon trap.
6. **[06 - Arrays](./06%20-%20Arrays/)**: Contiguous memory in heap, zero-based offset indexing, array aliasing (references), multi-dimensional arrays, and the `Arrays` utility class.
7. **[07 - Methods And Pass By Value](./07%20-%20Methods%20And%20Pass%20By%20Value/)**: Method signatures, why Java is 100% pass-by-value, reference copying semantics, and recursion base/recursive cases.
8. **[08 - Input Output Scanner](./08%20-%20Input%20Output%20Scanner/)**: Interactive reading with `Scanner`, the classic `nextInt()` + `nextLine()` buffer trap, and `String[] args` command-line argument processing.
9. **[09 - Packages And Imports](./09%20-%20Packages%20And%20Imports/)**: Directory mapping, collision prevention, reverse-domain convention, single vs on-demand imports, and `java.lang` automatic import.
