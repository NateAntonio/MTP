package Projects.Factory_Method;

public abstract class Form {
    public abstract TextField createTextField();
    void display(){
        TextField textField = createTextField();
        textField.display();
    }
}
