# Topic 05 — Explicit Locks & Condition (`java.util.concurrent.locks`)

## 1. Why Explicit Locks Over `synchronized`?

While Java's built-in `synchronized` keyword (intrinsic monitor lock) is simple and automatic, it has several critical limitations in production systems:

| Feature | `synchronized` (Intrinsic Lock) | Explicit Locks (`Lock` / `ReentrantLock`) |
|:---|:---|:---|
| **Acquisition Style** | Block-scoped; cannot span across methods. | Method-scoped; can `lock()` in one method and `unlock()` in another. |
| **Non-blocking Attempt** | ❌ No. Thread blocks indefinitely until lock is free. | ✅ Yes, via `tryLock()`. |
| **Timed Acquisition** | ❌ No. Cannot specify a timeout. | ✅ Yes, via `tryLock(timeout, unit)`. |
| **Interruptibility** | ❌ No. Cannot be interrupted while waiting for lock. | ✅ Yes, via `lockInterruptibly()`. |
| **Fairness Guarantee** | ❌ No. Thread scheduling is non-deterministic. | ✅ Yes. Can configure fair ordering (`new ReentrantLock(true)`). |
| **Read/Write Specialization** | ❌ No. Reads block other reads. | ✅ Yes, via `ReentrantReadWriteLock` & `StampedLock`. |
| **Multiple Wait-Sets** | ❌ Only 1 wait set per object (`wait()`/`notify()`). | ✅ Multiple independent wait queues via `Condition`. |

---

## 2. ReentrantLock (Mutual Exclusion with Flexibility)

`ReentrantLock` implements the `Lock` interface. Like `synchronized`, it guarantees that **only one thread at a time** can enter a critical section. It is **reentrant**, meaning a thread holding the lock can re-acquire it without deadlocking itself.

```
Thread 1 ──► lock.lock() ──► [ Critical Section ] ──► finally { lock.unlock(); }
                                    │
Thread 2 ──► lock.lock() ───────────┴─► [ BLOCKED / WAITING in lock queue ]
```

### 🚨 The Mandatory `try ... finally` Pattern
Because explicit locks do not release automatically, you **MUST** call `unlock()` inside a `finally` block. Failing to do so will cause permanent deadlocks if an exception is thrown!

```java
Lock lock = new ReentrantLock();

lock.lock(); // Acquire lock OUTSIDE the try block
try {
    // Critical Section
} finally {
    lock.unlock(); // Always release inside finally block!
}
```

---

## 3. ReadWriteLock (ReentrantReadWriteLock)

In read-heavy systems (e.g., product catalogs, caches), having threads block each other simply to read immutable data hurts throughput.
- **`readLock()` (Shared):** Multiple reader threads can acquire the lock simultaneously as long as no writer holds the lock.
- **`writeLock()` (Exclusive):** Only **one** writer thread can hold the lock. When writing, all readers and other writers are blocked.

```
Reader 1 ──► [Acquires Read Lock] ──┐
Reader 2 ──► [Acquires Read Lock] ──┼──► Can read concurrently!
Reader 3 ──► [Acquires Read Lock] ──┘
                                      
Writer 1 ──► [Wants Write Lock]   ───► BLOCKED until all readers release!
```

---

## 4. StampedLock (Java 8+: High-Performance & Optimistic Locking)

`StampedLock` provides three distinct locking modes using a numerical **stamp** (`long` value):

```
                                  ┌────────────────────────┐
                                  │      StampedLock       │
                                  └───────────┬────────────┘
                                              │
         ┌────────────────────────────────────┼────────────────────────────────────┐
         ▼                                    ▼                                    ▼
┌──────────────────┐                 ┌──────────────────┐                 ┌──────────────────┐
│ 1. Writing Mode  │                 │ 2. Reading Mode  │                 │3. Optimistic Read│
├──────────────────┤                 ├──────────────────┤                 ├──────────────────┤
│ lock.writeLock() │                 │ lock.readLock()  │                 │tryOptimisticRead │
│ Exclusive lock;  │                 │ Pessimistic shared│                │Non-blocking; no  │
│ returns stamp.   │                 │ lock; returns    │                 │actual lock taken!│
│ Unlocks with:    │                 │ stamp. Unlocks:  │                 │Validates via:    │
│ unlockWrite(st)  │                 │ unlockRead(st)   │                 │validate(stamp)   │
└──────────────────┘                 └──────────────────┘                 └──────────────────┘
```

### Optimistic Reading Mechanism
- Instead of acquiring a blocking read lock, the reader calls `long stamp = lock.tryOptimisticRead()`.
- It reads data without taking any lock (zero contention cost).
- It then calls `lock.validate(stamp)`:
  - If **`true`**: No write occurred in the meantime; the read values are valid!
  - If **`false`**: A writer modified the data during the read; the reader rolls back or falls back to a pessimistic `readLock()`.

> ⚠️ **Important:** `StampedLock` is **NOT reentrant**! A thread attempting to re-acquire its own lock will deadlock.

---

## 5. Semaphore (Permit-Based Concurrency Control)

A `Semaphore` manages a set of **permits** to restrict the number of threads accessing a physical or logical resource simultaneously (rate limiting, connection pooling, bounded thread execution).

```
                      ┌───────────────────────────┐
                      │    Semaphore (Permits: 2) │
                      └─────────────┬─────────────┘
                                    │
           ┌────────────────────────┴────────────────────────┐
           ▼                                                 ▼
┌──────────────────────┐                          ┌──────────────────────┐
│ Thread 1 (acquire)   │                          │ Thread 2 (acquire)   │
│ Permit count: 1 ──► 0│                          │ Permit count: 2 ──► 1│
│ [Enters Resource]    │                          │ [Enters Resource]    │
└──────────────────────┘                          └──────────────────────┘
           │                                                 │
           ▼                                                 ▼
┌──────────────────────┐                          ┌──────────────────────┐
│ Thread 3 (acquire)   │ ──► BLOCKED! (0 permits) │ Thread 1 (release)   │
│                      │ ◄── Woken up & Enters!   │ Permit count: 0 ──► 1│
└──────────────────────┘                          └──────────────────────┘
```

- `acquire()`: Takes a permit; if none are available, blocks until one is released.
- `release()`: Returns a permit back to the pool, notifying waiting threads.
- `new Semaphore(1)`: Equivalent to a binary semaphore / mutex lock.

---

## 6. Condition (Replacing `wait()` and `notify()`)

`Condition` decouples monitor methods (`wait`, `notify`, `notifyAll`) from `Object` and binds them to explicit `Lock` implementations.

```
Object Monitor (Intrinsic)          Condition Interface (Explicit)
---------------------------         ------------------------------
synchronized (obj)          ──►     lock.lock()
obj.wait()                  ──►     condition.await()
obj.notify()                ──►     condition.signal()
obj.notifyAll()             ──►     condition.signalAll()
```

### The Superpower of `Condition`: Multiple Wait Queues
With `synchronized`, an object has only **one** wait set. Producers and consumers share the same wait queue, forcing you to call `notifyAll()` to avoid missed signals.
With explicit locks, a single `Lock` can create **multiple independent condition queues**:
```java
Lock lock = new ReentrantLock();
Condition notFull  = lock.newCondition(); // Producer wait queue
Condition notEmpty = lock.newCondition(); // Consumer wait queue
```
- When a producer adds an item, it signals **only** `notEmpty.signal()` (wakes up a consumer).
- When a consumer takes an item, it signals **only** `notFull.signal()` (wakes up a producer).
- **Result:** No wasted context switches, zero spurious wakeups of the wrong thread tier!
