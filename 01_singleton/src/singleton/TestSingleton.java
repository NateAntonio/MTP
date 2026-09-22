package singleton;

public class TestSingleton {

    public static void main(String[] args) {
        //Reference to the HistoryList is obtained
        HistoryList command_list = HistoryList.getInstance();
        
        //Commands addition
        command_list.addCommand("Command 1");
        command_list.addCommand("Command 2");
        command_list.addCommand("Command 3");
        command_list.addCommand("Command 4");
        System.out.println("Commands in the command_list: \n" + command_list.toString());
        
        //Commands deleted
        System.out.println("Command to undo: " + command_list.undoCommand());
        System.out.println("Command to undo: " + command_list.undoCommand());
        System.out.println("Commands in the command_list: \n" + command_list.toString());
    }
    
}
