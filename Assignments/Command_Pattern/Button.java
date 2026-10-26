package Assignments.Command_Pattern;

public class Button {
    private final String label;
    private Command command;

    public Button(String label) {
        this.label = label;
    }

    public Button(String label, Command command) {
        this.label = label;
        this.command = command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void click() {
        System.out.println("-> Button '" + label + "' clicked.");
        if (command != null) {
            command.execute();
        } else {
            System.out.println("   No command assigned.");
        }
    }
}