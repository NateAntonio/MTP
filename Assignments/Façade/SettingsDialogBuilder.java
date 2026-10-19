package Assignments.Façade;

public class SettingsDialogBuilder {
    public String createDialog(String settings, String languages) {
        System.out.println("Creating Settings dialog");
        return "Dialog ready: Settings=[" + settings + "], Languages=[" + languages + "]";
    }
}
