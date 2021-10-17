

import todolist.Storage;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<Task>();

    private Storage storage;
//    private Task tasks;
//    private Ui todolist.ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            storage.checkThePath();
        }catch (IOException e){
            System.out.println("File not found");
        }
    }

    public void run(){
        greeting();
        getResponse();
    }


    private static final String DASHES =
                      "----------------------------------------"
                    + "----------------------------------------\n";

    public static void greeting(){
        System.out.println(
        DASHES
        + "Hello! Are you ready to start your day?\n"
        + "Enter today's task: \n"
        + DASHES);
    }

    public void getResponse(){
        Scanner in = new Scanner(System.in);
        String line = "";
        String taskStatus = "";
        String taskDescription = "";

        //check user input
        while (true){
            line = in.nextLine();
            //remove the whitespace from end
            line = line.stripTrailing();
            String[] taskSplit = line.split(" ",2);    //taskSplit[0] -> setTaskStatus

            //check the input and make sure it's not bye
            try{
                taskStatus = taskSplit[0].toUpperCase();
                if(line.contains("bye")){
                    System.out.println(
                            DASHES
                            + "Bye. Hope to see you again soon!\n"
                            + DASHES);
                    System.exit(0);
                }

                else if (taskSplit[0].contains("list")){
                    printList();
                }

                else if(!contains(taskStatus)){
                    throw new IllegalArgumentException("Invalid argument");
                }

                else {
                    taskDescription = taskSplit[1].stripLeading();
                    responseParse(taskStatus,taskDescription);
                }

            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println(
                        DASHES
                        +"☹ OOPS!!! The description of a "+taskStatus+" cannot be empty.\n"
                        +DASHES);
            }
            catch (IllegalArgumentException e){
                System.out.println(
                        DASHES
                        + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                        + DASHES);
            }
        }
    }

    public void responseParse(String taskStatus, String taskDescribe){
        //parse the Status of task
        Status status = Status.valueOf(taskStatus);
        String describe = taskDescribe;

        int doneId = 0;
        int mode = 0;

        try{
            switch (status){
                case TODO:
                    Todo todo = new Todo(describe);
                    todo.setTaskStatus("T");
                    tasks.add(todo);
                    System.out.println(DASHES+todo.toString());
                    storage.appendToFile(todo);
                    break;
                case DEADLINE:
                    String[] deadlineAndTime = describe.split("/by");
                    Deadline deadline = new Deadline(deadlineAndTime[0],deadlineAndTime[1]);
                    deadline.setTaskStatus("D");
                    tasks.add(deadline);
                    System.out.println(DASHES+deadline.toString());
                    storage.appendToFile(deadline);
                    break;
                case EVENT:
                    String[] eventAndTime = describe.split("/at");
                    Event event = new Event(eventAndTime[0],eventAndTime[1]);
                    event.setTaskStatus("E");
                    tasks.add(event);
                    System.out.println(DASHES+event.toString());
                    storage.appendToFile(event);
                    break;
                case DELETE:
                    doneId = Integer.parseInt(describe)-1;
                    tasks.get(doneId).markAsDone();
                    System.out.println(DASHES+"Noted. I've removed this task: \n" +
                            " ["+tasks.get(doneId).getStatusIcon()+"] " + tasks.get(doneId).getDescription());
                    tasks.remove(doneId);
                    storage.modifyFile(doneId+1,true);
                    break;
                case DONE:
                    doneId = Integer.parseInt(describe)-1;
                    tasks.get(doneId).markAsDone();
                    System.out.println(DASHES+"Nice! I've marked this task as done: \n" +
                            " ["+tasks.get(doneId).getStatusIcon()+"] " + tasks.get(doneId).getDescription());
                    storage.modifyFile(doneId+1,false);
                    break;
            }


//            if(mode>0){
//                todolist.storage.modifyFile(tasks.get(taskIndex).taskStatus,tasks.get(taskIndex).isDone,tasks.get(taskIndex).description,doneId,mode);
//            }else{
//                todolist.storage.appendToFile(tasks.get(taskIndex).taskStatus,tasks.get(taskIndex).isDone,tasks.get(taskIndex).description);
//            }

            System.out.println("Now you have "+tasks.size()+" tasks in the list.\n"+DASHES);
        }
         //check the input is not 0, is not exceed the total tasks index
         catch(NullPointerException e){
             System.out.println("There are only "+tasks.size()+" tasks, please enter the correct task number");
         }
         //check the 0 or negative
         catch(ArrayIndexOutOfBoundsException e){
             System.out.println("There are only "+tasks.size()+" tasks, please enter the correct task number");
         }
        catch(IndexOutOfBoundsException e){
            System.out.println("Task number entered: "+describe+" is invalid");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean contains(String test) {
        for (Status c : Status.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }


    public void printList(){
        String[] output = new String[100];
        System.out.print(DASHES);
        System.out.println("Here are the tasks in your list:");
        //print out all items in list
        for(int i = 0; i< tasks.size();i++){
            output = tasks.get(i).toString().split("\n ");
            System.out.println(i+1 + "."+output[1]);
        }
        System.out.println(DASHES);
    }

    public static void main(String[] args) {
//        try{
        Duke d = new Duke("/Users/leanne/duke/todolist.txt");
        d.run();
//        d.greeting();
//            getResponse();
//        }
//        catch(ArrayIndexOutOfBoundsException e){
//            System.out.println("Enter the location to the file");
//        }


    }
}
