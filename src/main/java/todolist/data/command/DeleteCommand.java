package todolist.data.command;

import todolist.data.TaskList;
import todolist.data.task.Task;
import todolist.storage.Storage;
import todolist.ui.DukeException;
import todolist.ui.Ui;
/**
 * Deleting a task from the Tasklist by identified the displayed index from the todolist
 */
public class DeleteCommand extends Command{

    public static final String COMMAND_WORD = "Delete";
    public static Task tempDelete;
    /**
     * Shows the usage of delete
     */
    public static final String MESSAGE_USAGE =
            String.format("    %-11s: %s\n" , COMMAND_WORD, "Delete the task identified by the index number used in the todolist listing " )
            + String.format("    %-11s: %s\n" , "Parameters", "INDEX" )
            + String.format("    %-11s: %s\n" , "Example",  COMMAND_WORD.toLowerCase() + " 1");


    public DeleteCommand(int taskId) {
        super(taskId);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        /**
         * Break the execution if user trying to delete a task id that not exist in list
         */
        assert(taskId < tasks.taskCount());
        tempDelete = tasks.getTasks(taskId);
        ui.setSize(tasks.taskCount()-1);
        ui.showDeleteMessage(tasks.getTasks(taskId).toString());
        tasks.deleteTask(taskId);
        storage.logCommand("delete");
    }

    @Override
    public boolean isExit(){
        return false;
    }

}
