// Topic 02: Thread Creation via Thread Class Subclassing

class CustomThreadWorker extends Thread {
    public CustomThreadWorker(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        System.out.println("Executing thread subclass: " + Thread.currentThread().getName());
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted: " + e.getMessage());
        }
        System.out.println("Execution finished: " + Thread.currentThread().getName());
    }
}

public class ThreadClassDemo {
    public static void main(String[] args) {
        System.out.println("Starting main method: " + Thread.currentThread().getName());

        // Step 1: Instantiate Thread subclass
        CustomThreadWorker workerThread = new CustomThreadWorker("Custom-Worker-Thread");

        // Step 2: Start the thread
        workerThread.start();

        System.out.println("Finish main method: " + Thread.currentThread().getName());
    }
}
