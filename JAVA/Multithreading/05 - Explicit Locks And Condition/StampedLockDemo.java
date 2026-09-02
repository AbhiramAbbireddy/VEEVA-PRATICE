import java.util.concurrent.locks.StampedLock;

class StampedResource {
    private int data = 10;
    private final StampedLock lock = new StampedLock();

    // 1. Exclusive Write Mode
    public void writeData(int newValue) {
        long stamp = lock.writeLock();
        try {
            System.out.println("[StampedLock - Write] Lock acquired by: " + Thread.currentThread().getName() + " (Stamp: " + stamp + ")");
            this.data = newValue;
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlockWrite(stamp);
            System.out.println("[StampedLock - Write] Lock released by: " + Thread.currentThread().getName());
        }
    }

    // 2. Pessimistic Read Mode
    public int readDataPessimistic() {
        long stamp = lock.readLock();
        try {
            System.out.println("[StampedLock - Read] Lock acquired by: " + Thread.currentThread().getName() + " (Stamp: " + stamp + ")");
            Thread.sleep(1500);
            return this.data;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return this.data;
        } finally {
            lock.unlockRead(stamp);
            System.out.println("[StampedLock - Read] Lock released by: " + Thread.currentThread().getName());
        }
    }

    // 3. Optimistic Read Mode (Zero lock overhead!)
    public void readDataOptimistic() {
        long stamp = lock.tryOptimisticRead();
        System.out.println("[StampedLock - Optimistic Read] Stamp obtained: " + stamp + " by " + Thread.currentThread().getName());

        int currentData = this.data;
        try {
            // Simulate processing time where a writer might interfere
            Thread.sleep(1000);

            // Validate whether any write occurred while reading
            if (lock.validate(stamp)) {
                System.out.println("[StampedLock - Optimistic Read] Validation SUCCESSFUL! Consistent value read: " + currentData);
            } else {
                System.out.println("[StampedLock - Optimistic Read] Validation FAILED (Write occurred). Rolling back & falling back to pessimistic read...");
                // Fallback to pessimistic read lock
                currentData = readDataPessimistic();
                System.out.println("[StampedLock - Optimistic Read] Fallback read value: " + currentData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class StampedLockDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Starting StampedLock Demonstration ===");
        StampedResource resource = new StampedResource();

        // Testing Optimistic Read with concurrent writer
        Thread reader = new Thread(resource::readDataOptimistic, "Optimistic-Reader");
        Thread writer = new Thread(() -> resource.writeData(99), "Writer-Thread");

        reader.start();
        Thread.sleep(200); // Give reader a head start
        writer.start();
    }
}
