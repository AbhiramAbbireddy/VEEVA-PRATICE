interface Alpha {
    default void log() {
        System.out.println("Alpha default log");
    }
}

interface Beta {
    default void log() {
        System.out.println("Beta default log");
    }
}

// Rule 3 in action: Resolving unrelated conflicting defaults manually
public class DiamondResolutionDemo implements Alpha, Beta {

    @Override
    public void log() {
        // Explicitly choosing Alpha's default implementation
        Alpha.super.log();
        System.out.println("DiamondResolutionDemo custom log extension.");
    }

    public static void main(String[] args) {
        DiamondResolutionDemo demo = new DiamondResolutionDemo();
        demo.log();
    }
}
