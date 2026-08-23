public class StaticDemo {
    // Static class-level counter
    public static int totalInstances = 0;

    // Instance-level field
    public final int instanceId;

    static {
        System.out.println("[Class Load] StaticDemo static block running once.");
    }

    public StaticDemo() {
        totalInstances++;
        this.instanceId = totalInstances;
    }

    public static void displayTotalCount() {
        // System.out.println(this.instanceId); // Compile Error: cannot use 'this' or instance fields in static context
        System.out.println("Total instances created so far: " + totalInstances);
    }

    public static void main(String[] args) {
        System.out.println("Main method started.");
        StaticDemo obj1 = new StaticDemo();
        StaticDemo obj2 = new StaticDemo();
        StaticDemo obj3 = new StaticDemo();

        System.out.println("obj1 ID: " + obj1.instanceId);
        System.out.println("obj2 ID: " + obj2.instanceId);
        System.out.println("obj3 ID: " + obj3.instanceId);

        StaticDemo.displayTotalCount();
    }
}
