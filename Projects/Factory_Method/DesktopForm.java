package Projects.Factory_Method;

public class DesktopForm extends Form {
     public TextField createTextField() {
        DesktopTextField textField = new DesktopTextField();
        return textField;
    }
}
