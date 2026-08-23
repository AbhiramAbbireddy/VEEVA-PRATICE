// Noninstantiable utility class (Effective Java Item 4)
public class MathUtils {

    // Suppress default constructor for noninstantiability
    private MathUtils() {
        throw new AssertionError("Instantiation of utility class MathUtils is strictly forbidden.");
    }

    public static int max(int a, int b) {
        return (a >= b) ? a : b;
    }

    public static int min(int a, int b) {
        return (a <= b) ? a : b;
    }

    public static boolean isEven(int n) {
        return (n % 2) == 0;
    }
}
