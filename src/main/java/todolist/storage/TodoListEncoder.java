package todolist.storage;

import todolist.data.Deadline;
import todolist.data.Event;
import todolist.data.Task;

import java.io.FileWriter;
import java.io.IOException;

/** TODO add todolist class
 * Encodes the {@code TodoList} object into a data file for storage.
 */

public class TodoListEncoder{

    public static String textToAppend = "";
    public static String splitBy = " | ";
    public static String filePath;

    public TodoListEncoder(String filePath){
        this.filePath = filePath;
    }

    public static void appendToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        textToAppend = task.getTaskStatus()+splitBy+getStatusNum(task.getIsDone())+splitBy+task.getDescription();
        //use get class to access different method
        if(task.getClass().getName().equals("todolist.data.Deadline")){
            fw.write(textToAppend+splitBy+ Deadline.getBy());
        }
        else if(task.getClass().getName().equals("todolist.data.Event")){
            fw.write(textToAppend+splitBy+ Event.getAt());
        }else{
            fw.write(textToAppend);
        }
        fw.write(System.lineSeparator());
        fw.close();
    }

    public static String getStatusNum(Boolean isDone) {
        return (isDone ? "1" : "0"); // mark done task with X
    }
}
