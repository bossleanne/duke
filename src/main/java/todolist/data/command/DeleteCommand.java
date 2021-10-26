package todolist.data.command;

import todolist.data.TaskList;
import todolist.storage.Storage;
import todolist.ui.Ui;

public class DeleteCommand extends Command{

    public DeleteCommand(int doneId) {
        super(doneId);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.setSize(tasks.taskCount()-1);
        ui.showDeleteMessage(tasks.getTasks(doneId).toString());
        tasks.deleteTask(doneId);
//        storage.modifyFile();
    }

    @Override
    public boolean isExit(){
        return false;
    }

}
