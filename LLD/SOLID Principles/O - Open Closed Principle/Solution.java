// GOOD: Following OCP using interface abstraction and polymorphism

class Marker {
    String name;
    int price;

    public Marker(String name, int price) {
        this.name = name;
        this.price = price;
    }
}

class Invoice {
    private final Marker marker;
    private final int quantity;

    public Invoice(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }

    public int calculateTotal() {
        return this.marker.price * this.quantity;
    }

    public String getItemName() { return marker.name; }
}

// 1. The Extensible Abstraction
interface InvoiceDao {
    void save(Invoice invoice);
}

// 2. Concrete Extension: SQL Database
class DatabaseInvoiceDao implements InvoiceDao {
    @Override
    public void save(Invoice invoice) {
        System.out.println("Saving invoice for " + invoice.getItemName() + " to SQL Database (Total: $" + invoice.calculateTotal() + ")");
    }
}

// 3. Concrete Extension: File Storage
class FileInvoiceDao implements InvoiceDao {
    @Override
    public void save(Invoice invoice) {
        System.out.println("Saving invoice for " + invoice.getItemName() + " to Local File (Total: $" + invoice.calculateTotal() + ")");
    }
}

// 4. Concrete Extension: Cloud / MongoDB Storage
class MongoDbInvoiceDao implements InvoiceDao {
    @Override
    public void save(Invoice invoice) {
        System.out.println("Saving invoice for " + invoice.getItemName() + " to MongoDB Cluster (Total: $" + invoice.calculateTotal() + ")");
    }
}

public class Solution {
    public static void main(String[] args) {
        Invoice invoice = new Invoice(new Marker("Whiteboard Marker", 30), 10);

        // System is OPEN for new persistence types without modifying existing DAOs
        InvoiceDao dbDao = new DatabaseInvoiceDao();
        dbDao.save(invoice);

        InvoiceDao fileDao = new FileInvoiceDao();
        fileDao.save(invoice);

        InvoiceDao mongoDao = new MongoDbInvoiceDao();
        mongoDao.save(invoice);
    }
}
