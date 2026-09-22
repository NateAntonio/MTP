package Projects.Singleton;

public class InterfaceConfiguration {
    private static InterfaceConfiguration instance;
    private String language;
    private Integer fontsize;
    
    // Info: language (String) and font size (int)
    // Initial Values: Language = "SP" and Font Size = 16
    private InterfaceConfiguration (){
        this.language = "SP";
        this.fontsize = 16;
    }

    public static InterfaceConfiguration getInstance(){
        if (instance == null){
            instance = new InterfaceConfiguration();
        }
        return instance;
    }

    // GETTERS
    public String getLanguage (){
        return language;
    }
    public int getFontsize(){
        return fontsize;
    }

    // SETTERS
    public void setLanguage(String language){
        this.language = language;
    }
    public void setFontsize(int fontsize){
        this.fontsize = fontsize;
    }

    // Create the ScreenHome and ScreenSettings classes, 
    // each with a display() method.
    
    public static class ScreenHome{
        public void display(){
            InterfaceConfiguration intCon = InterfaceConfiguration.getInstance();
            System.out.println("ScreenHome. Language: " + intCon.getLanguage() + " Fontsize: " + intCon.getFontsize());
        }
    }

    public static class ScreenSettings{
        public void display(){
            InterfaceConfiguration intCon = InterfaceConfiguration.getInstance();
            System.out.println("ScreenHome. Language: " + intCon.getLanguage() + " Fontsize: " + intCon.getFontsize());
        }
    }

    // Main

    public static void main(String[] args) {
        new ScreenHome().display();
        new ScreenSettings().display();
        InterfaceConfiguration intCon = InterfaceConfiguration.getInstance();
        intCon.setLanguage("EN");
        intCon.setFontsize(20);

        new ScreenHome().display();
        new ScreenSettings().display();
    }

}

/**
Question you should be able to answer
Why do ScreenHome and ScreenSettings see the exact same settings even though they each call
getInstance() separately?

Because the data they are getting the information from is the same, i.e: the variables of language and fontsize are both comign from the
same root.
**/
