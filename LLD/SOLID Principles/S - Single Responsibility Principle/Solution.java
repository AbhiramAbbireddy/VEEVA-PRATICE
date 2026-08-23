// GOOD: Each class has a single, focused responsibility

class Marker {
    String name;
    String color;
    int price;
    int year;

    public Marker(String name, String color, int price, int year) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.year = year;
    }
}

// Responsibility 1: Business calculation and state
class Invoice {
    private final Marker marker;
    private final int quantity;
    private int total;

    public Invoice(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }

    public void calculateTotal() {
        this.total = this.marker.price * this.quantity;
        System.out.println("Calculated Total: $" + this.total);
    }

    public Marker getMarker() { return marker; }
    public int getQuantity() { return quantity; }
    public int getTotal() { return total; }
}

// Responsibility 2: Persistence operations
class InvoiceDao {
    private final Invoice invoice;

    public InvoiceDao(Invoice invoice) {
        this.invoice = invoice;
    }

    public void saveToDB() {
        System.out.println("Saving invoice to Database: [Item=" + invoice.getMarker().name + ", Total=$" + invoice.getTotal() + "]");
    }
}

// Responsibility 3: Formatting and Printing
class InvoicePrinter {
    private final Invoice invoice;

    public InvoicePrinter(Invoice invoice) {
        this.invoice = invoice;
    }

    public void print() {
        System.out.println("Printing Invoice for " + invoice.getMarker().name + " x " + invoice.getQuantity() + " = $" + invoice.getTotal());
    }
}

public class Solution {
    public static void main(String[] args) {
        Marker marker = new Marker("Camlin Whiteboard Marker", "Blue", 25, 2024);
        Invoice invoice = new Invoice(marker, 4);

        invoice.calculateTotal();

        InvoiceDao dao = new InvoiceDao(invoice);
        dao.saveToDB();

        InvoicePrinter printer = new InvoicePrinter(invoice);
        printer.print();
    }
}
