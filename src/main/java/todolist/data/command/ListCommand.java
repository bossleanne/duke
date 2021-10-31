package todolist.data.command;

import todolist.data.TaskList;
import todolist.data.task.Task;
import todolist.storage.Storage;
import todolist.ui.Ui;

public class ListCommand extends Command{

    public static final String COMMAND_WORD = "List";

    public static final String MESSAGE_USAGE =
            String.format("    %-11s: %s\n" , COMMAND_WORD, "Displays all tasks in the todolist as a list with index numbers." )
                    + String.format("    %-11s: %s\n" , "Parameters", "" )
                    + String.format("    %-11s: %s\n" , "Example",  COMMAND_WORD.toLowerCase());



    public ListCommand(Task task) {
        super(task);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showToUserAllTasks(tasks);
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
