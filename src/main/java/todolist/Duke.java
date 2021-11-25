package todolist;

import todolist.data.TaskList;
import todolist.data.command.Command;
import todolist.parser.Parser;
import todolist.storage.Storage;
import todolist.ui.DukeException;
import todolist.ui.Ui;

/**
 * The Duke program implements an application that record
 * list of tasks user need to complete, or things that they  want to do
 *
 * @author  Sun Li
 * @version 1.0
 * @since   2021-09-18
 */

public class Duke{
    public TaskList tasks;
    public Storage storage;
    public Ui ui;

    /**
     * Entry point of the TODO list application.
     * Initializes the application and starts the interaction with the user.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /** Runs the program until termination.  */
    public void run(){
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        try{
            Duke duke = new Duke(args[0].strip());
            duke.run();
        }catch(ArrayIndexOutOfBoundsException e){
            throw new DukeException("\n     Please enter the output folder, i.e. User/Duke");
        }

    }
}
