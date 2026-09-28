package Assignments.Builder;

public class ScreenDirector {

    private ScreenBuilder builder;

    public ScreenDirector(ScreenBuilder builder) {
        this.builder = builder;
    }

    public void buildScreen() {
        builder.buildTitle();
        builder.buildHeader();
        builder.buildContent();
        builder.buildButton();
    }
}
