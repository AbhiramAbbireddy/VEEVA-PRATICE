// GOOD: Multiple focused, role-specific interfaces following ISP

interface ChefTasks {
    void prepareFood();
    void decideMenu();
}

interface WaiterTasks {
    void serveFoodAndDrinks();
    void takeOrder();
}

interface MaintenanceTasks {
    void cleanTheKitchen();
    void restockGroceries();
}

// Chef implements only Chef tasks
class Chef implements ChefTasks {
    @Override
    public void prepareFood() {
        System.out.println("Chef is preparing gourmet Italian pasta.");
    }

    @Override
    public void decideMenu() {
        System.out.println("Chef is designing the evening dinner menu.");
    }
}

// Waiter implements only Waiter tasks
class Waiter implements WaiterTasks {
    @Override
    public void takeOrder() {
        System.out.println("Waiter is taking customer order at Table 4.");
    }

    @Override
    public void serveFoodAndDrinks() {
        System.out.println("Waiter is serving wine and pasta.");
    }
}

// All-Rounder Staff can implement multiple interfaces without bloated single interface
class RestaurantManager implements ChefTasks, WaiterTasks {
    @Override
    public void prepareFood() {
        System.out.println("Manager stepping in to help kitchen.");
    }
    @Override
    public void decideMenu() {
        System.out.println("Manager reviewing seasonal menu.");
    }
    @Override
    public void serveFoodAndDrinks() {
        System.out.println("Manager seating and serving VIP guests.");
    }
    @Override
    public void takeOrder() {
        System.out.println("Manager taking VIP order.");
    }
}

public class Solution {
    public static void main(String[] args) {
        Chef chef = new Chef();
        Waiter waiter = new Waiter();

        System.out.println("=== Kitchen Operations ===");
        chef.prepareFood();
        chef.decideMenu();

        System.out.println("\n=== Dining Operations ===");
        waiter.takeOrder();
        waiter.serveFoodAndDrinks();
    }
}
