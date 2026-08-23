import java.util.Scanner;

public class ScannerTrapDemo {
    public static void main(String[] args) {
        String simulatedInput = "42\nJohn Doe\n";
        Scanner sc = new Scanner(simulatedInput);

        int age = sc.nextInt();
        sc.nextLine(); // Clear the leftover newline buffer
        String fullName = sc.nextLine();

        System.out.println("Age: " + age);
        System.out.println("Full Name: " + fullName);
    }
}
