// BAD: One fat interface forcing implementers to write error-throwing dummy methods

interface BadRestaurantEmployee {
    void prepareFood();
    void decideMenu();
    void serveFoodAndDrinks();
    void takeOrder();
    void cleanTheKitchen();
}

class BadWaiter implements BadRestaurantEmployee {
    @Override
    public void takeOrder() {
        System.out.println("Taking order...");
    }

    @Override
    public void serveFoodAndDrinks() {
        System.out.println("Serving food...");
    }

    @Override
    public void prepareFood() {
        // ❌ Forced to implement irrelevant method
        throw new AssertionError("Waiter cannot prepare food!");
    }

    @Override
    public void decideMenu() {
        // ❌ Forced to implement irrelevant method
        throw new AssertionError("Waiter cannot decide menu!");
    }

    @Override
    public void cleanTheKitchen() {
        // ❌ Forced to implement irrelevant method
        throw new AssertionError("Waiter cannot clean kitchen!");
    }
}

public class Violation {
    public static void main(String[] args) {
        BadWaiter waiter = new BadWaiter();
        waiter.takeOrder();
        waiter.serveFoodAndDrinks();

        try {
            waiter.prepareFood();
        } catch (AssertionError e) {
            System.out.println("Caught error due to fat interface: " + e.getMessage());
        }
    }
}
