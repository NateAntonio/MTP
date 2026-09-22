package Projects.Factory_Method;
public class Main {
    public static void main(String[] args) {
        MobileForm mobileForm = new MobileForm();
        mobileForm.display();

        DesktopForm desktopForm = new DesktopForm();
        desktopForm.display();
    }

    /*
    1) What role does the TextField interface play?
        Is an abstract Product that dictates a common interface
    2) What does "delegating" creation mean here?
        It means that the primary class doesnt instantiate objects directly using "new" and instead leaves it to the subclasses 
    3) Why do MobileForm and DesktopForm redefine createTextField()?
        They @Override createTextField() to return the specific product for either platform 
        MobileForm overrides it to return a MobileTextField.
        DesktopForm overrides it to return a DesktopTextField
    4) If we add a Web version tomorrow, what new classes would you create and what existing code would
    you not have to modify?
        We would create "WebTextField" that would implement "TextField", as well as WebForm which Extends "Form" 
        This will @Override createTextField() to return a WebTextField
        You would not have to modify: TextField, Form, MobileForm & DesktopForm, MobileTextField & DesktopTextField
    */
}