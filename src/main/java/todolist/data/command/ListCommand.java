package todolist.data.command;

import todolist.data.TaskList;
import todolist.data.task.Task;
import todolist.storage.Storage;
import todolist.ui.Ui;

public class ListCommand extends Command{

    public ListCommand(Task task) {
        super(task);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showToUserAllTasks(tasks);
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
