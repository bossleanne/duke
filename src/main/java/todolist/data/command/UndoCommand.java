package todolist.data.command;

import todolist.data.TaskList;
import todolist.storage.Storage;
import todolist.ui.DukeException;
import todolist.ui.Ui;

import java.util.ArrayList;

/**
 * Undo the last step of user input
 */

public class UndoCommand extends Command{

    public static final String COMMAND_WORD = "Undo";
    /**
     * Shows the usage of undo
     */
    public static final String MESSAGE_USAGE =
            String.format("    %-11s: %s\n" , COMMAND_WORD, "Undo the last step of user input " )
                    + String.format("    %-11s: %s\n" , "Example",  COMMAND_WORD.toLowerCase());

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<String> lastCommand = storage.getLogCommand();
        String printOutMsg = "Undo the last task "
                +tasks.getTasks(tasks.taskCount()-1).toString();

        switch (lastCommand.get(lastCommand.size() - 1)){
            case "add":
                tasks.deleteTask( tasks.taskCount()-1);
                break;
            case "delete":
                tasks.addTask( DeleteCommand.tempDelete);
                break;
            case "done":
                tasks.getTasks(DoneCommand.tempDone).setIsDone(false);
                break;
        }
        ui.setSize(tasks.taskCount());
        System.out.print(printOutMsg);
        System.out.println(ui.getSuffix());
    }
    @Override
    public boolean isExit(){
        return false;
    }

}
