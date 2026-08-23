import java.io.Serializable;

public class ClassicSingleton implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    // Eager thread-safe initialization
    private static final ClassicSingleton INSTANCE = new ClassicSingleton();

    private ClassicSingleton() {
        // Reflection attack defense
        if (INSTANCE != null) {
            throw new AssertionError("Instance already exists. Use getInstance().");
        }
    }

    public static ClassicSingleton getInstance() {
        return INSTANCE;
    }

    // Serialization attack defense
    private Object readResolve() {
        return INSTANCE;
    }

    // Cloning attack defense
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singleton cannot be cloned.");
    }
}
