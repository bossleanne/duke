package todolist.data.command;
/**
 * Represents the command executed by the user
 */
import todolist.data.TaskList;
import todolist.data.task.Task;
import todolist.storage.Storage;
import todolist.ui.DukeException;
import todolist.ui.Ui;

public abstract class Command {

    protected Task task;
    protected int doneId;
    /**
     * Initiate a Command object
     */
    public Command() {

    }
    /**
     * @param doneId listing index of the task
     */
    public Command(int doneId) {
        this.doneId = doneId;
    }

    /**
     * @param task need to be executed by the user
     */
    public Command(Task task){
        this.task = task;
    }

    /**
     * Executes the command
     * @param tasks take in Task object
     * @param ui take in Ui object to display the action
     * @param storage take in Storage object performing FIFO
     * @throws DukeException for any failed constrains.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * To terminate the program
     */
    public boolean isExit(){
        return false;
    }
}
