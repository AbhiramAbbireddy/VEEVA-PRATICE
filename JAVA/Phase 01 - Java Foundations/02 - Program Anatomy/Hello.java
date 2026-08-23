public class Hello {
    public static void main(String[] args) {
        // Basic console print
        System.out.println("Hello, Core Java Mastery!");

        // Inspecting command-line arguments
        System.out.println("Argument count: " + args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.println("args[" + i + "] = " + args[i]);
        }
    }
}
