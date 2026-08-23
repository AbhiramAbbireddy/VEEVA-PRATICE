// Strategy Design Pattern - Case Study 2: Shopping Cart Payment Processor

// 1. Strategy Interface
interface PaymentStrategy {
    void pay(double amount);
}

// 2. Concrete Strategy: Credit Card
class CreditCardPayment implements PaymentStrategy {
    private final String cardNumber;
    private final String name;

    public CreditCardPayment(String cardNumber, String name) {
        this.cardNumber = cardNumber;
        this.name = name;
    }

    @Override
    public void pay(double amount) {
        String maskedCard = cardNumber.substring(cardNumber.length() - 4);
        System.out.println("Paid $" + amount + " using Credit Card ending in " + maskedCard + " [Cardholder: " + name + "]");
    }
}

// 3. Concrete Strategy: PayPal
class PayPalPayment implements PaymentStrategy {
    private final String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal Account (" + email + ")");
    }
}

// 4. Concrete Strategy: UPI
class UPIPayment implements PaymentStrategy {
    private final String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using UPI ID (" + upiId + ")");
    }
}

// 5. Concrete Strategy: Crypto (Added easily without touching ShoppingCart!)
class CryptoPayment implements PaymentStrategy {
    private final String walletAddress;

    public CryptoPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Cryptocurrency Wallet (" + walletAddress + ")");
    }
}

// 6. Context Class: ShoppingCart
class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not selected! Please choose a payment method before checkout.");
        }
        System.out.print("[" + paymentStrategy.getClass().getSimpleName() + "] Processing: ");
        paymentStrategy.pay(amount);
    }
}

// Client Demo
public class PaymentProcessorDemo {
    public static void main(String[] args) {
        System.out.println("====== Strategy Pattern: Payment Processor ======");

        ShoppingCart cart = new ShoppingCart();

        // 1. Pay with Credit Card
        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456", "John Doe"));
        cart.checkout(149.99);

        // 2. Switch to PayPal at runtime
        cart.setPaymentStrategy(new PayPalPayment("john.doe@example.com"));
        cart.checkout(49.50);

        // 3. Switch to UPI
        cart.setPaymentStrategy(new UPIPayment("johndoe@okhdfcbank"));
        cart.checkout(1200.00);

        // 4. Switch to new Crypto extension without modifying ShoppingCart
        cart.setPaymentStrategy(new CryptoPayment("0x71C...8976F"));
        cart.checkout(500.00);
    }
}
