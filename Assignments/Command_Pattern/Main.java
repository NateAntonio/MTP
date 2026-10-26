package Assignments.Command_Pattern;

public class Main {
    public static void main(String[] args) {
        // Receiver
        Panel controlPanel = new Panel("System Overview");

        // Concrete Commands
        Command showCmd = new ShowPanelCommand(controlPanel);
        Command hideCmd = new HidePanelCommand(controlPanel);
        Command resetCmd = new ResetPanelCommand(controlPanel);

        // Invokers (Buttons) and configure their commands
        Button btnShow = new Button("Show Panel", showCmd);
        Button btnHide = new Button("Hide Panel", hideCmd);
        Button btnReset = new Button("Reset Panel", resetCmd);

        // User interactions
        System.out.println("=== Simulating User Interaction ===");
        btnHide.click();
        btnShow.click();
        btnHide.click();
        btnReset.click();
    }
}