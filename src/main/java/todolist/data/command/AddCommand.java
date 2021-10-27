package todolist.data.command;

import todolist.data.TaskList;
import todolist.data.task.Task;
import todolist.storage.Storage;
import todolist.ui.Ui;

import java.io.IOException;

public class AddCommand extends Command{

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds task to Todolist. "
            + "Parameters: Tasks description date time ...\n"
            + "Example: " + COMMAND_WORD
            + " event project meeting /at 2/12/2019 1800";


    public AddCommand(Task task) {
        super(task);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(task);
        ui.setSize(tasks.taskCount());
        ui.showAddMessage(task.toString());
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
