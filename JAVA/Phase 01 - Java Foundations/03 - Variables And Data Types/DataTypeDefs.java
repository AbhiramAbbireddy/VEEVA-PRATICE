public class DataTypeDefs {
    // Instance variables get default values
    byte defaultByte;
    int defaultInt;
    double defaultDouble;
    boolean defaultBool;
    char defaultChar;
    String defaultRef;

    public void testLocalVariables() {
        // Local variables MUST be explicitly assigned before use
        int localVal;
        // System.out.println(localVal); // Compile Error: localVal might not have been initialized
        localVal = 42;
        System.out.println("Initialized local: " + localVal);
    }

    public static void main(String[] args) {
        DataTypeDefs demo = new DataTypeDefs();
        System.out.println("Default byte: " + demo.defaultByte);
        System.out.println("Default int: " + demo.defaultInt);
        System.out.println("Default double: " + demo.defaultDouble);
        System.out.println("Default bool: " + demo.defaultBool);
        System.out.println("Default char: [" + demo.defaultChar + "]");
        System.out.println("Default ref: " + demo.defaultRef);

        demo.testLocalVariables();
    }
}
