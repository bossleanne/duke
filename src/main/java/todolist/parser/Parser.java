package todolist.parser;
/**
 * Parser package to parse user input and execute representative command.
 */
import todolist.data.command.*;
import todolist.data.task.*;
import todolist.ui.DukeException;
import todolist.ui.Ui;
import todolist.utils.Matching;

public class Parser {
    public static Task t;
    public static Ui ui;
    public static ParserDate p = new ParserDate();
    private static boolean isLoadedTask = false;

    public static void setLoadedTask(){
        isLoadedTask = true;
    }

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
        try{
            if(line.equals("bye")){
                return new ExitCommand(t);
            } else if (taskSplit[0].equals("list")){
                return new ListCommand();
            } else if (taskSplit[0].equals("help")){
                return new HelpCommand();
            } else if (taskSplit[0].equals("undo")){
                    return new UndoCommand();
            } else{
                status = Status.valueOf(taskStatus);
                String taskDescription = taskSplit[1].strip();

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
                    return new DeleteCommand(getValidTaskId(taskDescription));
                case DONE:
                    return new DoneCommand(getValidTaskId(taskDescription));
                case FIND:
                    SearchCommand s = new SearchCommand();
                    s.setSearch(taskDescription);
                    return s;
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
        try {
            String[] eventAndTime = fullCommand.split("at");
            eventAndTime[0] = eventAndTime[0].replace(" /", "");
            String strDateTime = eventAndTime[1].trim();
            if(!isLoadedTask){
                strDateTime = p.parseDate(strDateTime);
            } else{
                isLoadedTask = false;
            }
            return new Event(eventAndTime[0].trim(), strDateTime);
        }catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("No date or time founded, please re-enter");
        }
    }
    /**
     * Generate new Deadline task
     * @param fullCommand get the full description of Deadline string
     * @return Task object based on the user input
     * @throws DukeException for invalid time and date constrains
     */
    public static Task createDeadline(String fullCommand) throws DukeException {
        try{
            String[] deadlineAndTime = fullCommand.split("by");
            deadlineAndTime[0] = deadlineAndTime[0].replace(" /", "");
            String strDateTime = deadlineAndTime[1].trim();
            if(!isLoadedTask){
                strDateTime = p.parseDate(strDateTime);
            } else{
                isLoadedTask = false;
            }
            return new Deadline(deadlineAndTime[0].trim(),strDateTime);
        }catch (ArrayIndexOutOfBoundsException e){
           throw new DukeException("No date or time founded, please re-enter");
        }

    }
    /**
     * Parses user input index and align it with the array index
     * @param fullCommand get the full description of Deadline string
     * @return the index of task display in list of tasks
     */
    public static int getValidTaskId(String fullCommand) throws DukeException{
        int taskId = Integer.parseInt(fullCommand)-1;
        if(Matching.getMatch(fullCommand, "^[0-9]+$")=="-1"){
            throw new DukeException(Ui.inValidInput());
        }else if(taskId < 0){
            throw new DukeException("Invalid number input");
        }
        return taskId;
    }
}
