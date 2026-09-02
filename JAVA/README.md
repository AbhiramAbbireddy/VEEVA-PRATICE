# ☕ Core Java Mastery — Personal Knowledge Base & Roadmap

A comprehensive, production-grade repository documenting Core Java learning from ground zero to interview mastery. Built by combining conceptual intuition, JVM memory mechanics, deep "WHY" analysis, and industry best practices from Joshua Bloch's *Effective Java (3rd Edition)*.

---

## 🗺️ Learning Roadmap & Phase Directory

```
JAVA/
├── Phase 01 - Java Foundations/
│   ├── 01 - Basics And JVM/                 ← JDK vs JRE vs JVM, Platform Independence, Bytecode Flow
│   ├── 02 - Program Anatomy/                ← Class containers, main signature dissection, System.out
│   ├── 03 - Variables And Data Types/       ← 8 Primitives, Memory sizes, Defaults, Literals, Overflow
│   ├── 04 - Operators And Type Casting/     ← Division truncation, Expression promotion, Increment traps, Short-circuiting
│   ├── 05 - Control Flow And Loops/         ← if/else braces trap, switch fall-through, for/while/do-while, stray semicolon
│   ├── 06 - Arrays/                         ← Contiguous heap allocation, Zero-indexing, Aliasing, Arrays utility
│   ├── 07 - Methods And Pass By Value/      ← Pass-by-value proof, Stack frames, Recursion fundamentals
│   ├── 08 - Input Output Scanner/           ← Scanner reading mechanics, nextInt+nextLine trap, Command-line args
│   └── 09 - Packages And Imports/           ← Namespaces, Reverse-domain naming, Wildcard import boundaries, java.lang
│
├── Phase 02 - OOP Foundations/
│   ├── 01 - Classes And Objects/            ← Blueprints vs Instances, 'new' heap allocation, Reference vs Object
│   ├── 02 - Constructors And This/          ← Constructor rules, Chaining with this(), Shadowing resolution, Defaults
│   ├── 03 - Encapsulation And Access/       ← Invariant enforcement, Getters/Setters, 4 Access levels, ArrayList source
│   ├── 04 - Static Keyword Deep Dive/       ← Class-level variables/methods, Golden Rule of static, Static blocks
│   ├── 05 - Final And Initialization/       ← Final references vs immutability, Blank finals, Strict execution sequence
│   └── 06 - Singleton And Utility Classes/  ← Classic Singleton, Enum Singleton (EJ 3), Attack defenses, Noninstantiable (EJ 4)
│
├── Phase 03 - Inheritance And Polymorphism/
│   ├── 01 - Inheritance And Super/          ← extends, super() construction chaining, Heap memory layout, Diamond problem
│   ├── 02 - Reference Type vs Object Type/  ← Remote vs TV analogy, Compile-time access vs Runtime behavior
│   ├── 03 - Method Overloading/             ← Compile-time polymorphism, Signature rules, Static argument binding (EJ 52)
│   ├── 04 - Overriding And Dynamic Dispatch/← Runtime polymorphism, Dynamic Method Dispatch, Covariant returns, @Override (EJ 40)
│   ├── 05 - Final And Static Overriding/    ← Method hiding vs Overriding, Constructor calling overridable method trap (EJ 19)
│   └── 06 - Cosmic Object Class/            ← == vs equals(), hashCode() contract, PhoneNumber/Bigram bug, toString()
│
├── Phase 04 - Abstraction And Interfaces/
│   ├── 01 - Abstract Classes/               ← Partial blueprints, Abstract constructors, Illegal modifier combinations
│   ├── 02 - Interfaces/                     ← Pure capability contracts, Implicit modifiers, Multiple interfaces, Mixins
│   ├── 03 - Composition Over Inheritance/   ← Interface clash, InstrumentedHashSet double-counting bug (EJ 18), Forwarding wrapper
│   └── 04 - Java 8 Interface Evolution/     ← default/static methods, 3 Diamond resolution rules, removeIf concurrency hazard (EJ 21)
│
└── Multithreading/
    ├── 01 - Process vs Thread And Memory Model/  ← Process vs Thread, JVM Heap/Stack/PC/Registers, Memory Segments
    ├── 02 - Thread Creation And Lifecycle/       ← Runnable vs Thread, 6 Thread States, State transitions
    ├── 03 - Monitor Lock And Synchronization/    ← Monitor lock, synchronized methods/blocks, Mutex
    ├── 04 - Producer Consumer Problem/           ← Inter-thread coordination, wait(), notifyAll(), Spurious wakeups
    └── 05 - Explicit Locks And Condition/        ← ReentrantLock, ReadWriteLock, StampedLock, Semaphore, Condition
```

---

## 📓 Master Trap Notebook (Consolidated)

| # | Trap | Key Takeaway |
|:---|:---|:---|
| **01** | `JDK ⊃ JRE ⊃ JVM` | Factory contains Car which contains Engine. JVM runs bytecode; JRE bundles libraries; JDK adds tools. |
| **02** | `javac` vs `java` | `javac` takes **file names** (`javac Demo.java`); `java` takes **class names** (`java Demo`). |
| **03** | Platform Independence | Java bytecode is platform-independent; the JVM itself is platform-specific. |
| **04** | Case Sensitivity | Capitalizing classes is a *convention*; matching file name to public class name is a *rule (law)*. |
| **05** | `main` Signature | Must be `public static void main(String[] args)`. Wrong signature compiles but throws runtime error. |
| **06** | Variable Defaults | Instance & static fields get defaults (`0`, `null`, `false`); **local variables have NO default**. |
| **07** | Primitive Sizes | `byte(1)`, `short(2)`, `int(4)`, `long(8)`, `float(4)`, `double(8)`, `char(2)`, `boolean(unspecified)`. |
| **08** | Unicode in Char | `char` is 2 bytes because it supports universal Unicode characters, not just 1-byte ASCII. |
| **09** | Decimal Literals | `99.5` is `double` by default (needs `99.5f`); large whole numbers need `L` suffix (`10000000000L`). |
| **10** | Integer Division | `7 / 2 == 3` (truncation). `double d = 7 / 2;` produces `3.0`, not `3.5` because right side evaluates as int first. |
| **11** | Expression Promotion | Arithmetic on `byte`, `short`, `char` promotes to `int` first. `byte c = a + b` fails without `(byte)(a + b)`. |
| **12** | Compound Assignment | `b += 1` includes an implicit cast `(byte)(b + 1)`; `b = b + 1` does not and fails to compile. |
| **13** | Short-circuit Evaluation | `&&` and `\|\|` short-circuit (skipping right side on known false/true); `&` and `\|` always evaluate both sides. |
| **14** | Stray Semicolon Loop | `for (int i = 0; i < 3; i++); { System.out.println("Hi"); }` prints "Hi" **once** because `;` is an empty body. |
| **15** | Array Aliasing | `int[] b = a;` copies the heap reference. Mutating `b[0]` modifies `a[0]`. |
| **16** | Char Array Printing | `System.out.println(char[])` prints text; `System.out.println((Object)char[])` prints `[C@hash`. |
| **17** | Pass-By-Value | Java is **100% pass-by-value**. For primitives, value is copied. For objects, the reference is copied by value. |
| **18** | Scanner NextLine Trap | `nextInt()` reads number but leaves `\n` in the buffer. An extra `sc.nextLine()` is needed to consume it. |
| **19** | Wildcard Imports | `import java.util.*` imports all classes in `java.util`, but **never** subpackages like `java.util.concurrent`. |
| **20** | `new` Keyword | Dynamically allocates memory in heap at runtime and returns a reference. Left side is compile-time; right side is runtime. |
| **21** | `super()` Placement | `super(...)` must be the **very first statement** in a child constructor. Parent must be constructed before child. |
| **22** | Default Constructor Loss | If you write *any* constructor, Java stops providing the invisible default no-arg constructor. |
| **23** | Reference vs Object | **Access** is decided by Reference type at compile-time. **Behavior** (methods) is decided by Object type at runtime. |
| **24** | Fields are Hidden | Fields do not undergo dynamic dispatch. `Parent p = new Child(); System.out.println(p.x);` accesses `Parent.x`. |
| **25** | Static Method Hiding | Static methods cannot be overridden; they are hidden. Reference type determines which static method executes. |
| **26** | Final Ref ≠ Immutability | `final Student s = new Student();` prevents reassigning `s`, but the internal state of the `Student` object can change. |
| **27** | Constructor Traps | Constructors calling overridable methods invoke child overrides before child fields initialize (`null` / NPE risk). |
| **28** | equals() Contract | Must take `Object` parameter (`equals(Object o)`). Parameter of specific type is an overload, ignored by collections. |
| **29** | hashCode() Rule | Equal objects **must** produce equal hash codes. Failing to override `hashCode()` breaks `HashMap`/`HashSet`. |
| **30** | Inheritance Fragility | Subclasses depend on undocumented superclass self-use (e.g. `HashSet.addAll` calling `add`). Use composition instead. |
| **31** | Interface Modifiers | Interface fields are `public static final`. Interface methods are `public abstract` (pre-Java 8). Implements must be `public`. |
| **32** | Java 8 Diamond Rules | 1. Class wins over interface. 2. Sub-interface wins over super-interface. 3. Unrelated conflict requires manual resolution. |
| **33** | `start()` vs `run()` | Calling `run()` executes synchronously on the current thread; calling `start()` tells the JVM to spawn a new OS thread. |
| **34** | `wait()` vs `sleep()` | `wait()` **releases** the object's monitor lock; `Thread.sleep()` **retains** all held locks throughout its pause. |
| **35** | Spurious Wakeup Guard | Always call `wait()` inside a `while` loop (never `if`) so waking threads re-evaluate conditions before proceeding. |
| **36** | Monitor Lock Scope | Synchronize on the shared target object; locking on separate object instances gives zero mutual exclusion. |
| **37** | `unlock()` in `finally` | Always place explicit `lock.unlock()` inside `finally` to prevent unrecoverable deadlocks on unexpected exceptions. |
| **38** | ReadWriteLock Throughput | Use `ReentrantReadWriteLock` for read-heavy workloads to enable concurrent readers without lock contention. |
| **39** | StampedLock Non-Reentrant | `StampedLock` is **NOT reentrant**. Re-acquiring a lock on the same thread causes immediate self-deadlock. |
| **40** | Condition Queue Targeting | Use `Condition.await()` & `signal()` to target specific producer/consumer wait queues instead of waking all threads with `notifyAll()`. |


