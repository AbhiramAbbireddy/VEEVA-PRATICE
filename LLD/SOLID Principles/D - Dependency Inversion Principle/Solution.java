// GOOD: High-level component depends on abstractions via Constructor Injection

interface Keyboard {
    void getSpecifications();
}

interface Mouse {
    void getSpecifications();
}

// Low-level Module 1: Wired Keyboard
class WiredKeyboard implements Keyboard {
    private final String connectionType;
    private final String company;

    public WiredKeyboard(String connectionType, String company) {
        this.connectionType = connectionType;
        this.company = company;
    }

    @Override
    public void getSpecifications() {
        System.out.println("Keyboard: [Type=" + connectionType + ", Brand=" + company + "]");
    }
}

// Low-level Module 2: Bluetooth Keyboard
class BluetoothKeyboard implements Keyboard {
    private final String connectionType;
    private final String company;

    public BluetoothKeyboard(String connectionType, String company) {
        this.connectionType = connectionType;
        this.company = company;
    }

    @Override
    public void getSpecifications() {
        System.out.println("Keyboard: [Type=" + connectionType + ", Brand=" + company + " Wireless]");
    }
}

// Low-level Module 3: Wired Mouse
class WiredMouse implements Mouse {
    private final String connectionType;
    private final String company;

    public WiredMouse(String connectionType, String company) {
        this.connectionType = connectionType;
        this.company = company;
    }

    @Override
    public void getSpecifications() {
        System.out.println("Mouse: [Type=" + connectionType + ", Brand=" + company + "]");
    }
}

// Low-level Module 4: Bluetooth Mouse
class BluetoothMouse implements Mouse {
    private final String connectionType;
    private final String company;

    public BluetoothMouse(String connectionType, String company) {
        this.connectionType = connectionType;
        this.company = company;
    }

    @Override
    public void getSpecifications() {
        System.out.println("Mouse: [Type=" + connectionType + ", Brand=" + company + " Wireless]");
    }
}

// High-level Module: Depends only on abstractions
class MacBook {
    private final Keyboard keyboard;
    private final Mouse mouse;

    public MacBook(Keyboard keyboard, Mouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }

    public void displaySpecs() {
        System.out.println("--- MacBook Hardware Configuration ---");
        keyboard.getSpecifications();
        mouse.getSpecifications();
    }
}

public class Solution {
    public static void main(String[] args) {
        Keyboard wiredKb = new WiredKeyboard("USB-C", "Dell");
        Mouse wiredMs = new WiredMouse("USB-A", "Logitech");

        Keyboard btKb = new BluetoothKeyboard("Bluetooth 5.2", "Apple Magic Keyboard");
        Mouse btMs = new BluetoothMouse("Bluetooth 5.2", "Apple Magic Mouse");

        // Can easily assemble any combination without modifying MacBook
        MacBook setup1 = new MacBook(wiredKb, wiredMs);
        setup1.displaySpecs();

        System.out.println();
        MacBook setup2 = new MacBook(btKb, btMs);
        setup2.displaySpecs();
    }
}
