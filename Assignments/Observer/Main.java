package Assignments.Observer;

import java.util.ArrayList;
import java.util.List;

// ==========================================
// Transaction Record / Model
// ==========================================
enum TransactionType {
    DEPOSIT, WITHDRAWAL
}

class Transaction {
    private final TransactionType type;
    private final double amount;

    public Transaction(TransactionType type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

// ==========================================
// Observer Interface (Notification Contract)
// ==========================================
interface AccountObserver {
    void update(Account account);
}

// ==========================================
// Subject / Account Class
// ==========================================
class Account {
    private double balance;
    private Transaction latestTransaction;
    private final List<AccountObserver> observers = new ArrayList<>();

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    public void attach(AccountObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void detach(AccountObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (AccountObserver observer : observers) {
            observer.update(this);
        }
    }

    public boolean deposit(double amount) {
        System.out.println("\n[Action] Attempting deposit of €" + amount);
        if (amount <= 0) {
            System.out.println("  -> Rejected: Deposit amount must be positive.");
            return false;
        }

        this.balance += amount;
        this.latestTransaction = new Transaction(TransactionType.DEPOSIT, amount);
        notifyObservers();
        return true;
    }

    public boolean withdraw(double amount) {
        System.out.println("\n[Action] Attempting withdrawal of €" + amount);
        if (amount <= 0) {
            System.out.println("  -> Rejected: Withdrawal amount must be positive.");
            return false;
        }
        if (amount > this.balance) {
            System.out.println("  -> Rejected: Insufficient balance (Available: €" + balance + ").");
            return false;
        }

        this.balance -= amount;
        this.latestTransaction = new Transaction(TransactionType.WITHDRAWAL, amount);
        notifyObservers();
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public Transaction getLatestTransaction() {
        return latestTransaction;
    }
}

// ==========================================
// Concrete Observers (Screen Elements)
// ==========================================
class BalanceDisplay implements AccountObserver {
    @Override
    public void update(Account account) {
        System.out.printf("  -> [Balance Display] Current Balance: €%.2f%n", account.getBalance());
    }
}

class TransactionDisplay implements AccountObserver {
    @Override
    public void update(Account account) {
        Transaction tx = account.getLatestTransaction();
        if (tx != null) {
            System.out.printf("  -> [Transaction Display] Last Transaction: %s of €%.2f%n",
                    tx.getType(), tx.getAmount());
        }
    }
}

class AuditDisplay implements AccountObserver {
    @Override
    public void update(Account account) {
        Transaction tx = account.getLatestTransaction();
        if (tx != null) {
            System.out.printf("  -> [Audit Log] Recorded %s of €%.2f | Balance: €%.2f%n",
                    tx.getType(), tx.getAmount(), account.getBalance());
        }
    }
}

// ==========================================
// Main Class & Demonstration Sequence
// ==========================================
public class Main {
    public static void main(String[] args) {
        // Initialize account with €1,000
        Account account = new Account(1000.0);

        // Create displays
        BalanceDisplay balanceDisplay = new BalanceDisplay();
        TransactionDisplay transactionDisplay = new TransactionDisplay();
        AuditDisplay auditDisplay = new AuditDisplay();

        // Attach displays
        account.attach(balanceDisplay);
        account.attach(transactionDisplay);
        account.attach(auditDisplay);

        System.out.println("=== Initial State: Balance €" + account.getBalance() + " ===");

        // 1. Deposit €200
        account.deposit(200.0);

        // 2. Disconnect audit display
        System.out.println("\n[Config] Disconnecting Audit Display...");
        account.detach(auditDisplay);

        // 3. Withdraw €150
        account.withdraw(150.0);

        // 4. Reject withdrawal of €2,000 (exceeds balance)
        account.withdraw(2000.0);

        // 5. Reconnect audit display
        System.out.println("\n[Config] Reconnecting Audit Display...");
        account.attach(auditDisplay);

        // 6. Deposit €50
        account.deposit(50.0);
    }
}