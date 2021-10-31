package todolist.data.command;

import todolist.data.TaskList;
import todolist.data.task.Task;
import todolist.storage.Storage;
import todolist.ui.DukeException;
import todolist.ui.Ui;

public class AddCommand extends Command{

    public static final String COMMAND_WORD = "Add Task";

    public static final String MESSAGE_USAGE =
            String.format("    %-11s: %s\n" , COMMAND_WORD, "Adds task to Todolist. " )
            + String.format("    %-11s: %s\n" , "Parameters", "DESCRIPTIONS" )
            + String.format("    %-11s: %s\n" , "Example", "todo"+ " read book")
            + String.format("    %-11s: %s\n" , "", "event"+ " project meeting /at 30/10/2021 1800")
            + String.format("    %-11s: %s\n" , "", "deadline"+ " submission /by 2/11/2021 2359");


    public AddCommand(Task task) {
        super(task);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        ui.setSize(tasks.taskCount());
        ui.showAddMessage(task.toString());
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
