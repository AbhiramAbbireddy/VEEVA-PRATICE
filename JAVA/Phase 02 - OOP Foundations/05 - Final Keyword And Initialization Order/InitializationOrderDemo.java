public class InitializationOrderDemo {
    static int staticField = 100;

    static {
        System.out.println("1. Static block executed. staticField=" + staticField);
    }

    int instanceField = 200;

    {
        System.out.println("2. Instance block executed. instanceField=" + instanceField);
    }

    public InitializationOrderDemo() {
        System.out.println("3. Constructor executed.");
    }

    public static void main(String[] args) {
        System.out.println("--- Main method starts ---");
        System.out.println("Creating Object 1:");
        new InitializationOrderDemo();

        System.out.println("Creating Object 2:");
        new InitializationOrderDemo();
    }
}
