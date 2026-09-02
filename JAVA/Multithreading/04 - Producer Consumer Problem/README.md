# Topic 04 — Inter-Thread Communication & The Producer-Consumer Problem

## 1. Problem Definition: Producer-Consumer Coordination

Two concurrent threads—a **Producer** and a **Consumer**—share a common resource or bounded buffer:
- The **Producer** generates data and places it into the shared buffer.
- The **Consumer** retrieves and processes data from the buffer.

### Synchronization Invariants:
1. **The Producer must NOT produce** if the buffer is already full / item is already available.
2. **The Consumer must NOT consume** if the buffer is empty / item is unavailable (it must wait until an item arrives).
3. **Mutual Exclusion:** Both threads must not corrupt buffer state simultaneously.

---

## 2. The Inter-Thread Coordination Trio: `wait()`, `notify()`, `notifyAll()`

In Java, inter-thread signaling is performed via methods on `java.lang.Object`:

```
┌────────────────────────────────────────────────────────────────────────┐
│                        OBJECT MONITOR LOCK AREA                        │
│                                                                        │
│  [ Active Thread ] ──► Holds Lock & Executes                           │
│                                │                                       │
│                                │ calls wait()                          │
│                                ▼                                       │
│                   Releases Lock & Enters Wait Set                      │
│                                │                                       │
│  ┌─────────────────────────────┴────────────────────────────────────┐  │
│  │                            WAIT SET                              │  │
│  │   [ Consumer 1 ]       [ Consumer 2 ]       [ Consumer 3 ]       │  │
│  │    (WAITING)            (WAITING)            (WAITING)           │  │
│  └──────────────────────────────────────────────────────────────────┘  │
│                                ▲                                       │
│                                │ calls notifyAll()                     │
│  [ Producer Thread ] ──────────┘                                       │
│  (Produces item & wakes all waiting consumers)                         │
└────────────────────────────────────────────────────────────────────────┘
```

### Why are `wait()`, `notify()`, and `notifyAll()` defined in `Object` instead of `Thread`?
Because in Java, **locks and wait sets are attached to Objects in heap memory (the Monitor)**, not to threads. A thread acquires an object's monitor and registers itself on that specific object's wait set.

### Contract Requirements:
- A thread **MUST hold the object's monitor lock** (inside a `synchronized` block/method) before invoking `wait()`, `notify()`, or `notifyAll()`.
- Calling them outside a synchronized context throws `java.lang.IllegalMonitorStateException`.

---

## 3. The Lifecycle of `wait()` vs. `sleep()`

| Dimension | `Object.wait()` | `Thread.sleep(millis)` |
|:---|:---|:---|
| **Class** | `java.lang.Object` | `java.lang.Thread` |
| **Lock Behavior** | **RELEASES** the monitor lock, allowing other threads into synchronized blocks. | **RETAINS** all held monitor locks throughout the pause. |
| **State** | `WAITING` (or `TIMED_WAITING` with timeout). | `TIMED_WAITING`. |
| **Wakeup Mechanism** | Woken by `notify()`, `notifyAll()`, or timeout. | Woken automatically when sleep duration expires. |
| **Context** | Must be inside `synchronized` section. | Can be called anywhere. |
| **Usage** | Inter-thread communication & signaling. | Introducing time pauses. |

---

## 4. The Critical Rule: Always Call `wait()` Inside a `while` Loop

```java
// ❌ DANGEROUS: Using 'if' condition
if (!itemAvailable) {
    wait();
}

// ✅ CORRECT: Always use a 'while' loop!
while (!itemAvailable) {
    wait();
}
```

### Why a `while` loop is mandatory:
1. **Spurious Wakeups:** Under both POSIX and Windows OS thread schedulers, a thread can wake up from a wait set **without any thread having called `notify()`**!
2. **Multi-Consumer Race Condition:** If 5 consumer threads wake up via `notifyAll()`, the first thread to acquire the lock will consume the item and set `itemAvailable = false`. If the remaining 4 threads were in an `if` block, they would proceed to consume non-existent items. The `while` loop forces waking threads to **re-evaluate the condition** before proceeding!

---

## 5. `notify()` vs. `notifyAll()`

- **`notify()`:** Wakes up **one single arbitrary thread** from the object's wait set.
  - *Risk:* If that thread cannot proceed due to other conditions, the application can deadlock (lost notification).
- **`notifyAll()` (Recommended):** Wakes up **all threads** in the wait set. All woken threads transition to `BLOCKED` to compete for the monitor lock. Each thread re-checks its `while` condition safely.

---

## 6. Implementation Patterns in this Repository

### Pattern 1: Single-Item Flag ([ProducerConsumerDemo.java](./ProducerConsumerDemo.java))
- Coordinates via a boolean state flag `itemAvailable`.
- The Producer produces one item, transitions `itemAvailable = true`, and notifies the consumer.
- The Consumer waits while `!itemAvailable`, consumes the item, transitions `itemAvailable = false`, and notifies the producer.

### Pattern 2: Bounded Buffer / Fixed-Capacity Queue ([BoundedBufferDemo.java](./BoundedBufferDemo.java))
- Coordinates via a `Queue<Integer>` with a fixed capacity limit (e.g. `limit = 3`).
- **Producer Invariant:** While `buffer.size() == limit`, buffer is full ──► Producer invokes `wait()`.
- When an item is added via `buffer.offer(item)`, the producer signals waiting consumers via `notify()`.
- **Consumer Invariant:** While `buffer.isEmpty()`, buffer is empty ──► Consumer invokes `wait()`.
- When an item is retrieved via `buffer.poll()`, the consumer signals waiting producers via `notify()`.

```java
// Bounded Buffer condition checks (Strictly inside while loops!)
public synchronized void addItem(int item) throws InterruptedException {
    while (buffer.size() == capacity) {
        System.out.println("[Producer] Buffer is full (Capacity: " + capacity + "). Producer thread is waiting...");
        wait();
    }
    buffer.offer(item);
    System.out.println("[Producer] Produced item: " + item + " | Buffer size: " + buffer.size() + "/" + capacity);
    notify();
}

public synchronized int consumeItem() throws InterruptedException {
    while (buffer.isEmpty()) {
        System.out.println("[Consumer] Buffer is empty. Consumer thread is waiting...");
        wait();
    }
    int item = buffer.poll();
    System.out.println("[Consumer] Consumed item: " + item + " | Buffer size: " + buffer.size() + "/" + capacity);
    notify();
    return item;
}
```

