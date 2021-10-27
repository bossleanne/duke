package todolist.ui;

import todolist.data.TaskList;

import java.util.Scanner;

public class Ui {
    private static String DASHES =
            "----------------------------------------"
            + "----------------------------------------\n";

    private static String ADD_PREFIX = "Got it. I've added this task: ";
    private static String DELETE_PREFIX = "Noted. I've removed this task: ";
    private static String DONE_PREFIX = "Nice! I've marked this task as done: ";
    private static Integer TASKSIZE = 0;
    private static String TASK_SUFFIX = ("Now you have "+"%1$d"+" tasks in the list.");
    private static String ERROR_PREFIX = "☹ OOPS!!! ";
    private static String MESSAGE_SEARCH = "Here are the matching tasks in your list:";

    private static String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";

    public static DukeException dukeException;


    public String showLine(){
        return DASHES;
    }

    public void greeting(){
        System.out.println(
                showLine()
                        + "Hello! Are you ready to start your day?\n"
                        + "Enter today's task: \n"
                        + showLine());
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */

    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * @return command (full line) entered by the user
     */

    public String getUserCommand(){
        Scanner in = new Scanner(System.in);
        String command = "";
        command = in.nextLine();
        //remove the whitespace from end
        command = command.stripTrailing();
        while (shouldIgnore(command)) {
            command = in.nextLine();
        }
        return command;
    }

    public static Integer getSize() {
        return TASKSIZE;
    }

    public void setSize(Integer taskSize){
        this.TASKSIZE=taskSize;
    }

    public static String getSuffix() {
        return String.format(TASK_SUFFIX, getSize());
    }

    public void showGoodbyeMessage() {
        System.out.println(
                showLine()
                        + MESSAGE_GOODBYE+"\n"
                        + showLine());
    }

    public void showAddMessage(String message) {
        showToUser(ADD_PREFIX,message,getSuffix());
    }

    public void showDeleteMessage(String message) {
        showToUser(DELETE_PREFIX,message,getSuffix());
    }
    public void showModifyMessage(String message) {
        showToUser(DONE_PREFIX,message,"");
    }

    public void showToUser(String Prefix, String Message, String Suffix) {
        System.out.println(
                showLine()
                        +Prefix + "\n"
                        +Message +"\n"
                        +Suffix + "\n"
                        +showLine()
        );
    }

    public void showToUserAllTasks(TaskList taskList){

        System.out.print(DASHES);
        System.out.println(MESSAGE_SEARCH);

        for(int i = 0; i< taskList.taskCount();i++){
            System.out.println(i+1 + "."+taskList.getTasks(i).toString());
        }
        System.out.println(DASHES);
    }


    public void showError(String errorMessage) {
       System.out.println(errorMessage);
    }

    public void showLoadingError(){
        System.out.println("No tasks file found, process to take new tasks");
    }

    public static void showIncorrectInputs(String taskStatus){
        System.out.println(
                DASHES
                        + ERROR_PREFIX
                        +"The description of a "+taskStatus+" cannot be empty.\n"
                        +DASHES
        );
    }

    public static void showNonZero(){
        System.out.println(
                DASHES
                        + ERROR_PREFIX
                        +" I'm sorry, but I don't know what that means :-(\n"
                        +DASHES
        );
    }
}
