class Animal {
    int age = 5;

    public void sound() {
        System.out.println("Animal makes a generic sound.");
    }
}

class Dog extends Animal {
    int age = 2; // Field hiding
    int dogTagNumber = 999;

    @Override
    public void sound() {
        System.out.println("Dog barks: Woof Woof!");
    }

    public void fetch() {
        System.out.println("Dog fetches the ball.");
    }
}

public class ReferenceVsObjectDemo {
    public static void main(String[] args) {
        Animal myPet = new Dog();

        // 1. Field resolution (decided by REFERENCE type at compile-time)
        System.out.println("myPet.age: " + myPet.age); // 5 (from Animal!)

        // 2. Method dispatch (decided by OBJECT type at runtime)
        myPet.sound(); // "Dog barks: Woof Woof!" (from Dog!)

        // 3. Child-specific access blocked by Animal reference
        // myPet.fetch(); // Compile Error: Animal has no fetch() method
        // System.out.println(myPet.dogTagNumber); // Compile Error
    }
}
