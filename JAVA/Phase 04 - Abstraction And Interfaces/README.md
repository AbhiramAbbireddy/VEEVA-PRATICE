# Phase 04 — Abstraction, Interfaces & Composition

## 🎯 Phase Objective
To understand partial blueprints via abstract classes, pure capability contracts via interfaces, why inheritance frequently breaks encapsulation in practice, the architectural superiority of **Composition over Inheritance** (Effective Java Item 18), and modern interface evolution rules in Java 8+ including `default` methods, `static` methods, and multi-interface diamond conflict resolution.

---

## 📑 Topics Covered

1. **[01 - Abstract Classes](./01%20-%20Abstract%20Classes/)**: Incomplete blueprints, constructor execution in abstract hierarchies, and why `abstract static`, `abstract final`, and `abstract private` are illegal contradictions.
2. **[02 - Interfaces](./02%20-%20Interfaces/)**: Pure capability contracts, implicit `public static final` fields and `public abstract` methods, multiple interface implementation, nonhierarchical type frameworks, and mixins.
3. **[03 - Composition Over Inheritance](./03%20-%20Composition%20Over%20Inheritance/)**: The interface method clash problem (Kunal's Car engine vs media `start()`), the `InstrumentedHashSet` self-use double-counting bug (Effective Java Item 18), and the `ForwardingSet` wrapper / decorator pattern.
4. **[04 - Java 8 Interface Evolution](./04%20-%20Java%208%20Interface%20Evolution/)**: `default` and `static` interface methods, the 3 Diamond Resolution Rules, and the concurrency hazards of retrofitting default methods on existing interfaces (`removeIf` in `SynchronizedCollection`, Effective Java Item 21).
