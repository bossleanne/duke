package todolist.parser;

import todolist.data.command.*;
import todolist.data.task.*;
import todolist.parser.time.ParserDate;
import todolist.ui.Ui;

public class Parser {


    public static Task t;
    public static Ui ui;

    public static Command parse(String fullCommand){
        String line = fullCommand.stripTrailing();
        String[] taskSplit = line.split(" ",2);    //taskSplit[0] -> setTaskStatus

        String taskStatus = taskSplit[0].toUpperCase();
        Status status;

        SearchCommand s = new SearchCommand();

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
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
            ui.showIncorrectInputs(taskStatus);
        }
        catch (IllegalArgumentException e){
            ui.showNonZero();
        }
        return new ContinueCommand();
    }

    public static Task createToDo(String fullCommand){
        Todo todo = new Todo(fullCommand);
        return todo;
    }


    public static Task createEvent(String fullCommand){
        String[] eventAndTime = fullCommand.split("at");
        eventAndTime[0] = eventAndTime[0].replace(" /", "");
        String strDate = eventAndTime[1].trim();
        if(!ParserDate.isDate(strDate)){
            strDate = ParserDate.parseDate(strDate);
        }
        Event event = new Event(eventAndTime[0].trim(),strDate);
        return event;
    }


    public static Task createDeadline(String fullCommand){
        String[] deadlineAndTime = fullCommand.split("by");
        deadlineAndTime[0] = deadlineAndTime[0].replace(" /", "");
        String strDate = deadlineAndTime[1].trim();
        if(!ParserDate.isDate(strDate)){
            strDate = ParserDate.parseDate(strDate);
        }
        Deadline deadline = new Deadline(deadlineAndTime[0].trim(),strDate);
        return deadline;
    }

    public static int getTaskId(String fullCommand){
        String doneIdStr= fullCommand.replaceAll("[^0-9]", "");
        int doneId = Integer.parseInt(doneIdStr)-1;
        return doneId;
    }
}
