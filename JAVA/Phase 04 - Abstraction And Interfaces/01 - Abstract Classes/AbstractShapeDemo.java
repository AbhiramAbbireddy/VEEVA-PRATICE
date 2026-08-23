abstract class GraphicObject {
    int x, y;

    // Abstract constructor initializes inherited coordinate state
    public GraphicObject(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("GraphicObject base constructor executed.");
    }

    public void moveTo(int newX, int newY) {
        this.x = newX;
        this.y = newY;
        System.out.println("Moved to (" + x + ", " + y + ")");
    }

    // Abstract methods: child MUST provide implementation
    public abstract void draw();
    public abstract double calculateArea();
}

class Rectangle extends GraphicObject {
    double width, height;

    public Rectangle(int x, int y, double width, double height) {
        super(x, y); // Hand coordinates to abstract superclass
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Rectangle of area: " + calculateArea());
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}

public class AbstractShapeDemo {
    public static void main(String[] args) {
        // GraphicObject g = new GraphicObject(0, 0); // Compile Error: cannot instantiate abstract class
        GraphicObject rect = new Rectangle(10, 20, 5.0, 4.0);
        rect.draw();
        rect.moveTo(30, 40);
    }
}
