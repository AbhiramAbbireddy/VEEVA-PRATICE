import java.util.concurrent.Semaphore;

class ConnectionPoolResource {
    // Allows a maximum of 2 concurrent connections
    private final Semaphore semaphore = new Semaphore(2);

    public void accessDatabaseConnection(String clientName) {
        try {
            System.out.println("[" + clientName + "] Attempting to acquire a database connection permit...");
            semaphore.acquire();
            System.out.println("[" + clientName + "] Connection permit ACQUIRED! Available permits: " + semaphore.availablePermits());

            // Simulate executing database query
            Thread.sleep(3000);

            System.out.println("[" + clientName + "] Finished database queries. Releasing permit...");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
            System.out.println("[" + clientName + "] Permit RELEASED! Available permits: " + semaphore.availablePermits());
        }
    }
}

public class SemaphoreDemo {
    public static void main(String[] args) {
        System.out.println("=== Starting Semaphore (Rate Limiting) Demonstration ===");
        ConnectionPoolResource pool = new ConnectionPoolResource();

        // 4 concurrent clients attempting to access 2 available connections
        Thread client1 = new Thread(() -> pool.accessDatabaseConnection("Client-1"), "Thread-1");
        Thread client2 = new Thread(() -> pool.accessDatabaseConnection("Client-2"), "Thread-2");
        Thread client3 = new Thread(() -> pool.accessDatabaseConnection("Client-3"), "Thread-3");
        Thread client4 = new Thread(() -> pool.accessDatabaseConnection("Client-4"), "Thread-4");

        client1.start();
        client2.start();
        client3.start();
        client4.start();
    }
}
