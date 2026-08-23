import java.util.Collection;
import java.util.HashSet;
import java.util.List;

// Broken implementation: Demonstrating the self-use double count bug (Effective Java Item 18)
public class InstrumentedHashSet<E> extends HashSet<E> {
    private int addCount = 0;

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c); // Internally invokes this.add(), causing double count!
    }

    public int getAddCount() {
        return addCount;
    }

    public static void main(String[] args) {
        InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
        s.addAll(List.of("Snap", "Crackle", "Pop"));
        System.out.println("Expected: 3, Actual: " + s.getAddCount()); // Prints 6!
    }
}
