// BAD: This design violates SRP by cramming Calculation, Database Persistence, and Printing into one class

class BadMarker {
    String name;
    String color;
    int price;
    int year;

    public BadMarker(String name, String color, int price, int year) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.year = year;
    }
}

class BadInvoice {
    private BadMarker marker;
    private int quantity;
    private int total;

    public BadInvoice(BadMarker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }

    // Responsibility 1: Business Logic
    public void calculateTotal() {
        this.total = this.marker.price * this.quantity;
    }

    // Responsibility 2: Database Operations (Tight coupling to DB)
    public void saveToDB() {
        System.out.println("Saving to Database...");
    }

    // Responsibility 3: Printing Operations (Tight coupling to presentation)
    public void printInvoice() {
        System.out.println("Printing Invoice...");
    }
}

public class Violation {
    public static void main(String[] args) {
        BadInvoice invoice = new BadInvoice(new BadMarker("Camlin", "Black", 20, 2024), 5);
        invoice.calculateTotal();
        invoice.saveToDB();
        invoice.printInvoice();
    }
}
