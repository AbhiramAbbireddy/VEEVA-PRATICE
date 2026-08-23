# Topic 4.3 — Composition over Inheritance (Effective Java Item 18)

## 1. The Interface Method Clash Problem
When a class implements two interfaces that declare the exact same method signature (e.g. `Engine.start()` and `Media.start()`), the class is forced to collapse both distinct behaviors into a single method body.
- When the user tries to play music (`start()`), the car starts the engine instead!

### The Solution: Composition ("Has-A")
Instead of a `Car` **being** an engine and a media player, a `Car` **has** an `Engine` and **has** a `Media` player. It encapsulates instances of both and delegates calls cleanly (`startCar()` vs `startMusic()`).

---

## 2. Why Inheritance Breaks Encapsulation: The `InstrumentedHashSet` Bug

Consider a subclass counting total elements added:
```java
public class InstrumentedHashSet<E> extends HashSet<E> {
    private int addCount = 0;

    @Override public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }
}
```
If we call `s.addAll(List.of("A", "B", "C"))`:
- `addAll()` increments `addCount` by **3**.
- `super.addAll()` executes `HashSet`'s internal implementation, which internally loops and calls `this.add()`.
- Dynamic Method Dispatch routes those calls to the overridden `InstrumentedHashSet.add()`!
- `addCount` is incremented 3 more times -> **Final Count = 6 (Double Count Bug!)**.

---

## 3. The Production Fix: Wrapper / Decorator Pattern (`ForwardingSet`)

1. Create a `ForwardingSet<E>` that implements `Set<E>` and forwards all calls to an underlying `private final Set<E>`.
2. Extend `ForwardingSet<E>` in `InstrumentedSet<E>`:
```java
public class InstrumentedSet<E> extends ForwardingSet<E> {
    private int addCount = 0;
    public InstrumentedSet(Set<E> s) { super(s); }

    @Override public boolean add(E e) {
        addCount++;
        return super.add(e);
    }
    @Override public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }
}
```
- `super.addAll(c)` calls `ForwardingSet.addAll(c)`, which calls `s.addAll(c)` on the wrapped `HashSet` directly.
- The wrapped set's internal `add` calls hit the *wrapped* set's methods, **never our overridden wrapper methods**. Total count = **3 (Correct!)**.
- **Bonus:** Can wrap **any** `Set` (`HashSet`, `TreeSet`, `LinkedHashSet`).
