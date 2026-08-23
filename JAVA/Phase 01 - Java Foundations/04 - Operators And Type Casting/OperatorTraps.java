public class OperatorTraps {
    public static void main(String[] args) {
        // 1. Division Truncation
        int a = 7;
        double y1 = a / 2;       // 3.0
        double y2 = (double) a / 2; // 3.5
        System.out.println("y1 (truncated): " + y1 + ", y2 (accurate): " + y2);

        // 2. Pre vs Post increment
        int i = 5;
        System.out.println("i++: " + (i++)); // 5
        System.out.println("++i: " + (++i)); // 7
        System.out.println("i: " + i);       // 7

        // 3. Expression Promotion & Compound Assignment
        byte b = 1;
        // b = b + 1; // Compile error
        b += 1; // implicit cast to (byte)
        System.out.println("b after += 1: " + b);

        // 4. Short-circuit null safety
        String str = null;
        if (str != null && str.length() > 0) {
            System.out.println("Non-empty");
        } else {
            System.out.println("Short-circuit prevented NullPointerException safely.");
        }
    }
}
