# Phase 02 — Object-Oriented Programming (OOP) Foundations

## 🎯 Phase Objective
To understand real-world object modeling, dynamic memory allocation in heap via `new`, stack reference vs heap instance mechanics, constructor chaining and `this` resolution, strict encapsulation and access protection, the class-level nature of `static`, `final` immutability contracts, initialization execution order, and production singleton / noninstantiable patterns.

---

## 📑 Topics Covered

1. **[01 - Classes And Objects](./01%20-%20Classes%20And%20Objects/)**: Blueprints vs physical heap instances, the 3 properties of an object (State, Identity, Behavior), stack reference vs heap object, and the dot operator.
2. **[02 - Constructors And This](./02%20-%20Constructors%20And%20This/)**: Constructor mechanics, default constructor suppression, constructor chaining with `this()`, parameter shadowing resolution, and copy constructors.
3. **[03 - Encapsulation And Access Modifiers](./03%20-%20Encapsulation%20And%20Access%20Modifiers/)**: Data hiding, invariant enforcement, the 4 access levels (`private`, `default`, `protected`, `public`), and Java's own `ArrayList` source code analysis.
4. **[04 - Static Keyword Deep Dive](./04%20-%20Static%20Keyword%20Deep%20Dive/)**: Class-level variables (`Human.population`), the Golden Rule of static methods, static blocks (execution once at class loading), and static inner classes.
5. **[05 - Final Keyword And Initialization Order](./05%20-%20Final%20Keyword%20And%20Initialization%20Order/)**: Final variables/references vs immutability, blank finals, and the strict execution sequence: *Static initializers -> Instance initializers -> Constructor*.
6. **[06 - Singleton And Utility Classes](./06%20-%20Singleton%20And%20Utility%20Classes/)**: Classic Lazy/Eager Singleton, the Enum Singleton (Effective Java Item 3), defenses against reflection/serialization/cloning attacks, and noninstantiable utility classes (Effective Java Item 4).
