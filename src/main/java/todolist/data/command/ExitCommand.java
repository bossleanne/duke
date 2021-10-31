package todolist.data.command;

import todolist.data.TaskList;
import todolist.data.task.Task;
import todolist.storage.Storage;
import todolist.ui.DukeException;
import todolist.ui.Ui;

import java.io.IOException;

public class ExitCommand extends Command{

    public static final String COMMAND_WORD = "Exit";

    public static final String MESSAGE_USAGE =
            String.format("    %-11s: %s\n" , COMMAND_WORD, "Exits the program." )
                    + String.format("    %-11s: %s\n" , "Parameters", "" )
                    + String.format("    %-11s: %s\n" , "Example",  "bye");


    public ExitCommand(Task task) {
        super(task);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.store(tasks);
        ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit(){
        return true;
    }
}
