class Shape {
    public void draw() {
        System.out.println("Drawing generic shape.");
    }
}

class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Circle with radius and curves.");
    }
}

class Square extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Square with 4 equal sides.");
    }
}

public class DynamicDispatchDemo {

    public static void renderShape(Shape shape) {
        // Dynamic Method Dispatch executes the specific child method
        shape.draw();
    }

    public static void main(String[] args) {
        Shape s1 = new Circle();
        Shape s2 = new Square();

        renderShape(s1); // Drawing Circle...
        renderShape(s2); // Drawing Square...
    }
}
