import java.util.LinkedList;
import java.util.Queue;

// Pattern 2: Bounded Buffer Producer-Consumer using a fixed-capacity Queue
class BoundedSharedBuffer {
    private final Queue<Integer> buffer;
    private final int capacity;

    public BoundedSharedBuffer(int capacity) {
        this.buffer = new LinkedList<>();
        this.capacity = capacity;
    }

    // Producer method: adds item to buffer when space is available
    public synchronized void addItem(int item) throws InterruptedException {
        // While the buffer is full, the producer must wait
        while (buffer.size() == capacity) {
            System.out.println("[Producer] Buffer is full (Capacity: " + capacity + "). Producer thread is waiting...");
            wait();
        }

        buffer.offer(item);
        System.out.println("[Producer] Produced and added item: " + item 
                + " | Current buffer size: " + buffer.size() + "/" + capacity 
                + ". Notifying consumer...");
        
        // Notify the consumer that an item is available
        notify();
    }

    // Consumer method: retrieves item from buffer when items exist
    public synchronized int consumeItem() throws InterruptedException {
        // While the buffer is empty, the consumer must wait
        while (buffer.isEmpty()) {
            System.out.println("[Consumer] Buffer is empty. Consumer thread is waiting for items to be produced...");
            wait();
        }

        int item = buffer.poll();
        System.out.println("[Consumer] Consumed item: " + item 
                + " | Remaining buffer size: " + buffer.size() + "/" + capacity 
                + ". Notifying producer...");
        
        // Notify the producer that space is now available in the buffer
        notify();
        return item;
    }
}

public class BoundedBufferDemo {
    public static void main(String[] args) {
        System.out.println("=== Starting Bounded Buffer Producer-Consumer Demonstration ===");
        
        // Fixed buffer capacity of 3 items
        BoundedSharedBuffer sharedBuffer = new BoundedSharedBuffer(3);

        // Producer thread generating 7 items (0 to 6)
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 7; i++) {
                    sharedBuffer.addItem(i);
                    // Slight delay to observe interleaved execution
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Producer was interrupted: " + e.getMessage());
            }
        }, "Producer-Thread");

        // Consumer thread consuming 7 items
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 7; i++) {
                    sharedBuffer.consumeItem();
                    // Simulate processing time
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Consumer was interrupted: " + e.getMessage());
            }
        }, "Consumer-Thread");

        // Start both threads concurrently
        producer.start();
        consumer.start();
    }
}
