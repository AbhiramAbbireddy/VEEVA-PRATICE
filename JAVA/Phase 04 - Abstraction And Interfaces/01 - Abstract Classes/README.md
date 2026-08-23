# Topic 4.1 — Abstract Classes

## 1. Intuition: The Incomplete Blueprint
An abstract class specifies **WHAT must be done** (through abstract method signatures) while leaving **HOW it is done** to concrete child subclasses.

---

## 2. Core Rules & Constraints

1. **Cannot be Instantiated:** `new AbstractClass()` is a compile error because incomplete methods have no executable bytecode.
2. **Can Have Constructors:** Constructors in an abstract class execute via `super()` when a child class object is instantiated to initialize inherited state.
3. **Subclass Obligation:** A concrete subclass **must implement every inherited abstract method**, or it must also be declared `abstract`.

---

## 3. The 3 Illegal Modifier Combinations (Contradiction Principle)

| Illegal Combination | WHY it is Forbidden |
|:---|:---|
| `abstract final` | `abstract` requires inheritance to be completed; `final` forbids inheritance. |
| `abstract static` | `abstract` requires dynamic overriding; `static` methods belong to the class and cannot be overridden. |
| `abstract private` | `abstract` requires subclass implementation; `private` hides the method from subclasses entirely. |
