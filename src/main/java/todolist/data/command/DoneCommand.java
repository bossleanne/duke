package todolist.data.command;

import todolist.data.TaskList;
import todolist.storage.Storage;
import todolist.ui.Ui;

public class DoneCommand extends Command{


    public DoneCommand(int doneId) {
        super(doneId);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.finishTask(doneId);
        ui.showModifyMessage(tasks.getTasks(doneId).toString());
//        storage.modifyFile();
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
