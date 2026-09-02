// Topic 02: Thread Creation via Runnable Interface (Recommended)

class WorkerTask implements Runnable {
    private final String taskName;

    public WorkerTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("Executing task [" + taskName + "] on Thread: " + Thread.currentThread().getName());
        try {
            // Simulate work
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Task interrupted: " + e.getMessage());
        }
        System.out.println("Finished task [" + taskName + "] on Thread: " + Thread.currentThread().getName());
    }
}

public class RunnableDemo {
    public static void main(String[] args) {
        System.out.println("Starting inside main method: " + Thread.currentThread().getName());

        // Step 1: Create Runnable task instance
        WorkerTask task1 = new WorkerTask("Database-Sync");
        WorkerTask task2 = new WorkerTask("Email-Notification");

        // Step 2: Pass task to Thread constructor
        Thread thread1 = new Thread(task1, "Worker-Thread-1");
        Thread thread2 = new Thread(task2, "Worker-Thread-2");

        // Step 3: Start threads
        thread1.start();
        thread2.start();

        System.out.println("Finish main method: " + Thread.currentThread().getName());
    }
}
