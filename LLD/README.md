# 🏛️ Low Level Design (LLD) Mastery

A comprehensive, production-grade knowledge base for Object-Oriented Analysis and Design (OOAD), SOLID Principles, and Design Patterns in Java.

---

## 📑 Repository Structure

```
LLD/
├── SOLID Principles/
│   ├── README.md                                  ← Overview of all 5 SOLID Principles
│   ├── S - Single Responsibility Principle/       ← Invoice & InvoiceDao/Printer case study
│   ├── O - Open Closed Principle/                 ← Invoice persistence extension via interfaces
│   ├── L - Liskov Substitution Principle/         ← Bike & Vehicle engine capability hierarchies
│   ├── I - Interface Segregation Principle/       ← Fat restaurant interfaces split into role tasks
│   └── D - Dependency Inversion Principle/        ← MacBook hardware abstraction & constructor injection
│
└── Behavioral Patterns/
    └── Strategy Pattern/                          ← Vehicle Drive Modes & Shopping Cart Payment Strategies
```

---

## 🧭 The Core Design Philosophy

1. **High Cohesion & Low Coupling:** Components should do one thing well and be minimally dependent on the concrete implementation details of others.
2. **Favor Composition over Inheritance:** Encapsulate varying algorithms into interchangeable strategy components.
3. **Program to Interfaces, not Implementations:** Code against abstractions so the system remains open for extension and resilient to change.
