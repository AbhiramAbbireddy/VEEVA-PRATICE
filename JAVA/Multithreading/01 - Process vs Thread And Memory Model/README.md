# Topic 01 — Process vs. Thread & The JVM Memory Model

## 1. Introduction: Programs, Processes, and Threads

Before diving into concurrent programming, we must establish the distinction between a **Program**, a **Process**, and a **Thread**.

| Concept | Definition | Resource Allocation | Isolation Level |
|:---|:---|:---|:---|
| **Program** | Static set of compiled code/instructions sitting idle on disk (e.g., `Test.class`). | Zero runtime resources allocated. | Passive file. |
| **Process** | An active, executing **instance of a program** created by the Operating System. | Has its own dedicated memory space, file handles, and security context. | **Heavily isolated**. Processes cannot directly access each other's memory. |
| **Thread** | A **lightweight unit of execution** inside a process. The smallest sequence of instructions scheduled by the CPU. | Shares the process's heap and memory segments, but has its own stack and registers. | **Shared within process**. Threads communicate directly via shared memory. |

---

## 2. Compilation and Execution Flow in Java

```
[ Developer Code ]         [ Bytecode ]                       [ JVM Process ]
  Test.java       ──► javac ──► Test.class ──► java Test ──► Spawns OS Process
 (Source code)     (Compiler)   (Bytecode)   (JVM Launch)    Starts "main" Thread
```

1. **Compilation (`javac Test.java`):** Generates machine-neutral Java Bytecode (`Test.class`).
2. **Execution (`java Test`):** The operating system launches a new JVM instance (an OS Process). The JVM loads the target class and starts execution at `public static void main(String[] args)` on an initial thread called the **`main` thread**.

### Process Memory Configuration (`-Xms` and `-Xmx`)
When launching a Java process, the OS allocates virtual memory. You can configure the heap boundaries using JVM flags:
```bash
java -Xms256m -Xmx2048m Test
```
- `-Xms256m`: Sets the **initial / minimum** heap memory size to 256 MB.
- `-Xmx2048m`: Sets the **maximum** heap memory ceiling to 2048 MB (2 GB).
- If the application attempts to allocate objects beyond the maximum heap limit, the JVM crashes with:
  ```
  java.lang.OutOfMemoryError: Java heap space
  ```

---

## 3. Detailed Memory Architecture: Process vs. Thread

When the JVM process runs, its memory is divided into segments that are **shared across all threads** and areas that are **strictly private to each thread**.

```
┌────────────────────────────────────────────────────────────────────────────────┐
│                         JVM PROCESS MEMORY STRUCTURE                           │
│                                                                                │
│  ┌──────────────────────────────────────────────────────────────────────────┐  │
│  │                    SHARED ACROSS ALL THREADS IN PROCESS                  │  │
│  │                                                                          │  │
│  │  1. CODE SEGMENT (Method Area / Metaspace)                               │  │
│  │     - Holds compiled Bytecode (and JIT-compiled native machine code).    │  │
│  │     - Read-only across all threads.                                      │  │
│  │                                                                          │  │
│  │  2. DATA SEGMENT (Static Storage)                                        │  │
│  │     - Holds static variables, class-level constants, and type metadata.  │  │
│  │     - Readable and mutable by all threads ──► REQUIRES SYNCHRONIZATION!   │  │
│  │                                                                          │  │
│  │  3. HEAP MEMORY                                                          │  │
│  │     - Holds all runtime objects and arrays created via the `new` keyword.│  │
│  │     - Shared among all threads in this process (NOT across OS processes).│  │
│  │     - Garbage collection runs here.                                      │  │
│  │     - Mutable by all threads ──► REQUIRES SYNCHRONIZATION!               │  │
│  └──────────────────────────────────────────────────────────────────────────┘  │
│                                                                                │
│  ┌─────────────────────────────────┐      ┌─────────────────────────────────┐  │
│  │       THREAD 1 (PRIVATE)        │      │       THREAD 2 (PRIVATE)        │  │
│  │                                 │      │                                 │  │
│  │  • Thread Call Stack:           │      │  • Thread Call Stack:           │  │
│  │    Manages method frames,       │      │    Manages method frames,       │  │
│  │    primitive local variables,   │      │    primitive local variables,   │  │
│  │    and object reference handles.│      │    and object reference handles.│  │
│  │                                 │      │                                 │  │
│  │  • CPU Registers:               │      │  • CPU Registers:               │  │
│  │    Working memory used by CPU/  │      │    Working memory used by CPU/  │  │
│  │    JIT compiler for fast math.  │      │    JIT compiler for fast math.  │  │
│  │                                 │      │                                 │  │
│  │  • Program Counter (PC):        │      │  • Program Counter (PC):        │  │
│  │    Pointer to current JVM       │      │    Pointer to current JVM       │  │
│  │    instruction being executed.  │      │    instruction being executed.  │  │
│  └─────────────────────────────────┘      └─────────────────────────────────┘  │
└────────────────────────────────────────────────────────────────────────────────┘
```

### Why Thread Isolation Matters
- **Stack Safety:** Local variables inside a method live on that thread's own private stack. As long as a variable is local and does not escape, it is **inherently thread-safe** without any locking!
- **Heap Risk:** Any object placed on the heap is accessible by any thread that holds a reference to it. When multiple threads mutate the same heap object without synchronization, **race conditions** occur.

---

## 4. What is Multithreading?

> **Multithreading** is the capability of a single process to execute multiple concurrent paths of execution (threads) simultaneously, sharing common process resources while operating independently.

### Multitasking vs. Multithreading
- **Multitasking (OS Level):** The operating system running multiple distinct programs concurrently (e.g., streaming music on Spotify while typing in IntelliJ IDEA and compiling code in terminal).
- **Multithreading (Process Level):** A single application dividing its workload into concurrent sub-tasks (e.g., a web server handling 50 incoming HTTP requests on 50 concurrent worker threads).

---

## 5. Benefits and Challenges of Multithreading

### Benefits 🚀
1. **Improved Performance & Parallelism:** Maximizes modern multi-core CPUs by executing independent computations on separate physical cores simultaneously.
2. **Enhanced Responsiveness:** Long-running I/O operations (file reading, network queries) can run on background threads without freezing the user interface or main request pipeline.
3. **Resource Efficiency:** Threads are far lighter than processes. Spawning a new thread shares existing heap memory, avoiding the steep OS overhead of allocating an entirely new process.

### Challenges & Pitfalls ⚠️
1. **Concurrency Hazards:**
   - **Race Condition:** Occurs when multiple threads read and write shared data concurrently without coordination, producing unpredictable outcomes.
   - **Deadlock:** Two or more threads are permanently blocked, each waiting for a lock held by the other.
   - **Data Inconsistency:** Non-atomic operations (e.g., `count++`) leading to lost updates.
2. **Synchronization Overhead:** Excessive locking causes thread contention, CPU cache invalidations, and context-switching overhead.
3. **Debugging Complexity:** Multithreaded bugs are non-deterministic, hard to reproduce, and difficult to isolate in standard debuggers.
