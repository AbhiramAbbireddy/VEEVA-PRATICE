public class ControlFlowDemo {
    public static void main(String[] args) {
        // 1. Stray semicolon demonstration
        System.out.println("--- Stray Semicolon Loop ---");
        for (int i = 0; i < 3; i++);
        {
            System.out.println("This prints once because the for-loop had an empty body.");
        }

        // 2. Nested loop with break
        System.out.println("--- Nested Loop with Break ---");
        int count = 0;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (j == 2) break; // kills only the inner j-loop
                count++;
            }
        }
        System.out.println("Final count: " + count); // 3 (1 from each i iteration)

        // 3. Fall-through demo
        System.out.println("--- Switch Fall-Through ---");
        int val = 2;
        switch (val) {
            case 1: System.out.println("One");
            case 2: System.out.println("Two");
            case 3: System.out.println("Three");
            default: System.out.println("Default");
        }
    }
}
