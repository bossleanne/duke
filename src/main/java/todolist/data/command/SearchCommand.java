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

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": book "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: Single String Keyword ...\n"
            + "Example: " + COMMAND_WORD + " book";


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {

        TaskList result = new TaskList();

        for(int i = 0; i< tasks.taskCount();i++){

            if(tasks.getTasks(i).getDescription() != null && tasks.getTasks(i).getDescription().contains(getSearch())){
                result.addTask(tasks.getTasks(i));
            }
        }
        ui.showToUserAllTasks(result);
    }

}
