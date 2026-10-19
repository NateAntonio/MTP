package Assignments.Façade;

public class Main {
    public static void main(String[] args) {
        SettingsDialogFacade facade = new SettingsDialogFacade();
        SettingsScreenController controller = new SettingsScreenController(facade);
        
        controller.openSettings();
        controller.saveSettings("Language=English; Theme=Dark");
    }
}

/*
1. Which responsibilities would otherwise have to be handled by SettingsScreenController?
Without the Façade, the controller would have to directly manage and coordinate all the subsystem operations, 
including knowing which objects to use, which methods to call, and the exact order required to load settings, 
retrieve languages, create components, validate, and save.

2. Why should SettingsRepository and SettingsValidator remain separate subsystem classes?
They stay separate to make sure there is a clear separation of concerns, ensuring each class has its own distinct responsibility 
and keeping the subsystem classes available for other clients that might require more detailed control.

3. Does SettingsDialogFacade replace the subsystem classes, or does it coordinate them? Explain briefly. 
The Façade coordinates them rather than replacing them. It provides a simplified entry point and manages the detailed work 
to the underlying subsystem classes, which retain their original functionality and remain accessible.



*/