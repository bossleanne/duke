package todolist.data.command;

import todolist.data.TaskList;
import todolist.data.task.Task;
import todolist.storage.Storage;
import todolist.ui.Ui;

import java.io.IOException;

public abstract class Command {

    protected Task task;
    protected int doneId;

    public Command(int doneId) {
        this.doneId = doneId;
    }


    public Command(Task task){
        this.task = task;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    public boolean isExit(){
        return false;
    }


}
