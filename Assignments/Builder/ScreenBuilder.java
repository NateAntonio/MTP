package Assignments.Builder;

public interface ScreenBuilder {
    void buildTitle();
    void buildHeader();
    void buildContent();
    void buildButton();
    Screen getScreen();
}