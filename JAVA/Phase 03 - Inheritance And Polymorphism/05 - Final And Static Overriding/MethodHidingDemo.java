class Parent {
    public static void printCategory() {
        System.out.println("Static Parent Category");
    }

    public void printInstance() {
        System.out.println("Instance Parent Method");
    }
}

class Child extends Parent {
    // Method Hiding (NOT overriding)
    public static void printCategory() {
        System.out.println("Static Child Category");
    }

    // Method Overriding
    @Override
    public void printInstance() {
        System.out.println("Instance Child Method");
    }
}

public class MethodHidingDemo {
    public static void main(String[] args) {
        Parent ref = new Child();

        // 1. Static call resolved by Reference type (Parent)
        ref.printCategory(); // "Static Parent Category"

        // 2. Instance call resolved by Heap Object type (Child)
        ref.printInstance(); // "Instance Child Method"

        // 3. Proper static invocation style via Class name
        Parent.printCategory(); // "Static Parent Category"
        Child.printCategory();  // "Static Child Category"
    }
}
