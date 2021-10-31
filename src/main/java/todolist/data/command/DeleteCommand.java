package todolist.data.command;

import todolist.data.TaskList;
import todolist.storage.Storage;
import todolist.ui.DukeException;
import todolist.ui.Ui;

public class DeleteCommand extends Command{

    public static final String COMMAND_WORD = "Delete";

    public static final String MESSAGE_USAGE =
            String.format("    %-11s: %s\n" , COMMAND_WORD, "Delete the task identified by the index number used in the todolist listing " )
            + String.format("    %-11s: %s\n" , "Parameters", "INDEX" )
            + String.format("    %-11s: %s\n" , "Example",  COMMAND_WORD.toLowerCase() + " 1");


    public DeleteCommand(int doneId) {
        super(doneId);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        try{
            if (doneId < 0){
                throw new DukeException("Invalid number input");
            } else
            if(doneId >= tasks.taskCount()){
                throw new DukeException("There are only "+tasks.taskCount()+" tasks, please enter the correct task number");
            } else{
                ui.setSize(tasks.taskCount()-1);
                ui.showDeleteMessage(tasks.getTasks(doneId).toString());
                tasks.deleteTask(doneId);
            }
        }catch ( DukeException e){
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit(){
        return false;
    }

}
