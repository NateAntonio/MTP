package Assignments.Strategy;

// ==========================================
// Strategy Interface (Common Contract)
// ==========================================
interface PaymentStrategy {
    String getMethodName();
    double calculateFee(double subtotal);
}

// ==========================================
// Concrete Strategy: Bank Transfer (€0 fee)
// ==========================================
class BankTransferStrategy implements PaymentStrategy {
    @Override
    public String getMethodName() {
        return "Bank Transfer";
    }

    @Override
    public double calculateFee(double subtotal) {
        return 0.0;
    }
}

// ==========================================
// Concrete Strategy: Credit Card (2% fee)
// ==========================================
class CreditCardStrategy implements PaymentStrategy {
    @Override
    public String getMethodName() {
        return "Credit Card";
    }

    @Override
    public double calculateFee(double subtotal) {
        return subtotal * 0.02;
    }
}

// ==========================================
// Concrete Strategy: Digital Wallet (€1.50 fixed fee)
// ==========================================
class DigitalWalletStrategy implements PaymentStrategy {
    @Override
    public String getMethodName() {
        return "Digital Wallet";
    }

    @Override
    public double calculateFee(double subtotal) {
        return 1.50;
    }
}

// ==========================================
// Context: Checkout
// ==========================================
class Checkout {
    private double subtotal;
    private PaymentStrategy paymentStrategy;

    public Checkout(double subtotal, PaymentStrategy paymentStrategy) {
        this.subtotal = subtotal;
        this.paymentStrategy = paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void calculateAndDisplay() {
        System.out.println("\n--- Processing Checkout ---");

        // Validate: Subtotal must be strictly greater than 0
        if (subtotal <= 0) {
            System.out.printf("  [Error] Calculation rejected: Subtotal (€%.2f) must be greater than zero.%n", subtotal);
            return;
        }

        if (paymentStrategy == null) {
            System.out.println("  [Error] Calculation rejected: No payment method selected.");
            return;
        }

        double fee = paymentStrategy.calculateFee(subtotal);
        double total = subtotal + fee;

        System.out.printf("  Selected Method: %s%n", paymentStrategy.getMethodName());
        System.out.printf("  Subtotal:        €%.2f%n", subtotal);
        System.out.printf("  Fee:             €%.2f%n", fee);
        System.out.printf("  Total:           €%.2f%n", total);
    }
}

// ==========================================
// Main Demonstration Class
// ==========================================
public class Main {
    public static void main(String[] args) {
        // Create strategies
        PaymentStrategy bankTransfer = new BankTransferStrategy();
        PaymentStrategy creditCard = new CreditCardStrategy();
        PaymentStrategy digitalWallet = new DigitalWalletStrategy();

        // ----------------------------------------------------
        // Demonstrate valid subtotal of €100 with all 3 policies
        // ----------------------------------------------------
        System.out.println("==================================================");
        System.out.println("DEMO 1: Subtotal €100 across 3 Payment Methods");
        System.out.println("==================================================");

        Checkout checkout = new Checkout(100.0, bankTransfer);

        // (a) Bank Transfer
        checkout.calculateAndDisplay();

        // (b) Switch to Credit Card
        checkout.setPaymentStrategy(creditCard);
        checkout.calculateAndDisplay();

        // (c) Switch to Digital Wallet
        checkout.setPaymentStrategy(digitalWallet);
        checkout.calculateAndDisplay();

        // ----------------------------------------------------
        // Demonstrate rejection of a subtotal of €0
        // ----------------------------------------------------
        System.out.println("\n==================================================");
        System.out.println("DEMO 2: Rejection of Nonpositive Subtotal (€0)");
        System.out.println("==================================================");

        checkout.setSubtotal(0.0);
        checkout.calculateAndDisplay();
    }
}