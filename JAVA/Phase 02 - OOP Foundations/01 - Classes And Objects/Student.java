public class Student {
    int rollNo;
    String name;
    float marks;

    public static void main(String[] args) {
        // Dynamic memory allocation
        Student kunal = new Student();
        System.out.println("Default rollNo: " + kunal.rollNo); // 0
        System.out.println("Default name: " + kunal.name);     // null
        System.out.println("Default marks: " + kunal.marks);   // 0.0

        // Populating state via dot operator
        kunal.rollNo = 13;
        kunal.name = "Kunal Kushwaha";
        kunal.marks = 88.5f;

        System.out.println("Updated student: " + kunal.name + " with roll " + kunal.rollNo);
    }
}
