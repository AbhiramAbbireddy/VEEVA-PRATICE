# Topic 03 — Monitor Lock & Synchronization

## 1. What is a Monitor Lock?

In Java, every object created in the heap has an intrinsic lock associated with it, known as a **Monitor Lock** (or Intrinsic Lock).
- The monitor lock state is stored inside the object's memory header (the **Mark Word** in the 64-bit object header).
- The `synchronized` keyword leverages this monitor lock to enforce **Mutual Exclusion (Mutex)**—guaranteeing that **only one thread at a time can execute a synchronized critical section** guarded by that object.

---

## 2. How Monitor Locks Function

```
Thread A ──► [ Attempts Entry ] ──► Monitor Lock FREE? ──► YES ──► Acquires Lock ──► [ Executes Synchronized Code ]
                                                                                         │
Thread B ──► [ Attempts Entry ] ──► Monitor Lock FREE? ──► NO                            ▼
                  │                                                              Exits Block & Releases Lock
                  ▼                                                                      │
           [ BLOCKED State ] ◄───────────────────────────────────────────────────────────┘
           (Waits in line until lock is released)
```

1. When Thread A arrives at a `synchronized` block/method, it attempts to acquire the target object's monitor lock.
2. If the lock is available, Thread A acquires it and proceeds into the critical section.
3. If Thread B attempts to enter any critical section guarded by that **same object's monitor lock**, the JVM halts Thread B and transitions it into the **`BLOCKED`** state.
4. When Thread A completes the section (either by returning normally or throwing an exception), the JVM automatically releases the monitor lock.
5. The thread scheduler unblocks Thread B, allowing it to compete for and acquire the lock.

---

## 3. The 3 Forms of Synchronization in Java

### 1. Synchronized Instance Method
Locks on the **`this`** instance (the invoking object):
```java
public synchronized void updateBalance(double amount) {
    // Locks on 'this'
    this.balance += amount;
}
```

### 2. Synchronized Block (Recommended for Granularity ⭐)
Locks on a specific specified object:
```java
public void updateBalance(double amount) {
    // Only lock the critical section, not the entire method
    synchronized (this) {
        this.balance += amount;
    }
}
```
*Tip: You can use a dedicated private lock object to avoid exposing your lock to external callers:*
```java
private final Object lock = new Object();
synchronized (lock) {
    // critical section
}
```

### 3. Synchronized Static Method
Locks on the **`Class` object** (`MyClass.class`), not on any instance:
```java
public static synchronized void globalIncrement() {
    // Locks on MyClass.class
    counter++;
}
```

---

## 4. Critical Rules & Common Traps

| Trap | Explanation | Outcome |
|:---|:---|:---|
| **Locking on Different Objects** | Thread 1 locks on `obj1`, Thread 2 locks on `obj2`. | **Zero mutual exclusion!** Both threads execute simultaneously, causing race conditions. |
| **Reentrant Nature** | If Thread A holds lock on `obj` and calls another synchronized method on `obj`, can it enter? | **Yes!** Java locks are **reentrant**. A thread that holds a lock can re-acquire it without deadlocking itself. |
| **Exception Safety** | What if an exception is thrown inside a `synchronized` block? | The JVM's `monitorexit` bytecode instruction **automatically releases the lock** even on unhandled errors. |
