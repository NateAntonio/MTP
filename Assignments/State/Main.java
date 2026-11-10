package Assignments.State;

// ==========================================
// State Interface (Common Contract)
// ==========================================
interface LoanState {
    void submit(LoanApplication application);
    void approve(LoanApplication application);
    void close(LoanApplication application);
    String getStatusName();
}

// ==========================================
// Concrete State: Draft
// ==========================================
class DraftState implements LoanState {
    @Override
    public void submit(LoanApplication application) {
        System.out.println("  -> Submitting application...");
        application.setState(new UnderReviewState());
    }

    @Override
    public void approve(LoanApplication application) {
        System.out.println("  -> [Invalid Action] Cannot approve an application while it is in Draft status.");
    }

    @Override
    public void close(LoanApplication application) {
        System.out.println("  -> [Invalid Action] Cannot close an application directly from Draft status.");
    }

    @Override
    public String getStatusName() {
        return "Draft";
    }
}

// ==========================================
// Concrete State: Under Review
// ==========================================
class UnderReviewState implements LoanState {
    @Override
    public void submit(LoanApplication application) {
        System.out.println("  -> [Invalid Action] Application is already submitted and Under Review.");
    }

    @Override
    public void approve(LoanApplication application) {
        System.out.println("  -> Approving application...");
        application.setState(new ApprovedState());
    }

    @Override
    public void close(LoanApplication application) {
        System.out.println("  -> Closing application from review...");
        application.setState(new ClosedState());
    }

    @Override
    public String getStatusName() {
        return "Under Review";
    }
}

// ==========================================
// Concrete State: Approved
// ==========================================
class ApprovedState implements LoanState {
    @Override
    public void submit(LoanApplication application) {
        System.out.println("  -> [Invalid Action] Application has already been approved.");
    }

    @Override
    public void approve(LoanApplication application) {
        System.out.println("  -> [Invalid Action] Application is already approved.");
    }

    @Override
    public void close(LoanApplication application) {
        System.out.println("  -> Closing approved application...");
        application.setState(new ClosedState());
    }

    @Override
    public String getStatusName() {
        return "Approved";
    }
}

// ==========================================
// Concrete State: Closed
// ==========================================
class ClosedState implements LoanState {
    @Override
    public void submit(LoanApplication application) {
        System.out.println("  -> [Invalid Action] Application is Closed. No further actions allowed.");
    }

    @Override
    public void approve(LoanApplication application) {
        System.out.println("  -> [Invalid Action] Application is Closed. No further actions allowed.");
    }

    @Override
    public void close(LoanApplication application) {
        System.out.println("  -> [Invalid Action] Application is already Closed.");
    }

    @Override
    public String getStatusName() {
        return "Closed";
    }
}

// ==========================================
// Context: LoanApplication
// ==========================================
class LoanApplication {
    private LoanState currentState;

    public LoanApplication() {
        // Initial status is Draft
        this.currentState = new DraftState();
        System.out.println("Loan Application created. Initial Status: " + currentState.getStatusName());
    }

    public void setState(LoanState newState) {
        this.currentState = newState;
        System.out.println("  => Status updated to: " + currentState.getStatusName());
    }

    public void submit() {
        System.out.println("\n[User Action] Clicked 'Submit' (Current Status: " + currentState.getStatusName() + ")");
        currentState.submit(this);
    }

    public void approve() {
        System.out.println("\n[User Action] Clicked 'Approve' (Current Status: " + currentState.getStatusName() + ")");
        currentState.approve(this);
    }

    public void close() {
        System.out.println("\n[User Action] Clicked 'Close' (Current Status: " + currentState.getStatusName() + ")");
        currentState.close(this);
    }

    public String getStatus() {
        return currentState.getStatusName();
    }
}

// ==========================================
// Main Demonstration Class
// ==========================================
public class Main {
    public static void main(String[] args) {
        // ----------------------------------------------------
        // Sequence 1: Submit -> Approve -> Close -> Submit again
        // ----------------------------------------------------
        System.out.println("==================================================");
        System.out.println("SEQUENCE 1: Submit -> Approve -> Close -> Submit");
        System.out.println("==================================================");
        LoanApplication app1 = new LoanApplication();

        app1.submit();   // Draft -> Under Review
        app1.approve();  // Under Review -> Approved
        app1.close();    // Approved -> Closed
        app1.submit();   // Attempt invalid action on Closed

        // ----------------------------------------------------
        // Sequence 2: Invalid Approve on a new Draft application
        // ----------------------------------------------------
        System.out.println("\n==================================================");
        System.out.println("SEQUENCE 2: Invalid Approve on new Draft");
        System.out.println("==================================================");
        LoanApplication app2 = new LoanApplication();

        app2.approve();  // Prohibited in Draft
    }
}