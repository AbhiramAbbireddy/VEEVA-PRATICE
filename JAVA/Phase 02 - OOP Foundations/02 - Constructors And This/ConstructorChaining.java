public class ConstructorChaining {
    int id;
    String name;
    double balance;

    // 1. Default/no-arg constructor delegating via this()
    public ConstructorChaining() {
        this(0, "Guest", 0.0); // Must be the FIRST statement
        System.out.println("Default constructor completed.");
    }

    // 2. Overloaded constructor with 2 arguments
    public ConstructorChaining(int id, String name) {
        this(id, name, 100.0);
    }

    // 3. Foundational parameterized constructor
    public ConstructorChaining(int id, String name, double balance) {
        this.id = id;       // 'this' resolves parameter shadowing
        this.name = name;
        this.balance = balance;
    }

    public static void main(String[] args) {
        ConstructorChaining user = new ConstructorChaining();
        System.out.println("User initialized: " + user.name + ", Balance: " + user.balance);
    }
}
