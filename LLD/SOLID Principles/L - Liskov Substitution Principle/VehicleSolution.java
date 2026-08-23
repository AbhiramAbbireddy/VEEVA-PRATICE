import java.util.ArrayList;
import java.util.List;

// GOOD: Vehicle hierarchy properly structured so subtypes never violate contracts

class Vehicle {
    public Integer getNumberOfWheels() {
        return 2; // Default baseline wheels
    }
}

class Bicycle extends Vehicle {
    // Inherits getNumberOfWheels() -> 2
}

class EngineVehicle extends Vehicle {
    public Boolean hasEngine() {
        return true;
    }
}

class MotorCycle extends EngineVehicle {
    // 2 wheels, hasEngine -> true
}

class Car extends EngineVehicle {
    @Override
    public Integer getNumberOfWheels() {
        return 4;
    }
}

public class VehicleSolution {
    public static void main(String[] args) {
        List<Vehicle> allVehicles = new ArrayList<>();
        allVehicles.add(new MotorCycle());
        allVehicles.add(new Car());
        allVehicles.add(new Bicycle());

        System.out.println("--- All Vehicles Wheel Count ---");
        for (Vehicle v : allVehicles) {
            System.out.println(v.getClass().getSimpleName() + " wheels: " + v.getNumberOfWheels());
        }

        List<EngineVehicle> motorizedVehicles = new ArrayList<>();
        motorizedVehicles.add(new MotorCycle());
        motorizedVehicles.add(new Car());
        // motorizedVehicles.add(new Bicycle()); // Compile error: Type safety enforced!

        System.out.println("\n--- Motorized Vehicles Engine Check ---");
        for (EngineVehicle ev : motorizedVehicles) {
            System.out.println(ev.getClass().getSimpleName() + " has engine: " + ev.hasEngine());
        }
    }
}
