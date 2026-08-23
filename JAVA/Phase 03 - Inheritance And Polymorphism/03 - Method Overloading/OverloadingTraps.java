import java.util.*;

public class OverloadingTraps {

    // Overloaded methods
    public static void identify(Object obj) {
        System.out.println("Invoked identify(Object)");
    }

    public static void identify(String str) {
        System.out.println("Invoked identify(String)");
    }

    public static void identify(int primitiveInt) {
        System.out.println("Invoked identify(int)");
    }

    public static void identify(Integer boxedInt) {
        System.out.println("Invoked identify(Integer)");
    }

    public static void main(String[] args) {
        // 1. Static reference binding
        Object textAsObject = "Hello World";
        identify(textAsObject); // "Invoked identify(Object)" -> bound statically!

        // 2. Direct string literal
        identify("Hello World"); // "Invoked identify(String)" -> exact match

        // 3. Primitive vs Wrapper overload
        identify(5);               // "Invoked identify(int)"
        identify(Integer.valueOf(5)); // "Invoked identify(Integer)"
    }
}
