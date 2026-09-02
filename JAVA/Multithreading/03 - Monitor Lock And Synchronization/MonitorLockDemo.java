// Topic 03: Demonstrating Object Monitor Lock & Mutual Exclusion

class BankAccount {
    private int balance = 1000;

    // Synchronized method locks on the 'this' instance monitor
    public synchronized void withdraw(int amount, String user) {
        System.out.println("[" + user + "] Initiating withdrawal of $" + amount + " on " + Thread.currentThread().getName());
        
        if (balance >= amount) {
            System.out.println("[" + user + "] Sufficient balance found. Processing withdrawal...");
            try {
                // Simulate processing time
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            balance -= amount;
            System.out.println("[" + user + "] Withdrawal complete! Remaining Balance: $" + balance);
        } else {
            System.out.println("[" + user + "] INSUFFICIENT FUNDS! Current Balance: $" + balance);
        }
    }

    public int getBalance() {
        return balance;
    }
}

public class MonitorLockDemo {
    public static void main(String[] args) {
        // Shared resource: single BankAccount object with its own monitor lock
        BankAccount sharedAccount = new BankAccount();

        // Two threads attempting to withdraw concurrently
        Thread user1 = new Thread(() -> sharedAccount.withdraw(700, "Alice"), "Alice-Thread");
        Thread user2 = new Thread(() -> sharedAccount.withdraw(700, "Bob"), "Bob-Thread");

        user1.start();
        user2.start();
    }
}
