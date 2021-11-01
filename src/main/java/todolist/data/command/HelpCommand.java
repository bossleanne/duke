package todolist.data.command;

import todolist.data.TaskList;
import todolist.storage.Storage;
import todolist.ui.DukeException;
import todolist.ui.Ui;
/**
 * Display TodoList usage instructions
 */
public class HelpCommand extends Command {

    public HelpCommand() {
        String instruction =
                "    ________________________________________________________________________________\n"
                        + "   |                            TodoList usage instructions:                        |\n"
                        + "   |________________________________________________________________________________|";
        System.out.println(
                instruction
                        + "\n" + AddCommand.MESSAGE_USAGE
                        + "\n" + DeleteCommand.MESSAGE_USAGE
                        + "\n" + DoneCommand.MESSAGE_USAGE
                        + "\n" + SearchCommand.MESSAGE_USAGE
                        + "\n" + ListCommand.MESSAGE_USAGE
                        + "\n" + ExitCommand.MESSAGE_USAGE
        );
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    }
}
