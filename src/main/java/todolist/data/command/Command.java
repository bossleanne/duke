package todolist.data.command;

import todolist.data.TaskList;
import todolist.data.task.Task;
import todolist.storage.Storage;
import todolist.ui.Ui;

import java.io.IOException;

public abstract class Command {

    protected Task task;
    protected int doneId;

    //    protected String[] thingsToDisplay = new String[100];
//    protected boolean flag = false;
//    public Command() {}
    public Command(int doneId) {
        this.doneId = doneId;
    }


    public Command(Task task){
        this.task = task;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
//    public void execute(TaskList tasks, Ui ui, Storage storage){

//
//        //display the action , either added task or delete task or modify the task
////        ui.showToUser(thingsToDisplay);
//        //storage.modifyFile(); TODO change the modifyFIle input arguments
//        //storage turn display to txt
//    }

    public boolean isExit(){
        return false;
    }


}
