package todolist.parser;
/**
 * Parser package to parse user input and execute representative command.
 */
import todolist.data.command.AddCommand;
import todolist.data.command.Command;
import todolist.data.command.DeleteCommand;
import todolist.data.command.DoneCommand;
import todolist.data.command.ExitCommand;
import todolist.data.command.HelpCommand;
import todolist.data.command.ListCommand;
import todolist.data.command.SearchCommand;
import todolist.data.task.Deadline;
import todolist.data.task.Event;
import todolist.data.task.Status;
import todolist.data.task.Task;
import todolist.data.task.Todo;
import todolist.ui.DukeException;
import todolist.ui.Ui;

public class Parser {
    public static Task t;
    public static Ui ui;

    /**
     * Parses user input into command for execution.
     * @param fullCommand get the full line of user input string
     * @return Command object based on the user input
     * @throws DukeException for invalid input of content and incorrect index
     */
    public static Command parse(String fullCommand) throws DukeException {
        String line = fullCommand.stripTrailing();
        String[] taskSplit = line.split(" ",2);
        String taskStatus = taskSplit[0].toUpperCase();
        Status status;
        SearchCommand s;
        s = new SearchCommand();

        try{
            if(line.equals("bye")){
                return new ExitCommand(t);
            } else if (taskSplit[0].equals("list")){
                return new ListCommand(t);
            } else if (taskSplit[0].equals("help")){
                return new HelpCommand();
            } else {
                status = Status.valueOf(taskStatus);
                String taskDescription = taskSplit[1];

                switch (status){
                case TODO:
                    t = createToDo(taskDescription);
                    return new AddCommand(t);
                case DEADLINE:
                    t = createDeadline(taskDescription);
                    return new AddCommand(t);
                case EVENT:
                    t = createEvent(taskDescription);
                    return new AddCommand(t);
                case DELETE:
                    return new DeleteCommand(getTaskId(taskDescription));
                case DONE:
                    return new DoneCommand(getTaskId(taskDescription));
                case FIND:
                    s.setSearch(taskDescription);
                    return new SearchCommand();
                default:
                    return new HelpCommand();
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
            throw new DukeException(Ui.showIncorrectIndex(taskStatus));
        }
        catch (IllegalArgumentException e){
            throw new DukeException(Ui.inValidInput());
        }
    }
    /**
     * Generate new TODO task
     * @param fullCommand get the full description of todo string
     * @return Task object based on the user input
     */
    public static Task createToDo(String fullCommand){
        return new Todo(fullCommand);
    }
    /**
     * Generate new Event task
     * @param fullCommand get the full description of Event string
     * @return Task object based on the user input
     * @throws DukeException for invalid time and date constrains
     */
    public static Task createEvent(String fullCommand) throws DukeException{
        String[] eventAndTime = fullCommand.split("at");
        eventAndTime[0] = eventAndTime[0].replace(" /", "");
        String strDate = eventAndTime[1].trim();
//        if(!ParserDate.isDate(strDate)){
//            strDate = ParserDate.parseDate(strDate);
//        }
        return new Event(eventAndTime[0].trim(),strDate);
    }
    /**
     * Generate new Deadline task
     * @param fullCommand get the full description of Deadline string
     * @return Task object based on the user input
     * @throws DukeException for invalid time and date constrains
     */
    public static Task createDeadline(String fullCommand) throws DukeException {
        String[] deadlineAndTime = fullCommand.split("by");
        deadlineAndTime[0] = deadlineAndTime[0].replace(" /", "");
        String strDateTime = deadlineAndTime[1].trim();
        strDateTime = ParserDate.parseDate(strDateTime);
        return new Deadline(deadlineAndTime[0].trim(),strDateTime);
    }
    /**
     * Parses user input index and align it with the array index
     * @param fullCommand get the full description of Deadline string
     * @return the index of task display in list of tasks
     */
    public static int getTaskId(String fullCommand) {
        String doneIdStr= fullCommand.replaceAll("[^0-9]", "");
        return Integer.parseInt(doneIdStr)-1;
    }
}
