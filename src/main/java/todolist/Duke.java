package todolist;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
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
    //#### test out GUI
//    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
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

    public static void main(String[] args) {
        Duke duke = new Duke("/Users/leanne/duke/todolist.txt");
        duke.run();
    }
}
