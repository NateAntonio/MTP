package Assignments.Façade;

public class SettingsDialogFacade {
    private SettingsRepository settingsRepository;
    private LanguageService languageService;
    private SettingsDialogBuilder dialogBuilder;
    private SettingsValidator settingsValidator;

    public SettingsDialogFacade() {
        this.settingsRepository = new SettingsRepository();
        this.languageService = new LanguageService();
        this.dialogBuilder = new SettingsDialogBuilder();
        this.settingsValidator = new SettingsValidator();
    }

    public String openSettingsDialog() {
        String settings = settingsRepository.loadSettings();
        String languages = languageService.getAvailableLanguages();
        return dialogBuilder.createDialog(settings, languages);
    }

    public void saveSettings(String settings) {
        if (settingsValidator.validate(settings)) {
            settingsRepository.saveSettings(settings);
        }
    }
}