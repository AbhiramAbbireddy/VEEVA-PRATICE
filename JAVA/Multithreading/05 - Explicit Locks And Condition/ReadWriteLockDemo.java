import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReadWriteResource {
    private boolean isAvailable = false;
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    // Exclusive Write Lock: Only 1 thread can write at a time
    public void produce() {
        rwLock.writeLock().lock();
        try {
            System.out.println("[WriteLock] Lock acquired by: " + Thread.currentThread().getName());
            isAvailable = true;
            // Simulate write operation
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            rwLock.writeLock().unlock();
            System.out.println("[WriteLock] Lock released by: " + Thread.currentThread().getName());
        }
    }

    // Shared Read Lock: Multiple threads can read concurrently
    public void consume() {
        rwLock.readLock().lock();
        try {
            System.out.println("[ReadLock] Lock acquired by: " + Thread.currentThread().getName() + " | Data available: " + isAvailable);
            // Simulate read operation
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            rwLock.readLock().unlock();
            System.out.println("[ReadLock] Lock released by: " + Thread.currentThread().getName());
        }
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        System.out.println("=== Starting ReadWriteLock Demonstration ===");
        ReadWriteResource resource = new ReadWriteResource();

        // 1 Writer Thread
        Thread writer = new Thread(resource::produce, "Writer-1");

        // 3 Reader Threads (will read concurrently once write completes)
        Thread reader1 = new Thread(resource::consume, "Reader-1");
        Thread reader2 = new Thread(resource::consume, "Reader-2");
        Thread reader3 = new Thread(resource::consume, "Reader-3");

        writer.start();
        reader1.start();
        reader2.start();
        reader3.start();
    }
}
