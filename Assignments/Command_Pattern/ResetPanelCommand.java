package Assignments.Command_Pattern;

public class ResetPanelCommand implements Command {
    private final Panel panel;

    public ResetPanelCommand(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void execute() {
        panel.reset();
    }
}
