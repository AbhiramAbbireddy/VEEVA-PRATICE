// GOOD: Following LSP by separating Bike core capabilities from Engine capabilities

abstract class Bike {
    // All bikes can accelerate and apply brakes
    public abstract void accelerate();
    public abstract void applyBrakes();
}

interface Engine {
    void turnOnEngine();
    void turnOffEngine();
}

class MotorCycle extends Bike implements Engine {
    String company;
    boolean isEngineOn;
    int speed;

    public MotorCycle(String company, int speed) {
        this.company = company;
        this.speed = speed;
    }

    @Override
    public void turnOnEngine() {
        this.isEngineOn = true;
        System.out.println(company + " MotorCycle Engine is ON!");
    }

    @Override
    public void turnOffEngine() {
        this.isEngineOn = false;
        System.out.println(company + " MotorCycle Engine is OFF!");
    }

    @Override
    public void accelerate() {
        this.speed += 10;
        System.out.println(company + " MotorCycle Speed: " + this.speed);
    }

    @Override
    public void applyBrakes() {
        this.speed -= 5;
        System.out.println(company + " MotorCycle Speed: " + this.speed);
    }
}

class Bicycle extends Bike {
    String brand;
    int speed;

    public Bicycle(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
    }

    @Override
    public void accelerate() {
        this.speed += 5;
        System.out.println(brand + " Bicycle Speed: " + this.speed);
    }

    @Override
    public void applyBrakes() {
        this.speed -= 2;
        System.out.println(brand + " Bicycle Speed: " + this.speed);
    }
}

public class BikeSolution {
    public static void main(String[] args) {
        Bike bike1 = new MotorCycle("Hero Honda", 20);
        Bike bike2 = new Bicycle("Hercules", 10);

        // Polymorphic treatment of all Bikes is 100% safe!
        Bike[] fleet = {bike1, bike2};
        for (Bike b : fleet) {
            b.accelerate();
            b.applyBrakes();
        }

        // Engine operations applied specifically to Engine instances
        Engine motorized = (Engine) bike1;
        motorized.turnOnEngine();
        motorized.turnOffEngine();
    }
}
