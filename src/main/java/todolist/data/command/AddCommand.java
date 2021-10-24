package todolist.data.command;

import todolist.data.TaskList;
import todolist.data.task.Task;
import todolist.storage.Storage;
import todolist.ui.Ui;

import java.io.IOException;

public class AddCommand extends Command{


    private static int addCount = 0;

    public AddCommand(Task task) {
        super(task);
    }



//    public void execute(TaskList tasks, Ui ui, Storage storage){
//
//
//
//        //tasks. sth sth can pass to newui,
//        ui.setSize(tasks.taskCount());
//        ui.showAddMessage(task.toString());
//
//        //display the action , either added task or delete task or modify the task
////        ui.showToUser(thingsToDisplay);
//        //storage.modifyFile(); TODO change the modifyFIle input arguments
//        //storage turn display to txt
//    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(task);
        ui.setSize(tasks.taskCount());
        ui.showAddMessage(task.toString());
        storage.appendToFile(task);
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
