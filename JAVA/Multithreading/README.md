# 🧵 Java Multithreading & Concurrency Mastery

A comprehensive, production-grade guide to Multithreading in Java—covering foundational OS concepts, memory architecture, thread lifecycles, monitor synchronization, and inter-thread communication patterns.

---

## 📑 Module Index

```
JAVA/Multithreading/
├── README.md                                                  ← (You are here) Master Overview & Architecture Guide
│
├── 01 - Process vs Thread And Memory Model/
│   ├── README.md                                              ← Process vs Thread, JVM Heap/Stack/Registers/PC, Memory Segments
│   └── ProcessThreadDemo.java                                 ← Main thread inspection, JVM thread counts & memory inspection
│
├── 02 - Thread Creation And Lifecycle/
│   ├── README.md                                              ← Runnable vs Thread class, 6 Thread States, State Transition Matrix
│   ├── RunnableDemo.java                                      ← Implementation via Runnable interface (Best Practice)
│   └── ThreadClassDemo.java                                   ← Implementation via Thread class inheritance
│
├── 03 - Monitor Lock And Synchronization/
│   ├── README.md                                              ← Object Monitor Locks, Synchronized methods/blocks, Race Conditions
│   └── MonitorLockDemo.java                                   ← Mutual exclusion and lock acquisition walkthrough
│
├── 04 - Producer Consumer Problem/
│   ├── README.md                                              ← Inter-thread coordination, wait() vs sleep(), notify() vs notifyAll()
│   ├── ProducerConsumerDemo.java                              ← Single-item flag Producer-Consumer implementation
│   └── BoundedBufferDemo.java                                 ← Fixed-capacity Queue Bounded Buffer implementation
│
└── 05 - Explicit Locks And Condition/
    ├── README.md                                              ← ReentrantLock, ReadWriteLock, StampedLock, Semaphore, Condition
    ├── ReentrantLockDemo.java                                 ← Explicit lock/unlock in finally block
    ├── ReadWriteLockDemo.java                                 ← Shared read vs exclusive write locking
    ├── StampedLockDemo.java                                   ← Optimistic reading & stamp validation
    ├── SemaphoreDemo.java                                     ← Permit-based rate limiting & connection pool
    └── ConditionProducerConsumerDemo.java                     ← await() & signal() with multiple condition queues
```

---

## 🧠 Core Mental Model: Process vs. Thread Memory

```
┌──────────────────────────────────────────────────────────────────────────┐
│                             PROCESS (OS Level)                           │
│                                                                          │
│  ┌────────────────────────────────────────────────────────────────────┐  │
│  │                     SHARED MEMORY (All Threads)                    │  │
│  │                                                                    │  │
│  │  ┌─────────────────────┐  ┌─────────────────────┐  ┌────────────┐  │  │
│  │  │    Code Segment     │  │    Data Segment     │  │    Heap    │  │  │
│  │  │ (Compiled Bytecode) │  │  (Static & Globals) │  │  (Objects) │  │  │
│  │  │      [Read-Only]    │  │ [Sync Required!]    │  │[Sync Req.] │  │  │
│  │  └─────────────────────┘  └─────────────────────┘  └────────────┘  │  │
│  └────────────────────────────────────────────────────────────────────┘  │
│                                                                          │
│  ┌─────────────────────────┐                ┌─────────────────────────┐  │
│  │    THREAD 1 (Private)   │                │    THREAD 2 (Private)   │  │
│  │  ┌───────────────────┐  │                │  ┌───────────────────┐  │  │
│  │  │    Call Stack     │  │                │  │    Call Stack     │  │  │
│  │  │  (Frames, Locals) │  │                │  │  (Frames, Locals) │  │  │
│  │  ├───────────────────┤  │                ├───────────────────┤  │  │
│  │  │   CPU Registers   │  │                │   CPU Registers   │  │  │
│  │  ├───────────────────┤  │                ├───────────────────┤  │  │
│  │  │  Program Counter  │  │                │  Program Counter  │  │  │
│  │  └───────────────────┘  │                └───────────────────┘  │  │
│  └─────────────────────────┘                └─────────────────────────┘  │
└──────────────────────────────────────────────────────────────────────────┘
```

---

## ⚡ Thread State Transition Overview

Java threads adhere strictly to the 6 states defined in `java.lang.Thread.State`:

```
                 start()
    [ NEW ] ──────────────► [ RUNNABLE ] ◄─────────────────────────┐
                                │   ▲                              │
                   Scheduler    │   │ Dispatched                   │
                   dispatches   ▼   │                              │
                           [ RUNNING ]                             │
                           (in JVM mapped                          │
                           to RUNNABLE)                            │
                                │                                  │
         ┌──────────────────────┼──────────────────────┐           │
         │                      │                      │           │
  wait() │               sleep()│          Wait for    │           │
  join() │               wait(t)│          Monitor Lock│           │
         ▼                      ▼                      ▼           │
    [ WAITING ]         [ TIMED_WAITING ]         [ BLOCKED ]      │
         │                      │                      │           │
         │ notify()             │ Time expires /       │ Lock      │
         │ notifyAll()          │ notify()             │ acquired  │
         └──────────────────────┴──────────────────────┴───────────┘
                                │
                                │ run() completes
                                ▼
                         [ TERMINATED ]
```

---

## 🚨 Critical Concurrency Rules & Traps

| # | Topic | Golden Rule | Why? |
|:---|:---|:---|:---|
| **01** | `wait()` vs `sleep()` | `wait()` **releases** the monitor lock; `sleep()` **holds** it. | Calling `sleep()` inside a synchronized block while waiting for another thread causes a deadlock. |
| **02** | `wait()` in Loop | Always invoke `wait()` inside a `while` loop, never an `if` statement. | Prevents **spurious wakeups** and race conditions where another thread consumes the state first. |
| **03** | `Runnable` vs `Thread` | Prefer implementing `Runnable` over extending `Thread`. | Preserves single inheritance (`extends`), decouples task from execution, and integrates with ExecutorService. |
| **04** | Monitor Scope | Synchronize on the **shared resource object**, not separate objects. | Locking on different objects provides zero mutual exclusion. |
| **05** | `start()` vs `run()` | Always invoke `thread.start()`, never `thread.run()`. | `start()` requests a new OS thread from the JVM; `run()` merely executes synchronously on the calling thread. |
| **06** | `unlock()` in `finally` | Always invoke `lock.unlock()` inside a `finally` block. | Prevents permanent system deadlocks if an exception is thrown inside the critical section. |
| **07** | ReadWriteLock Advantage | Use `ReadWriteLock` when reads overwhelmingly outnumber writes. | Allows multiple concurrent readers without blocking, dramatically boosting throughput. |
| **08** | StampedLock Caution | `StampedLock` is **NOT reentrant**. | Re-acquiring a held stamp will result in self-deadlock. |
| **09** | Condition Multiple Queues | Prefer `Condition.await()` & `signal()` over `wait()`/`notify()` for complex coordination. | Enables separate, dedicated wait queues for producers and consumers on a single lock. |

