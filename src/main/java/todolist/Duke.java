package todolist;

import todolist.data.TaskList;
import todolist.storage.Storage;
import todolist.ui.Ui;
import todolist.parser.Parser;
import todolist.data.command.Command;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    public TaskList tasks;
    public Storage storage;
    public Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    public void run(){
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
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
