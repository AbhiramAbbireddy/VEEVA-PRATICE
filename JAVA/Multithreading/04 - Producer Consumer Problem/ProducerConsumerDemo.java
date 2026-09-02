// Topic 04: Producer-Consumer Implementation Using wait() and notifyAll()

class SharedResource {
    private boolean itemAvailable = false;

    // Producer method: marks item available and notifies waiting consumers
    public synchronized void addItem() {
        itemAvailable = true;
        System.out.println("[Producer] Item added by " + Thread.currentThread().getName() + ". Notifying waiting threads...");
        // Wake up all threads in this object's wait set
        notifyAll();
    }

    // Consumer method: waits while item is unavailable, then consumes it
    public synchronized void consumeItem() {
        System.out.println("[Consumer] Entered consumeItem on " + Thread.currentThread().getName());

        // Always check condition inside a while loop to guard against spurious wakeups
        while (!itemAvailable) {
            try {
                System.out.println("[Consumer] Item is unavailable. " + Thread.currentThread().getName() + " releasing lock and waiting...");
                // wait() releases the monitor lock on 'this' and places the thread into WAITING state
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted while waiting: " + e.getMessage());
                return;
            }
        }

        System.out.println("[Consumer] Consuming item on " + Thread.currentThread().getName() + "!");
        itemAvailable = false; // Reset state for subsequent production
    }
}

class Producer implements Runnable {
    private final SharedResource shared;

    public Producer(SharedResource shared) {
        this.shared = shared;
    }

    @Override
    public void run() {
        System.out.println("Producer task started on: " + Thread.currentThread().getName());
        try {
            // Simulate 3 seconds of data generation work
            System.out.println("Producer generating data (simulating work)...");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        shared.addItem();
    }
}

class Consumer implements Runnable {
    private final SharedResource shared;

    public Consumer(SharedResource shared) {
        this.shared = shared;
    }

    @Override
    public void run() {
        System.out.println("Consumer task started on: " + Thread.currentThread().getName());
        shared.consumeItem();
    }
}

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        System.out.println("=== Starting Producer-Consumer Demonstration ===");

        // Single shared resource instance providing intrinsic monitor lock
        SharedResource shared = new SharedResource();

        // Separate threads for producer and consumer
        Thread producer = new Thread(new Producer(shared), "Producer-Thread");
        Thread consumer = new Thread(new Consumer(shared), "Consumer-Thread");

        // Start both threads concurrently
        consumer.start(); // Starts waiting because itemAvailable is false
        producer.start(); // Produces item and notifies consumer

        System.out.println("Main thread finished launching workers: " + Thread.currentThread().getName());
    }
}
