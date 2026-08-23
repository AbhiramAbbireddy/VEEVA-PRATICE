# Open / Closed Principle (OCP)

## 1. Definition
> **"Software entities (classes, modules, functions) should be OPEN for extension, but CLOSED for modification."**

- **Open for Extension:** New behaviors, algorithms, or integrations can be added seamlessly.
- **Closed for Modification:** The existing, tested, production-grade code remains untouched and unmodified.

---

## 2. Why It Exists (The Risk of Modifying Tested Code)
When a feature is already tested and running stably in production, modifying its core source files to support a new requirement:
- Introduces regression risks (breaking working features).
- Demands extensive regression testing of the entire class.
- Violates modularity and leads to sprawling switch/if-else statements.

---

## 3. Real-World Analogy
Think of a **Smartphone and its USB-C Port / Apps**:
- Your smartphone's operating system and hardware are **closed for modification** (you don't open the phone casing and solder new chips every time you want a new feature).
- But it is **open for extension** via standard interfaces: you can plug in a flash drive, an external microphone, or headphones, and install new apps via the app store.

---

## 4. Code Examples

### ❌ Violating OCP
```java
public class InvoiceDao {
    Invoice invoice;

    public InvoiceDao(Invoice invoice) {
        this.invoice = invoice;
    }

    public void saveToDB() {
        System.out.println("Saving to SQL Database...");
    }

    // ❌ VIOLATION: To add file saving, we had to modify the existing InvoiceDao class!
    public void saveToFile() {
        System.out.println("Saving to File...");
    }

    // If we later need saveToMongoDB() or saveToS3(), we must keep modifying this class!
}
```

---

### ✅ Following OCP (Using Interfaces & Polymorphism)

```java
// Define the extensible contract
public interface InvoiceDao {
    void save(Invoice invoice);
}

// Extension 1: Database persistence
public class DatabaseInvoiceDao implements InvoiceDao {
    @Override
    public void save(Invoice invoice) {
        System.out.println("Saving invoice to SQL Database...");
    }
}

// Extension 2: File persistence (Added WITHOUT modifying DatabaseInvoiceDao!)
public class FileInvoiceDao implements InvoiceDao {
    @Override
    public void save(Invoice invoice) {
        System.out.println("Saving invoice to File system...");
    }
}

// Extension 3: MongoDB persistence (Zero changes to existing classes)
public class MongoDbInvoiceDao implements InvoiceDao {
    @Override
    public void save(Invoice invoice) {
        System.out.println("Saving invoice to MongoDB NoSQL store...");
    }
}
```

---

## 5. Benefits of OCP
1. **Zero Regression Risk:** Existing implementations are untouched.
2. **Pluggable Architecture:** New features are added as new classes (plugins).
3. **Decoupled Testing:** Unit test only the newly added concrete class.
4. **Enhanced Maintainability:** Promotes interface-driven design.

---

## 6. Drawbacks & Nuances
- **Premature Abstraction:** Don't create interfaces for parts of the system that will never change. Apply OCP when variability is expected or after observing repeated changes.

---

## 7. Best Practices
- Program to interfaces or abstract classes rather than concrete types.
- Use Design Patterns that promote OCP: **Strategy Pattern**, **Factory Pattern**, **Decorator Pattern**, **Observer Pattern**.

---

## 8. Common Interview Questions
> **Q1: How does OCP relate to Polymorphism?**
> **A:** Polymorphism is the primary mechanism to achieve OCP. By invoking methods through an interface reference, client code remains unchanged while concrete behaviors are swapped or extended at runtime.

> **Q2: Does OCP mean you should never touch existing code when fixing a bug?**
> **A:** No. Bug fixes in existing logic *require* modifying that logic. OCP applies to adding **new capabilities/features**, not correcting defective behavior.
