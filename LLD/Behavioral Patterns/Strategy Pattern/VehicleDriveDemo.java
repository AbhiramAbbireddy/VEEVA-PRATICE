// Strategy Design Pattern - Case Study 1: Vehicle Drive Modes

// 1. Strategy Interface
interface DriveStrategy {
    void drive();
}

// 2. Concrete Strategy: Normal Drive
class NormalDrive implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("Driving Capability: Normal [Economical & Smooth]");
    }
}

// 3. Concrete Strategy: Sports Drive
class SportsDrive implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("Driving Capability: Sports [High Torque & Speed]");
    }
}

// 4. Concrete Strategy: Electric Drive
class EVDrive implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("Driving Capability: Electric [Silent & Instant Acceleration]");
    }
}

// 5. Context Base Class (Composition with Strategy)
class Vehicle {
    private DriveStrategy driveStrategy;

    public Vehicle(DriveStrategy driveStrategy) {
        this.driveStrategy = driveStrategy;
    }

    public void setDriveStrategy(DriveStrategy driveStrategy) {
        this.driveStrategy = driveStrategy;
    }

    public void drive() {
        System.out.print(this.getClass().getSimpleName() + " -> ");
        driveStrategy.drive();
    }
}

// Concrete Contexts inheriting the base context behavior
class SportsVehicle extends Vehicle {
    public SportsVehicle(DriveStrategy driveStrategy) {
        super(driveStrategy);
    }
}

class GoodsVehicle extends Vehicle {
    public GoodsVehicle(DriveStrategy driveStrategy) {
        super(driveStrategy);
    }
}

class OffRoadVehicle extends Vehicle {
    public OffRoadVehicle(DriveStrategy driveStrategy) {
        super(driveStrategy);
    }
}

class HybridVehicle extends Vehicle {
    public HybridVehicle(DriveStrategy driveStrategy) {
        super(driveStrategy);
    }
}

// Client Demo
public class VehicleDriveDemo {
    public static void main(String[] args) {
        System.out.println("====== Strategy Pattern: Vehicle Drive Modes ======");

        Vehicle sportsCar = new SportsVehicle(new SportsDrive());
        sportsCar.drive();

        Vehicle truck = new GoodsVehicle(new NormalDrive());
        truck.drive();

        Vehicle offRoader = new OffRoadVehicle(new SportsDrive()); // Reuses SportsDrive without code duplication!
        offRoader.drive();

        Vehicle hybrid = new HybridVehicle(new EVDrive());
        hybrid.drive();

        // Runtime dynamic switching of strategy
        System.out.println("\n--- Switching Hybrid Vehicle to Sports Mode at Runtime ---");
        hybrid.setDriveStrategy(new SportsDrive());
        hybrid.drive();
    }
}
