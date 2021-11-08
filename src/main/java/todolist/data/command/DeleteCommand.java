package todolist.data.command;

import todolist.data.TaskList;
import todolist.storage.Storage;
import todolist.ui.DukeException;
import todolist.ui.Ui;
/**
 * Deleting a task from the Tasklist by identified the displayed index from the todolist
 */
public class DeleteCommand extends Command{

    public static final String COMMAND_WORD = "Delete";
    /**
     * Shows the usage of delete
     */
    public static final String MESSAGE_USAGE =
            String.format("    %-11s: %s\n" , COMMAND_WORD, "Delete the task identified by the index number used in the todolist listing " )
            + String.format("    %-11s: %s\n" , "Parameters", "INDEX" )
            + String.format("    %-11s: %s\n" , "Example",  COMMAND_WORD.toLowerCase() + " 1");


    public DeleteCommand(int doneId) {
        super(doneId);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        /**
         * Break the execution if user trying to delete a non-exist task
         */
        assert(doneId > 0);
        if(doneId >= tasks.taskCount()){
            throw new DukeException(Ui.outOfIndex(tasks.taskCount()));
        } else{
            ui.setSize(tasks.taskCount()-1);
            ui.showDeleteMessage(tasks.getTasks(doneId).toString());
            tasks.deleteTask(doneId);
        }
    }

    @Override
    public boolean isExit(){
        return false;
    }

}
