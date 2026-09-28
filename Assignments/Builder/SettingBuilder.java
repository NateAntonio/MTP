package Assignments.Builder;

public class SettingBuilder implements ScreenBuilder {
    private Screen screen;

    public SettingBuilder() {
        screen = new Screen();
    }

    @Override 
    public void buildTitle() {
        screen.setTitle("SETTINGS");
    }

    @Override 
    public void buildHeader() {
        screen.setHeader("App Settings");
    }

    @Override 
    public void buildContent() {
        screen.setContent("Language - Theme - Notifications");
    }

    @Override 
    public void buildButton() {
        screen.setMainButton("[ SAVE CHANGES ]");
    }

    @Override 
    public Screen getScreen() {
        return screen;
    }
}
