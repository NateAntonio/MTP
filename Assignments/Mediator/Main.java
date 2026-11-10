package Assignments.Mediator;
// ==========================================
// Enumerations & Helper Types
// ==========================================
enum RiskProfile {
    CONSERVATIVE("Conservative", 0, 30),
    BALANCED("Balanced", 20, 60),
    AGGRESSIVE("Aggressive", 40, 80);

    private final String displayName;
    private final int minEquity;
    private final int maxEquity;

    RiskProfile(String displayName, int minEquity, int maxEquity) {
        this.displayName = displayName;
        this.minEquity = minEquity;
        this.maxEquity = maxEquity;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getMinEquity() {
        return minEquity;
    }

    public int getMaxEquity() {
        return maxEquity;
    }

    public boolean isAllowed(int equity) {
        return equity >= minEquity && equity <= maxEquity;
    }
}

// ==========================================
// Mediator Interface
// ==========================================
interface PortfolioMediator {
    void onRiskProfileChanged(RiskProfile newProfile);
    void onEquityPercentageChanged(int newEquity);
    void onConfirmClicked();
}

// ==========================================
// Base Component
// ==========================================
abstract class Component {
    protected PortfolioMediator mediator;

    public void setMediator(PortfolioMediator mediator) {
        this.mediator = mediator;
    }
}

// ==========================================
// UI Components (Colleagues)
// ==========================================
class RiskProfileSelector extends Component {
    private RiskProfile selectedProfile;

    public void selectProfile(RiskProfile profile) {
        this.selectedProfile = profile;
        System.out.println("\n[User Action] Selected Risk Profile: " + profile.getDisplayName());
        if (mediator != null) {
            mediator.onRiskProfileChanged(profile);
        }
    }

    public RiskProfile getSelectedProfile() {
        return selectedProfile;
    }
}

class EquityAllocationField extends Component {
    private int equityPercentage;
    private int minPermitted;
    private int maxPermitted;

    // Called by the mediator to set the permitted bounds
    public void setPermittedRange(int min, int max) {
        this.minPermitted = min;
        this.maxPermitted = max;
        System.out.println("  -> [Equity Field] Permitted range updated to: " + min + "% - " + max + "%");
    }

    // Called when the user inputs a new value
    public void enterEquityPercentage(int percentage) {
        this.equityPercentage = percentage;
        System.out.println("\n[User Action] Entered Equity Percentage: " + percentage + "%");
        if (mediator != null) {
            mediator.onEquityPercentageChanged(percentage);
        }
    }

    public int getEquityPercentage() {
        return equityPercentage;
    }
}

class PortfolioSummaryPanel extends Component {
    public void displaySummary(RiskProfile profile, int equityPercentage, int bondPercentage) {
        System.out.println("  -> [Summary Panel] Profile: " + profile.getDisplayName() 
                + " | Equities: " + equityPercentage + "% | Bonds: " + bondPercentage + "%");
    }
}

class ValidationMessageArea extends Component {
    public void showMessage(String message, boolean isValid) {
        String status = isValid ? "VALID" : "INVALID";
        System.out.println("  -> [Validation] Status: " + status + " - " + message);
    }
}

class ConfirmButton extends Component {
    private boolean enabled = false;

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        System.out.println("  -> [Confirm Button] State: " + (enabled ? "ENABLED" : "DISABLED"));
    }

    public void click() {
        System.out.println("\n[User Action] Clicked 'Confirm Portfolio' button.");
        if (mediator != null) {
            mediator.onConfirmClicked();
        }
    }

    public boolean isEnabled() {
        return enabled;
    }
}

// ==========================================
// Concrete Mediator / Coordinator
// ==========================================
class PortfolioCoordinator implements PortfolioMediator {
    private final RiskProfileSelector riskSelector;
    private final EquityAllocationField equityField;
    private final PortfolioSummaryPanel summaryPanel;
    private final ValidationMessageArea validationArea;
    private final ConfirmButton confirmButton;

    private RiskProfile currentProfile;
    private int currentEquity;

    public PortfolioCoordinator(RiskProfileSelector riskSelector,
                                EquityAllocationField equityField,
                                PortfolioSummaryPanel summaryPanel,
                                ValidationMessageArea validationArea,
                                ConfirmButton confirmButton) {
        this.riskSelector = riskSelector;
        this.equityField = equityField;
        this.summaryPanel = summaryPanel;
        this.validationArea = validationArea;
        this.confirmButton = confirmButton;

        // Register coordinator with all components
        this.riskSelector.setMediator(this);
        this.equityField.setMediator(this);
        this.summaryPanel.setMediator(this);
        this.validationArea.setMediator(this);
        this.confirmButton.setMediator(this);

        // Initial default configuration: Conservative, 20% equity
        this.currentProfile = RiskProfile.CONSERVATIVE;
        this.currentEquity = 20;

        // Set initial state without triggering user events
        equityField.setPermittedRange(currentProfile.getMinEquity(), currentProfile.getMaxEquity());
        evaluateAndSync();
    }

    @Override
    public void onRiskProfileChanged(RiskProfile newProfile) {
        this.currentProfile = newProfile;
        // 1. Update range on the equity field
        equityField.setPermittedRange(newProfile.getMinEquity(), newProfile.getMaxEquity());
        // 2. Re-evaluate consistency, update summary, message, and button
        evaluateAndSync();
    }

    @Override
    public void onEquityPercentageChanged(int newEquity) {
        this.currentEquity = newEquity;
        // Re-evaluate consistency, update summary, message, and button
        evaluateAndSync();
    }

    @Override
    public void onConfirmClicked() {
        if (confirmButton.isEnabled()) {
            System.out.println("  *** SUCCESS: Portfolio confirmed successfully! ***");
        } else {
            System.out.println("  *** FAILED: Cannot confirm portfolio with invalid allocation! ***");
        }
    }

    private void evaluateAndSync() {
        int bondPercentage = 100 - currentEquity;

        // Update summary panel
        summaryPanel.displaySummary(currentProfile, currentEquity, bondPercentage);

        // Validate allocation against range
        boolean isValid = currentProfile.isAllowed(currentEquity);
        if (isValid) {
            validationArea.showMessage("Equity percentage is within the permitted range.", true);
            confirmButton.setEnabled(true);
        } else {
            validationArea.showMessage(
                "Allocation out of bounds! Permitted: " + currentProfile.getMinEquity() 
                + "% - " + currentProfile.getMaxEquity() + "%, Current: " + currentEquity + "%",
                false
            );
            confirmButton.setEnabled(false);
        }
    }
}

// ==========================================
// Main Demonstration Class
// ==========================================
public class Main {
    public static void main(String[] args) {
        // Instantiate GUI components
        RiskProfileSelector riskSelector = new RiskProfileSelector();
        EquityAllocationField equityField = new EquityAllocationField();
        PortfolioSummaryPanel summaryPanel = new PortfolioSummaryPanel();
        ValidationMessageArea validationArea = new ValidationMessageArea();
        ConfirmButton confirmButton = new ConfirmButton();

        System.out.println("==================================================");
        System.out.println("Initializing Coordinator (Default: Conservative, 20%)");
        System.out.println("==================================================");
        PortfolioCoordinator coordinator = new PortfolioCoordinator(
                riskSelector, equityField, summaryPanel, validationArea, confirmButton);

        // ----------------------------------------------------
        // Sequence (a): Confirm initial Conservative / 20%
        // ----------------------------------------------------
        System.out.println("\n==================================================");
        System.out.println("SEQUENCE (a): Confirm Initial Configuration");
        System.out.println("==================================================");
        confirmButton.click();

        // ----------------------------------------------------
        // Sequence (b): Change to Aggressive without changing 20%
        // (Shows range 40-80% and disabled confirmation)
        // ----------------------------------------------------
        System.out.println("\n==================================================");
        System.out.println("SEQUENCE (b): Change Risk Profile to Aggressive");
        System.out.println("==================================================");
        riskSelector.selectProfile(RiskProfile.AGGRESSIVE);
        confirmButton.click();

        // ----------------------------------------------------
        // Sequence (c): Change equities to 50%
        // (Shows valid status and successful confirmation)
        // ----------------------------------------------------
        System.out.println("\n==================================================");
        System.out.println("SEQUENCE (c): Change Equity Allocation to 50%");
        System.out.println("==================================================");
        equityField.enterEquityPercentage(50);
        confirmButton.click();
    }
}
