# Strategy Design Pattern (Behavioral Pattern)

## 1. Definition
> The **Strategy Pattern** is a behavioral design pattern that defines a family of algorithms, encapsulates each one inside a separate class, and makes their objects interchangeable at runtime.

It enables selecting and changing an algorithm's behavior dynamically based on client configuration, without altering the context class that uses it.

---

## 2. Real-World Analogies & Examples
1. **Courier Shipping Cost Calculation:** Calculating delivery charges based on different strategies (Standard Flat-rate, Express Air, Distance-based, or Weight-based).
2. **E-Commerce Checkout Payment:** Paying via Credit Card, PayPal, UPI, Net Banking, or Cash on Delivery.
3. **Vehicle Manufacturing & Drive Modes:** Vehicles with varying drive modes (Normal Drive, Sports Drive, Electric/EV Drive, All-Wheel Drive).
4. **Navigation Maps:** Routing strategy based on travel mode (Driving, Walking, Cycling, Public Transit).

---

## 3. Problems Without Strategy Pattern
1. **Massive Conditional (`if-else` / `switch`) Blocks:** A single class becomes bloated with algorithms for all variants.
2. **Violation of OCP & SRP:** Adding a new algorithm requires modifying the monolithic context class, risking regressions.
3. **Code Duplication:** Subclasses in an inheritance hierarchy that need the same special behavior are forced to duplicate code if it's not in the parent class (e.g. `SportsVehicle` and `OffRoadVehicle` both duplicating sports driving logic).
4. **Tight Coupling & Testing Friction:** Inability to test algorithms in isolation.

---

## 4. Structure of Strategy Pattern

```
┌──────────────────────────────────────┐
│          Context (e.g. Vehicle)      │
│  - strategy: DriveStrategy           │
├──────────────────────────────────────┤
│  + setStrategy(DriveStrategy)        │
│  + drive()                           │
└──────────────────┬───────────────────┘
                   │ has-a (delegates)
                   ▼
┌──────────────────────────────────────┐
│     «interface» DriveStrategy        │
├──────────────────────────────────────┤
│  + drive()                           │
└──────────────────┬───────────────────┘
                   │
    ┌──────────────┼──────────────┐
    ▼              ▼              ▼
┌──────────────┐ ┌──────────────┐ ┌──────────────┐
│ NormalDrive  │ │ SportsDrive  │ │   EVDrive    │
├──────────────┤ ├──────────────┤ ├──────────────┤
│ + drive()    │ │ + drive()    │ │ + drive()    │
└──────────────┘ └──────────────┘ └──────────────┘
```

### Components
1. **Strategy Interface (`PaymentStrategy`, `DriveStrategy`):** Common contract implemented by all algorithmic variants.
2. **Concrete Strategies (`CreditCardPayment`, `PayPalPayment`, `SportsDrive`):** Specific encapsulated algorithms.
3. **Context (`ShoppingCart`, `Vehicle`):** Maintains a reference to a Strategy object and delegates execution to it.
4. **Client:** Configures the context with the desired strategy at runtime.

---

## 5. Implementations

### Case Study 1: Vehicle Drive Modes
- **Strategy Interface:** `DriveStrategy` (`drive()`)
- **Concrete Strategies:** `NormalDrive`, `SportsDrive`, `EVDrive`
- **Context:** `Vehicle` (with subclasses `SportsVehicle`, `GoodsVehicle`, `HybridVehicle`, `OffRoadVehicle`)
- **Code:** [VehicleDriveDemo.java](./VehicleDriveDemo.java)

### Case Study 2: Shopping Cart Payment Processing
- **Strategy Interface:** `PaymentStrategy` (`pay(amount)`)
- **Concrete Strategies:** `CreditCardPayment`, `PayPalPayment`, `UPIPayment`
- **Context:** `ShoppingCart` (`checkout(amount)`)
- **Code:** [PaymentProcessorDemo.java](./PaymentProcessorDemo.java)

---

## 6. Key Benefits of Strategy Pattern
1. **Clean Runtime Algorithm Swapping:** Switch payment or drive strategies on the fly without creating new context objects.
2. **Strict Adherence to OCP:** Add a new payment method (e.g. `CryptoPayment`) by simply adding a new class implementing `PaymentStrategy`—zero edits to `ShoppingCart`.
3. **Eliminates Code Duplication:** Multiple vehicle types share the `SportsDrive` strategy instance rather than duplicating overridden methods.
4. **Eliminates Sprawling Conditional Statements:** Replaces fragile `switch(type)` trees with dynamic polymorphism.

---

## 7. Drawbacks & Nuances
- **Increased Class Count:** Every new algorithm adds a new `.java` file.
- **Client Must Be Aware of Differences:** The calling client must understand how strategies differ to choose the appropriate one.

---

## 8. Common Interview Questions
> **Q1: What is the difference between Strategy Pattern and State Pattern?**
> **A:** While structurally similar, their **intent** differs:
> - **Strategy Pattern:** The client *consciously chooses* an algorithm/strategy to configure the context. Strategies are usually independent and unaware of each other.
> - **State Pattern:** The context's behavior changes automatically as its internal state transitions. States often know about each other and trigger state transitions.

> **Q2: Strategy Pattern vs Factory Pattern?**
> **A:** Strategy is a **Behavioral Pattern** (how algorithms run and swap at runtime). Factory is a **Creational Pattern** (how objects are instantiated). Often they work together: a Factory creates and supplies the Strategy to the Context.
