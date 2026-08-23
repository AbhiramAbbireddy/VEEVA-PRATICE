import java.util.ArrayList;
import java.util.List;

// BAD: Violates LSP by throwing exceptions or returning null in subtypes

interface BadBike {
    void turnOnEngine();
    void accelerate();
    void applyBrakes();
}

class BadBicycle implements BadBike {
    @Override
    public void turnOnEngine() {
        // ❌ LSP Violation: Bicycle cannot support turning on engine
        throw new AssertionError("Bicycle has no engine!");
    }

    @Override
    public void accelerate() {
        System.out.println("Pedaling...");
    }

    @Override
    public void applyBrakes() {
        System.out.println("Braking...");
    }
}

class BadVehicle {
    public Boolean hasEngine() { return true; }
}

class BadBicycleVehicle extends BadVehicle {
    @Override
    public Boolean hasEngine() {
        // ❌ LSP Violation: Returning null causes unexpected NPE for caller
        return null;
    }
}

public class ViolationDemo {
    public static void main(String[] args) {
        System.out.println("=== LSP Violation 1: Exception on Subtype ===");
        BadBike bicycle = new BadBicycle();
        try {
            bicycle.turnOnEngine(); // 💥 Throws AssertionError
        } catch (AssertionError e) {
            System.out.println("Caught unexpected error: " + e.getMessage());
        }

        System.out.println("\n=== LSP Violation 2: NullPointerException ===");
        List<BadVehicle> vehicles = new ArrayList<>();
        vehicles.add(new BadBicycleVehicle());
        for (BadVehicle v : vehicles) {
            try {
                System.out.println("Has engine: " + v.hasEngine().toString()); // 💥 NPE!
            } catch (NullPointerException npe) {
                System.out.println("Caught NPE because subtype violated return contract!");
            }
        }
    }
}
