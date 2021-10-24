package todolist;

import todolist.data.TaskList;
import todolist.data.command.Command;
import todolist.parser.Parser;
import todolist.storage.Storage;
import todolist.ui.Ui;

import java.io.IOException;

public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;
//    private Parser parser;
//    private DukeException DukeException;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

//        try {
//            tasks = new TaskList(storage.load());
//        }catch (IOException e){
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
        tasks = new TaskList();
    }

    public void run(){

        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Command c = Parser.parse(fullCommand);     //suppose return some command that, e.g add todotask
                c.execute(tasks, ui, storage); //call add command by right
                // but how to add the dedicated task to tasksList>
                isExit = c.isExit();
            } catch (IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

    }


    public static void main(String[] args) {
        new Duke("/Users/leanne/duke/todolist.txt").run();
    }
}
