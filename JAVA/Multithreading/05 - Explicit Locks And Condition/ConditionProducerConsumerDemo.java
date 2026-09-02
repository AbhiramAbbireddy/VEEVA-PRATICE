import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Demonstrating Condition interface: await() = wait(), signal() = notify()
class BoundedBufferWithCondition {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity;

    private final Lock lock = new ReentrantLock();
    // Two separate condition wait queues on the same lock!
    private final Condition notFull = lock.newCondition();  // Producer queue
    private final Condition notEmpty = lock.newCondition(); // Consumer queue

    public BoundedBufferWithCondition(int capacity) {
        this.capacity = capacity;
    }

    public void produce(int item) throws InterruptedException {
        lock.lock();
        try {
            // While buffer is full, wait on the 'notFull' condition
            while (buffer.size() == capacity) {
                System.out.println("[Producer] Buffer is full (Capacity: " + capacity + "). Awaiting 'notFull' signal...");
                // await() releases the explicit lock and sleeps
                notFull.await();
            }

            buffer.offer(item);
            System.out.println("[Producer] Produced item: " + item + " | Buffer size: " + buffer.size() + "/" + capacity);

            // Signal only waiting consumers!
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int consume() throws InterruptedException {
        lock.lock();
        try {
            // While buffer is empty, wait on the 'notEmpty' condition
            while (buffer.isEmpty()) {
                System.out.println("[Consumer] Buffer is empty. Awaiting 'notEmpty' signal...");
                // await() releases the explicit lock and sleeps
                notEmpty.await();
            }

            int item = buffer.poll();
            System.out.println("[Consumer] Consumed item: " + item + " | Buffer size: " + buffer.size() + "/" + capacity);

            // Signal only waiting producers!
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }
}

public class ConditionProducerConsumerDemo {
    public static void main(String[] args) {
        System.out.println("=== Starting Condition (await / signal) Demonstration ===");
        BoundedBufferWithCondition sharedBuffer = new BoundedBufferWithCondition(3);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    sharedBuffer.produce(i);
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer-Thread");

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    sharedBuffer.consume();
                    Thread.sleep(600);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer-Thread");

        consumer.start();
        producer.start();
    }
}
