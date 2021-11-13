package todolist.data.command;

import todolist.data.TaskList;
import todolist.storage.Storage;
import todolist.ui.Ui;
/**
 * List out all tasks in the Tasklist to the user
 */
public class ListCommand extends Command{
    public static final String COMMAND_WORD = "List";
    /**
     * Shows the usage of List
     */
    public static final String MESSAGE_USAGE =
            String.format("    %-11s: %s\n" , COMMAND_WORD, "Displays all tasks in the todolist as a list with index numbers." )
                    + String.format("    %-11s: %s\n" , "Example",  COMMAND_WORD.toLowerCase());

    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        if(tasks.taskCount()>0){
            ui.showToUserAllTasks(tasks);
        }else{
            ui.showEmptyTask();
        }
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
