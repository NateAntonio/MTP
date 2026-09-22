package singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryList {
    private List history = Collections.synchronizedList(new ArrayList<String>());
    private static HistoryList instance = new HistoryList();
    
    //Constructor privado
    private HistoryList() {}
    
    public static HistoryList getInstance() { return instance; }
    
    public void addCommand(String command){
        history.add(command);
    }
    
    public Object undoCommand() {
        return history.remove(history.size() - 1);
    }
    
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < history.size(); i++){
            result.append("   ");
            result.append(history.get(i));
            result.append("\n");
        }
        return result.toString();
    }
}
