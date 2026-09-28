package Assignments.Builder;

public class Screen {

    private String title;
    private String header;
    private String content;
    private String mainButton;

    public void setTitle(String title){
        this.title = title;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public void setMainButton(String mainButton) {
        this.mainButton = mainButton;
    }

    public void show(){
        System.out.println("Title: " + this.title + "\n" + "Header: " + this.header + 
        "\n" + "Content: " + this.content + "\n" + "Button: " + this.mainButton);
    }
    
}
