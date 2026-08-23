import java.util.Arrays;

public class ArrayTraps {
    public static void main(String[] args) {
        // 1. Array Aliasing
        int[] original = {10, 20, 30};
        int[] alias = original;
        alias[0] = 999;
        System.out.println("Original[0] after modifying alias: " + original[0]); // 999

        // 2. Printing char[] vs Object cast
        char[] chars = {'H', 'e', 'l', 'l', 'o'};
        System.out.print("Direct char[] print: ");
        System.out.println(chars); // Hello
        System.out.print("Cast to Object print: ");
        System.out.println((Object) chars); // [C@...

        // 3. Recommended printing via utility
        System.out.println("Arrays.toString: " + Arrays.toString(original));
    }
}
