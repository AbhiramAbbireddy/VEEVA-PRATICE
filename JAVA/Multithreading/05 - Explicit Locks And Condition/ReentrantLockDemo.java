import java.util.concurrent.locks.ReentrantLock;

class ReentrantResource {
    private boolean isAvailable = false;
    private final ReentrantLock lock = new ReentrantLock();

    public void produce() {
        // Step 1: Acquire lock explicitly
        lock.lock();
        try {
            System.out.println("[ReentrantLock] Lock acquired by: " + Thread.currentThread().getName());
            isAvailable = true;
            // Simulate processing work
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread was interrupted: " + e.getMessage());
        } finally {
            // Step 2: Always unlock inside finally block
            lock.unlock();
            System.out.println("[ReentrantLock] Lock released by: " + Thread.currentThread().getName());
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) {
        System.out.println("=== Starting ReentrantLock Demonstration ===");
        ReentrantResource resource = new ReentrantResource();

        Thread th1 = new Thread(resource::produce, "Worker-Thread-1");
        Thread th2 = new Thread(resource::produce, "Worker-Thread-2");

        th1.start();
        th2.start();
    }
}
