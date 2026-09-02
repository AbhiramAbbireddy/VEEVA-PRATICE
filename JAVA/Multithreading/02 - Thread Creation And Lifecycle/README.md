# Topic 02 — Thread Creation & Thread Lifecycle

## 1. Two Ways to Create a Thread in Java

Java provides two core mechanisms to define and launch threads:

```
                  ┌────────────────────────────────────────┐
                  │          Thread Creation Ways          │
                  └───────────────────┬────────────────────┘
                                      │
           ┌──────────────────────────┴──────────────────────────┐
           ▼                                                     ▼
┌──────────────────────────────────────┐  ┌──────────────────────────────────────┐
│     Implementing `Runnable`         │  │       Extending `Thread`             │
├──────────────────────────────────────┤  ├──────────────────────────────────────┤
│  class Task implements Runnable {    │  │  class Worker extends Thread {       │
│      @Override                       │  │      @Override                       │
│      public void run() { ... }       │  │      public void run() { ... }       │
│  }                                   │  │  }                                   │
│                                      │  │                                      │
│  Thread t = new Thread(new Task());  │  │  Worker t = new Worker();            │
│  t.start();                          │  │  t.start();                          │
└──────────────────────────────────────┘  └──────────────────────────────────────┘
```

---

## 2. Approach 1: Implementing `Runnable` Interface (Recommended Best Practice ⭐)

`Runnable` is a Functional Interface (`@FunctionalInterface`) containing a single contract method:
```java
@FunctionalInterface
public interface Runnable {
    void run();
}
```

### Execution Steps
1. **Define the Task:** Implement `Runnable` and override `run()` to specify the code the thread will execute.
2. **Instantiate the Task:** Create an instance of your runnable class:
   ```java
   MyTask task = new MyTask();
   ```
3. **Bind to Execution Vehicle:** Pass the task instance to the `Thread` constructor:
   ```java
   Thread worker = new Thread(task, "Worker-1");
   ```
4. **Launch Execution:** Call `worker.start()` to spawn a new OS thread.

---

## 3. Approach 2: Extending `Thread` Class

The `Thread` class itself implements `Runnable`:
```java
public class Thread implements Runnable {
    // ...
    @Override
    public void run() { ... }
}
```

### Execution Steps
1. **Subclass `Thread`:** Create a class that extends `Thread` and override `run()`.
2. **Instantiate and Launch:**
   ```java
   MyThread worker = new MyThread();
   worker.start();
   ```

---

## 4. Why Does Java Have Two Ways to Create Threads?

| Criteria | Implementing `Runnable` | Extending `Thread` |
|:---|:---|:---|
| **Inheritance Hierarchy** | **Preserves single inheritance**. Your class can still extend another domain class (e.g. `class Task extends BaseEntity implements Runnable`). | **Consumes single inheritance**. Your class cannot extend any other class because Java does not support multiple class inheritance. |
| **Separation of Concerns** | **Decouples Task from Worker**. `Runnable` defines *what to do*; `Thread` defines *how to run it*. | Conflates the task logic with the thread infrastructure. |
| **Thread Pool Compatibility** | **Fully compatible with ExecutorService**. You can submit `Runnable` tasks to thread pools without tying them to physical threads. | Incompatible with thread pooling. |
| **Resource Sharing** | One `Runnable` instance can be shared across multiple threads. | Each thread requires a distinct object subclass. |

> **Verdict:** Always prefer implementing `Runnable` (or `Callable` with `ExecutorService`). Extend `Thread` only when building custom thread infrastructure.

---

## 5. Thread Lifecycle: The 6 Thread States

Java defines 6 distinct lifecycle states in `java.lang.Thread.State`:

```
                 start()
    [ NEW ] ──────────────► [ RUNNABLE ] ◄─────────────────────────┐
                                │   ▲                              │
                   Scheduler    │   │ Dispatched                   │
                   dispatches   ▼   │                              │
                           [ RUNNING ]                             │
                                │                                  │
         ┌──────────────────────┼──────────────────────┐           │
         │                      │                      │           │
  wait() │               sleep()│          Waiting for │           │
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

### Detailed State Breakdown

| State | Definition | Triggering Condition | How It Transitions Back |
|:---|:---|:---|:---|
| **`NEW`** | A thread object has been created via `new Thread()`, but `start()` has not yet been called. | `Thread t = new Thread();` | Call `t.start()` to transition to `RUNNABLE`. |
| **`RUNNABLE`** | The thread is eligible to run and waiting for CPU time from the OS thread scheduler, OR actively executing on a core. | `t.start()`, or waking from wait/sleep. | Dispatched by CPU scheduler to execute instructions. |
| **`BLOCKED`** | The thread is waiting to acquire an **intrinsic monitor lock** to enter or re-enter a `synchronized` block/method currently held by another thread. | Attempting to enter a `synchronized` section locked by another thread. | The lock-holding thread exits the `synchronized` section, releasing the lock. |
| **`WAITING`** | The thread is waiting **indefinitely** for another thread to perform a specific action. | `Object.wait()`, `Thread.join()`, `LockSupport.park()`. | Another thread invokes `Object.notify()` or `notifyAll()`, or the joined thread finishes. **Releases all monitor locks!** |
| **`TIMED_WAITING`** | The thread is waiting for a **specified maximum duration**. | `Thread.sleep(millis)`, `Object.wait(millis)`, `Thread.join(millis)`. | Timeout expires, or `notify()`/`interrupt()` occurs. **`sleep()` DOES NOT release locks!** |
| **`TERMINATED`** | The thread has finished executing its `run()` method (either by returning normally or terminating due to an unhandled exception). | `run()` finishes execution. | **Terminal state**. A terminated thread cannot be restarted. |

---

## 6. Critical Interview Traps

### Trap 1: Calling `run()` instead of `start()`
```java
Thread t = new Thread(task);
t.run();   // ❌ WRONG! Executes synchronously on the CALLING thread (e.g. main).
t.start(); // ✅ CORRECT! Prompts the JVM to spawn a new native OS thread.
```

### Trap 2: Restarting a Terminated Thread
```java
Thread t = new Thread(task);
t.start();
// ... thread finishes ...
t.start(); // 💥 Throws java.lang.IllegalThreadStateException!
```
A thread's lifecycle is strictly one-way. Once a thread reaches `TERMINATED`, it can never be started again.

### Trap 3: `wait()` vs `sleep()` Lock Handling
- `Thread.sleep(5000)`: Pauses the thread but **retains all held monitor locks**. Other threads needing those locks remain blocked!
- `object.wait()`: Pauses the thread and **releases the monitor lock on that object**, allowing competing threads to proceed.
