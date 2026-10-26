package Assignments.Command_Pattern;

public class HidePanelCommand implements Command {
    private final Panel panel;

    public HidePanelCommand(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void execute() {
        panel.hide();
    }
}   
