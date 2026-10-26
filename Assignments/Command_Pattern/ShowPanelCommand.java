package Assignments.Command_Pattern;

public class ShowPanelCommand implements Command {
    private final Panel panel;

    public ShowPanelCommand(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void execute() {
        panel.show();
    }
}
