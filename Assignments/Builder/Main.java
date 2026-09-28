package Assignments.Builder;

public class Main {
    public static void main(String[] args) throws Exception {
        // ProfileBuilder
        ScreenBuilder profileBuilder = new ProfileBuilder();
        ScreenDirector director = new ScreenDirector(profileBuilder);
        director.buildScreen();
        
        Screen screen = profileBuilder.getScreen();
        screen.show();

        System.out.println(); // Space between outputs

        // SettingBuilder
        ScreenBuilder settingBuilder = new SettingBuilder();
        director = new ScreenDirector(settingBuilder);
        director.buildScreen();

        Screen settingsScreen = settingBuilder.getScreen();
        settingsScreen.show();
    }
}

/*
Question: Why do we use Builder instead of directly creating a Screen object with a constructor that
receives all of its attributes?

Answer: We use a Builder when an object has too many attributes, 
a standard constructor gets hard to read, hard to maintain, and is quite error-prone. 

Furthermore, when an object has optional attributes, 
traditional constructors force you to pass null or default values for every unused parameter. 

With the Builder pattern, you only call the methods to set the attributes you actually need, 
while leaving the rest initialized to their default values.   

Also, while using a Builder we get to separate the construction process from the final object's representation, 
allowing a Director to reuse the exact same sequence of steps: 
buildTitle() --> buildHeader() --> buildContent() --> buildButton() 
All to create completely different screens.
*/

