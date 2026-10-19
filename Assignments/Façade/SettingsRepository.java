package Assignments.Façade;

public class SettingsRepository {
    public String loadSettings() {
        System.out.println("Loading saved settings");
        return "Language=English; Theme=Dark";
    }

    public void saveSettings(String settings) {
        System.out.println("Saving settings");
    }
}