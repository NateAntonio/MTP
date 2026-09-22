package Projects.Factory_Method;

public class MobileForm extends Form {
     public TextField createTextField() {
        MobileTextField textField = new MobileTextField();
        return textField;
    }  
}
