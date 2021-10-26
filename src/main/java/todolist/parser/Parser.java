package todolist.parser;

import todolist.data.command.*;
import todolist.data.task.*;


public class Parser {


    public static Task t;

    public static Command parse(String fullCommand){
        String line;
        String taskStatus = "";

        line = fullCommand.stripTrailing();
        String[] taskSplit = line.split(" ",2);    //taskSplit[0] -> setTaskStatus
        String taskDescription = taskSplit[1];
        Status status;

        try{
            taskStatus = taskSplit[0].toUpperCase();
            if(line.contains("bye")){
                return new ExitCommand(t);
            }

            if (taskSplit[0].contains("list")){
                System.out.println("HERE!!!!!!!");
//                System.out.println("HERE!!!!!!!");
                return new ListCommand(t);
            }
            else if (taskSplit[0].contains("find")){
                SearchCommand s = new SearchCommand(t);
                s.setSearch(taskSplit[1]);
                return new SearchCommand(t);
            }

            else if(!contains(taskStatus)){
                throw new IllegalArgumentException("Invalid argument");
            }

            else {
                status = Status.valueOf(taskStatus);
                switch (status){
                case TODO:
                    t = createToDo(taskDescription);
                    break;
                case DEADLINE:
                    t = createDeadline(taskDescription);
                    break;
                case EVENT:
                    t = createEvent(taskDescription);
                    break;
                case DELETE:
                    return new DeleteCommand(getTaskId(taskDescription));

                case DONE:
                    //if the number big than the input number throw error
                    return new DoneCommand(getTaskId(taskDescription));
                }
                return new AddCommand(t);
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println(
                    "DASHES"
                            +"☹ OOPS!!! The description of a "+taskStatus+" cannot be empty.\n"
                            +"DASHES");
        }
        catch (IllegalArgumentException e){
            System.out.println(
                    "DASHES"
                            + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                            + "DASHES");
        }
        //check the input is not 0, is not exceed the total tasks index
        catch(NullPointerException e){
            System.out.println("There are only "+"tasks.taskCount()"+" tasks, please enter the correct task number");
        }
        //check the 0 or negative
//        catch(ArrayIndexOutOfBoundsException e){
//            System.out.println("There are only "+"tasks.taskCount()"+" tasks, please enter the correct task number");
//        }
//        catch(IndexOutOfBoundsException e){
//            System.out.println("todolist.data.task.Task number entered: "+taskDescription+" is invalid");
//        }

        return new ExitCommand(t);
    }


    public static boolean contains(String test) {
        for (Status c : Status.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

    public static Task createToDo(String fullCommand){
        Todo todo = new Todo(fullCommand);
        todo.setTaskStatus("T");
        return todo;
    }


    public static Task createEvent(String fullCommand){
        String[] eventAndTime = fullCommand.split("/at");
        Event event = new Event(eventAndTime[0],eventAndTime[1]);
        event.setTaskStatus("E");
        return event;
    }


    public static Task createDeadline(String fullCommand){
        String[] deadlineAndTime = fullCommand.split("/by");
        Deadline deadline = new Deadline(deadlineAndTime[0],deadlineAndTime[1]);
        deadline.setTaskStatus("D");
        return deadline;
    }

    public static int getTaskId(String fullCommand){
        String doneIdStr= fullCommand.replaceAll("[^0-9]", "");
        int doneId = Integer.parseInt(doneIdStr)-1;
        return doneId;
    }
}
