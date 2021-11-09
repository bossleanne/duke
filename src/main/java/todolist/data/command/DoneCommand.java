package todolist.data.command;

import todolist.data.TaskList;
import todolist.storage.Storage;
import todolist.ui.DukeException;
import todolist.ui.Ui;
/**
 * Updating a task from the Tasklist by identified the displayed index from the todolist
 */
public class DoneCommand extends Command{

    public static final String COMMAND_WORD = "Done";

    /**
     * Shows the usage of done
     */
    public static final String MESSAGE_USAGE =
            String.format("    %-11s: %s\n" , COMMAND_WORD, "Make the task as done identified by the index number used in the todolist listing." )
                    + String.format("    %-11s: %s\n" , "Parameters", "INDEX" )
                    + String.format("    %-11s: %s\n" , "Example",  COMMAND_WORD.toLowerCase() + " 1");


    public DoneCommand(int taskId) {
        super(taskId);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (taskId < 0){
            throw new DukeException("Invalid number input");
        } else
        if(taskId >= tasks.taskCount()){
            throw new DukeException(Ui.outOfIndex(tasks.taskCount()));
        } else{
            tasks.finishTask(taskId);
            ui.showModifyMessage(tasks.getTasks(taskId).toString());
        }
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
