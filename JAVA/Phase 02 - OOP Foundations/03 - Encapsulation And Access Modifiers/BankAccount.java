public class BankAccount {
    // Encapsulated private state
    private double balance;
    private final String accountNumber;

    public BankAccount(String accountNumber, double initialDeposit) {
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be empty.");
        }
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative.");
        }
        this.accountNumber = accountNumber;
        this.balance = initialDeposit;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be strictly positive.");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be strictly positive.");
        }
        if (amount > this.balance) {
            throw new IllegalStateException("Insufficient funds for withdrawal.");
        }
        this.balance -= amount;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }
}
