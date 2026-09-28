package Assignments.Builder;

public class ProfileBuilder implements ScreenBuilder {
    
    private Screen screen;

    public ProfileBuilder() {
        screen = new Screen();
    }

    @Override 
    public void buildTitle() {
        screen.setTitle("MY PROFILE");
    }

    @Override 
    public void buildHeader() {
        screen.setHeader("User Data");
    }

    @Override 
    public void buildContent() {
        screen.setContent("Name - Email - Photography");
    }

    @Override 
    public void buildButton() {
        screen.setMainButton("[ EDIT PROFILE ]");
    }

    @Override 
    public Screen getScreen() {
        return screen;
    }
}