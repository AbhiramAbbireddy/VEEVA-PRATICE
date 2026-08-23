// BAD: Every new persistence mechanism forces editing this existing, tested class!

class BadInvoiceDao {
    public void saveToDB() {
        System.out.println("Saving to SQL Database...");
    }

    // Violation: Modifying existing class to add file saving
    public void saveToFile() {
        System.out.println("Saving to File...");
    }

    // Violation: Modifying existing class again to add MongoDB
    public void saveToMongoDB() {
        System.out.println("Saving to MongoDB...");
    }
}

public class Violation {
    public static void main(String[] args) {
        BadInvoiceDao dao = new BadInvoiceDao();
        dao.saveToDB();
        dao.saveToFile();
        dao.saveToMongoDB();
    }
}
