package todolist.data.command;

import todolist.data.TaskList;
import todolist.data.task.Task;
import todolist.storage.Storage;
import todolist.ui.Ui;

import java.io.IOException;

public class ExitCommand extends Command{

    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "Example: " + COMMAND_WORD;

    public ExitCommand(Task task) {
        super(task);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.store(tasks);
        ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit(){
        return true;
    }
}
