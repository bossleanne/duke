package todolist.data.command;

import todolist.data.TaskList;
import todolist.storage.Storage;
import todolist.ui.Ui;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public HelpCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        System.out.println(
                HelpCommand.MESSAGE_USAGE
                        + "\n" +AddCommand.MESSAGE_USAGE
                        + "\n" + DeleteCommand.MESSAGE_USAGE
                        + "\n" + DoneCommand.MESSAGE_USAGE
                        + "\n" + SearchCommand.MESSAGE_USAGE
                        + "\n" + ListCommand.MESSAGE_USAGE
                        + "\n" + ExitCommand.MESSAGE_USAGE
        );
    }
}
