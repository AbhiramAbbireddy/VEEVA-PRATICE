# Phase 03 — Inheritance & Polymorphism

## 🎯 Phase Objective
To master code reuse through class hierarchies, understand the `super` keyword and contiguous heap memory layouts, navigate the critical distinction between Reference Types (compile-time access) vs Object Types (runtime behavior), master compile-time Method Overloading, master runtime Method Overriding and Dynamic Method Dispatch, dissect `final` and `static` method hiding, and implement contract-obeying `equals()`, `hashCode()`, and `toString()` methods on the cosmic `java.lang.Object` superclass.

---

## 📑 Topics Covered

1. **[01 - Inheritance And Super](./01%20-%20Inheritance%20And%20Super/)**: `extends` keyword, `super()` constructor chaining rules, heap memory layouts of derived objects, single vs multilevel vs hierarchical inheritance, and the Diamond Problem.
2. **[02 - Reference Type vs Object Type](./02%20-%20Reference%20Type%20vs%20Object%20Type/)**: Remote control vs physical TV analogy, compile-time access bounds vs runtime object identity, and why child references cannot point to parent objects.
3. **[03 - Method Overloading](./03%20-%20Method%20Overloading/)**: Compile-time (static) polymorphism, parameter list signature requirements, return type irrelevance, and static argument type binding traps (Effective Java Item 52).
4. **[04 - Method Overriding And Dynamic Dispatch](./04%20-%20Method%20Overriding%20And%20Dynamic%20Dispatch/)**: Run-time (dynamic) polymorphism, late binding via Dynamic Method Dispatch, covariant return types, access modifier non-reduction, and `@Override` safety (Effective Java Item 40).
5. **[05 - Final And Static Overriding](./05%20-%20Final%20And%20Static%20Overriding/)**: Early binding with `final`, method hiding of `static` methods, and the dangerous trap of constructors invoking overridable methods (Effective Java Item 19).
6. **[06 - Cosmic Object Class And Contracts](./06%20-%20Cosmic%20Object%20Class%20And%20Contracts/)**: `==` vs `equals()`, the critical `equals`/`hashCode` contract, hash bucket lookup breakdowns, `toString()` conventions, and the symmetry/transitivity pitfalls of subclass value extension (Effective Java Items 10-12).
