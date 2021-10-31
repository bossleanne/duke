package todolist.data.command;

import todolist.data.TaskList;
import todolist.data.task.Task;
import todolist.storage.Storage;
import todolist.ui.DukeException;
import todolist.ui.Ui;

public abstract class Command {

    protected Task task;
    protected int doneId;

    public Command() {

    }

    public Command(int doneId) {
        this.doneId = doneId;
    }

    public Command(Task task){
        this.task = task;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

//    public abstract void unExecute();

    public boolean isExit(){
        return false;
    }


}
