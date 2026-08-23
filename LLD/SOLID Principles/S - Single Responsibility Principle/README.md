# Single Responsibility Principle (SRP)

## 1. Definition
> **"A class should have only ONE reason to change."**

In other words, every class should have **one and only one responsibility or job**. If a class handles multiple concerns, changes to one concern can inadvertently break or require recompilation of unrelated concerns, resulting in fragile and bloated code.

---

## 2. Why It Exists (The Problem of God Classes)
When business logic (e.g. invoice calculation), persistence (e.g. database saving), and presentation (e.g. printing or formatting) live inside the same class:
- A change in database schema forces modification of the financial calculation class.
- A change in PDF formatting rules forces re-testing of the database layer.
- Testing becomes tedious because mocking requires setting up unrelated dependencies.

---

## 3. Real-World Analogy
Think of a **Chef in a Restaurant**:
- A Chef's single responsibility is to **cook food**.
- If the Chef also manages the cash register (Accounting), serves the food (Waiter), and repairs plumbing (Maintenance), the kitchen grinds to a halt whenever the plumbing breaks or cash discrepancies occur.
- Specialized roles (Chef, Cashier, Waiter) ensure high efficiency and isolated failures.

---

## 4. Code Examples

### ❌ Violating SRP
```java
public class Invoice {
    private Marker marker;
    private int quantity;
    private int total;

    public Invoice(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }

    // Responsibility 1: Business Logic (Calculation)
    public void calculateTotal() {
        this.total = this.marker.price * this.quantity;
    }

    // Responsibility 2: Persistence (Database)
    public void saveToDB() {
        System.out.println("Saving invoice to DB...");
    }

    // Responsibility 3: Presentation (Printing)
    public void printInvoice() {
        System.out.println("Printing Invoice...");
    }
}
```
**Why this violates SRP:** 
The `Invoice` class has **3 reasons to change**:
1. Tax/pricing calculation logic changes.
2. Database technology/schema changes (e.g., migrating from SQL to MongoDB).
3. Output format changes (e.g., printing to thermal printer, HTML, or PDF).

---

### ✅ Following SRP

Each responsibility is segregated into its own cohesive class:

```java
// Responsibility 1: Pure business state & calculation
public class Invoice {
    private Marker marker;
    private int quantity;
    private int total;

    public Invoice(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }

    public void calculateTotal() {
        this.total = this.marker.price * this.quantity;
    }

    public int getTotal() { return total; }
    public Marker getMarker() { return marker; }
    public int getQuantity() { return quantity; }
}

// Responsibility 2: Persistence only
public class InvoiceDao {
    private Invoice invoice;

    public InvoiceDao(Invoice invoice) {
        this.invoice = invoice;
    }

    public void saveToDB() {
        System.out.println("Saving invoice to DB: Total = " + invoice.getTotal());
    }
}

// Responsibility 3: Presentation only
public class InvoicePrinter {
    private Invoice invoice;

    public InvoicePrinter(Invoice invoice) {
        this.invoice = invoice;
    }

    public void print() {
        System.out.println("Printing Invoice for " + invoice.getMarker().name + " x " + invoice.getQuantity());
    }
}
```

---

## 5. Benefits of SRP
1. **High Cohesion:** Each class is small, focused, and understandable.
2. **Independent Testability:** Can unit-test invoice pricing without mocking database connections or printer streams.
3. **Reusability:** `InvoicePrinter` can be reused for domestic, international, or digital invoices.
4. **Fewer Merge Conflicts:** Database developers and UI/Report developers edit separate files.

---

## 6. Drawbacks & Nuances
- **Class Proliferation:** Over-applying SRP can lead to an explosion of tiny, fragmented classes with one-line methods.
- **Rule of Thumb:** A responsibility is an "axis of change". Group things that change together for the same business reason.

---

## 7. Best Practices
- Keep Data Transfer Objects (DTOs) separate from Data Access Objects (DAOs) and Services.
- Watch for classes with method names using disparate domain words (`calculateTax`, `sendEmailAlert`, `writeToS3`).
- Name classes specifically after their role (`InvoiceCalculator`, `InvoiceRepository`, `InvoicePdfExporter`).

---

## 8. Common Interview Questions
> **Q1: Does SRP mean a class should only have one method?**
> **A:** No. A class can have many methods as long as they all serve the **same unified responsibility**. For instance, `InvoiceDao` can have `save()`, `update()`, `delete()`, and `findById()`.

> **Q2: How do you identify an SRP violation during code review?**
> **A:** Look for:
> 1. Classes with hundreds of lines doing disparate tasks (God Classes).
> 2. Mixed imports (e.g. `java.sql.*` and `java.awt.*` inside the same domain model).
> 3. Frequent merge conflicts in the same class from developers working on unrelated tickets.
