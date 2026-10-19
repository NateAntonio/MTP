package Assignments.Façade;

public class SettingsScreenController {
    private SettingsDialogFacade facade;

    public SettingsScreenController(SettingsDialogFacade facade) {
        this.facade = facade;
    }

    public void openSettings() {
        String dialog = facade.openSettingsDialog();
        System.out.println(dialog);
    }

    public void saveSettings(String settings) {
        facade.saveSettings(settings);
    }
}