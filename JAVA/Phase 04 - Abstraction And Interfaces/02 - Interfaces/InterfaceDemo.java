interface Engine {
    int DEFAULT_HORSEPOWER = 150; // implicitly public static final

    void start(); // implicitly public abstract
    void stop();
}

interface Drivable {
    void accelerate(int speed);
}

// Implementing multiple interfaces cleanly
class SportsCar implements Engine, Drivable {

    @Override
    public void start() { // MUST be public
        System.out.println("SportsCar Engine started with " + DEFAULT_HORSEPOWER + " HP.");
    }

    @Override
    public void stop() {
        System.out.println("SportsCar Engine stopped.");
    }

    @Override
    public void accelerate(int speed) {
        System.out.println("Accelerating to " + speed + " km/h.");
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        SportsCar car = new SportsCar();
        car.start();
        car.accelerate(100);
        car.stop();
    }
}
