import java.util.Objects;

public final class Employee {
    private final int id;
    private final String name;
    private final double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }

    @Override
    public boolean equals(Object obj) {
        // 1. Reflexivity / Identity shortcut
        if (this == obj) return true;

        // 2. Null and Type Check
        if (obj == null || getClass() != obj.getClass()) return false;

        // 3. Safe cast
        Employee other = (Employee) obj;

        // 4. Compare significant fields
        return this.id == other.id &&
               Double.compare(this.salary, other.salary) == 0 &&
               Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary);
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + "}";
    }
}
