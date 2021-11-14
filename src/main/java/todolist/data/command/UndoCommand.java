package todolist.data.command;

import todolist.data.TaskList;
import todolist.storage.Storage;
import todolist.ui.DukeException;
import todolist.ui.Ui;

import java.util.ArrayList;

/**
 * Undo the last step of user input
 */

public class UndoCommand extends Command{

    public static final String COMMAND_WORD = "Undo";
    /**
     * Shows the usage of undo
     */
    public static final String MESSAGE_USAGE =
            String.format("    %-11s: %s\n" , COMMAND_WORD, "Undo the last step of user input " )
                    + String.format("    %-11s: %s\n" , "Example",  COMMAND_WORD.toLowerCase());

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try{
            ArrayList<String> lastCommand = storage.getLogCommand();
            String printOutMsg = "Undo the last action "
                    +tasks.getTasks(tasks.taskCount()-1).toString();

            switch (lastCommand.get(lastCommand.size() - 1)){
                case "add":
//                    printOutMsg = "Undo the last action add "
//                            +tasks.getTasks(tasks.taskCount()-1).toString();
                    tasks.deleteTask( tasks.taskCount()-1);
                    break;
                case "delete":
//                    printOutMsg = "Undo the last action delete "
//                            +tasks.getTasks(tasks.taskCount()-1).toString();
                    tasks.addTask( DeleteCommand.tempDelete);
                    break;
                case "done":
//                    printOutMsg = "Undo the last action done "
//                            +tasks.getTasks(tasks.taskCount()-1).toString();
                    tasks.getTasks(DoneCommand.tempDone).setIsDone(false);
                    break;
            }
            ui.setSize(tasks.taskCount());
            ui.showToUserAllTasks(tasks);
//            System.out.print(printOutMsg);
//            System.out.println(ui.getSuffix());
        }catch (IndexOutOfBoundsException  e){
            throw new DukeException("This action cannot be undone");
        }

    }
    @Override
    public boolean isExit(){
        return false;
    }

}
