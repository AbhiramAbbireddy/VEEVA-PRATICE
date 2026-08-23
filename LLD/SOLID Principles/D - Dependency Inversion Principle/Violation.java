// BAD: High-level component directly couples to low-level concrete implementations

class BadWiredKeyboard {
    public void getSpecs() {
        System.out.println("Wired Keyboard connected via USB.");
    }
}

class BadWiredMouse {
    public void getSpecs() {
        System.out.println("Wired Mouse connected via USB.");
    }
}

class BadMacBook {
    // ❌ Violation: Direct dependency on concrete classes
    private final BadWiredKeyboard keyboard;
    private final BadWiredMouse mouse;

    public BadMacBook(BadWiredKeyboard keyboard, BadWiredMouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }

    public void show() {
        keyboard.getSpecs();
        mouse.getSpecs();
    }
}

public class Violation {
    public static void main(String[] args) {
        BadMacBook mac = new BadMacBook(new BadWiredKeyboard(), new BadWiredMouse());
        mac.show();

        // Cannot pass Bluetooth hardware without rewriting BadMacBook class!
    }
}
