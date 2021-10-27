package todolist.data.command;

import todolist.data.TaskList;
import todolist.data.task.Task;
import todolist.storage.Storage;
import todolist.ui.Ui;

public class ListCommand extends Command{

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all tasks in the todolist as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

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
