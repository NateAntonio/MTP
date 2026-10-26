package Assignments.Command_Pattern;

public class Panel {
    private String title;
    private boolean isVisible;

    public Panel(String defaultTitle) {
        this.title = defaultTitle;
        this.isVisible = true;
    }

    public void show() {
        this.isVisible = true;
        System.out.println("[Panel] Shown: '" + title + "' (Visible: " + isVisible + ")");
    }

    public void hide() {
        this.isVisible = false;
        System.out.println("[Panel] Hidden: '" + title + "' (Visible: " + isVisible + ")");
    }

    public void reset() {
        this.title = "Default Panel Title";
        this.isVisible = true;
        System.out.println("[Panel] Reset to default configuration: '" + title + "' (Visible: " + isVisible + ")");
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
        System.out.println("[Panel] Title updated to: '" + title + "'");
    }
}