# 🧱 SOLID Principles of Object-Oriented Design

The **SOLID** principles are five design guidelines introduced by Robert C. Martin (Uncle Bob) to create understandable, flexible, testable, and maintainable software architectures.

---

## 📋 The 5 Principles at a Glance

```
S ──► Single Responsibility Principle (SRP)
      "A class should have only ONE reason to change."

O ──► Open/Closed Principle (OCP)
      "Software entities should be OPEN for extension, but CLOSED for modification."

L ──► Liskov Substitution Principle (LSP)
      "Subtypes must be substitutable for their base types without breaking application behavior."

I ──► Interface Segregation Principle (ISP)
      "Clients should not be forced to depend on interfaces they do not use."

D ──► Dependency Inversion Principle (DIP)
      "High-level modules should depend on ABSTRACTIONS, not on concrete low-level details."
```

---

## 🗂️ Principle Breakdown & Navigation

| Principle | Primary Problem Solved | Key Design Strategy | Link |
|:---|:---|:---|:---|
| **[SRP](./S%20-%20Single%20Responsibility%20Principle/)** | Bloated god-classes with tangled concerns | Split business logic, persistence, and presentation into dedicated classes | [Explore SRP](./S%20-%20Single%20Responsibility%20Principle/) |
| **[OCP](./O%20-%20Open%20Closed%20Principle/)** | Cascading code edits and regression bugs when adding features | Introduce interfaces and polymorphism to allow new plugins without editing existing code | [Explore OCP](./O%20-%20Open%20Closed%20Principle/) |
| **[LSP](./L%20-%20Liskov%20Substitution%20Principle/)** | Fragile inheritance hierarchies and unexpected runtime exceptions | Ensure subclasses never narrow down parent capabilities or throw `UnsupportedOperationException` | [Explore LSP](./L%20-%20Liskov%20Substitution%20Principle/) |
| **[ISP](./I%20-%20Interface%20Segregation%20Principle/)** | Fat interfaces forcing classes to implement dummy/error-throwing methods | Segregate large bloated interfaces into smaller, role-specific, focused contracts | [Explore ISP](./I%20-%20Interface%20Segregation%20Principle/) |
| **[DIP](./D%20-%20Dependency%20Inversion%20Principle/)** | Tight coupling between high-level policy and low-level utility modules | Use Dependency Injection with abstract interfaces rather than `new ConcreteClass()` | [Explore DIP](./D%20-%20Dependency%20Inversion%20Principle/) |
