package todolist.data.command;

import todolist.data.TaskList;
import todolist.storage.Storage;
import todolist.ui.Ui;

import java.io.IOException;

public class SearchCommand extends Command{

    private static String search;

    public void setSearch(String search){
        this.search = search;
    }

    public static String getSearch(){
        return search;
    }

    public static final String COMMAND_WORD = "Find";

    public static final String MESSAGE_USAGE =
            String.format("    %-11s: %s\n" , COMMAND_WORD, "the specified keywords (case-sensitive) and displays them as a list with index numbers." )
                    + String.format("    %-11s: %s\n" , "Parameters", "Single String Keyword" )
                    + String.format("    %-11s: %s\n" , "Example",  COMMAND_WORD.toLowerCase() + " book");


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {

        TaskList result = new TaskList();
        boolean isFound = false;

        for(int i = 0; i< tasks.taskCount();i++){
            if(tasks.getTasks(i).getDescription() != null && tasks.getTasks(i).getDescription().contains(getSearch())){
                result.addTask(tasks.getTasks(i));
                isFound = true;
            }
        }
        if (!isFound){
            System.out.println("No matching tasks in your list, please try other keywords ");
        }else{
            ui.showToUserAllTasks(result);
        }

    }

}
