package todolist.ui;

import todolist.data.TaskList;
import todolist.data.command.HelpCommand;

import java.util.Scanner;
/**
 * TodoList application's command-line user interface.
 */

public class Ui {
    /** Line separator. */
    private static final String DASHES =
            "________________________________________________________________________________\n";
    /**Prefix used to add to the beginning of lines printed by TodoList when adding task */
    private static final String ADD_PREFIX = "Got it. I've added this task: ";
    /**Prefix used to add to the beginning of lines printed by TodoList when deleting task */
    private static final String DELETE_PREFIX = "Noted. I've removed this task: ";
    /**Prefix used to add to the beginning of lines printed by TodoList when uppdate task */
    private static final String DONE_PREFIX = "Nice! I've marked this task as done: ";
    /**Suffix used to add to the end of lines printed by TodoList*/
    private static final String TASK_SUFFIX = ("\nNow you have "+"%1$d"+" tasks in the list.");
    /**Prefix used to add to the end of lines printed by TodoList when encounter error*/
    private static final String ERROR_PREFIX = "☹ OOPS!!! ";
    private static final String MESSAGE_SEARCH = "Here are the matching tasks in your list:";
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    /** Size of tasks inside TodoList program*/
    private static Integer TASK_SIZE = 0;

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    static boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * @return command (full line) entered by the user
     */
    public String getUserCommand(){
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        command = command.trim();
        while (shouldIgnore(command)) {
            command = in.nextLine();
        }
        return command;
    }

    public static String getSuffix() {
        return String.format(TASK_SUFFIX, TASK_SIZE);
    }
    public void setSize(Integer taskSize){
        TASK_SIZE=taskSize;
    }

    /**
     * Prints welcome messages at the start of the application.
     */
    public void greeting(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println(DASHES
                + logo
                + " Hello! I'm Duke todolist apps, are you ready to start your day?\n"
                + " Here are the usage of this application:");
        new HelpCommand();
        System.out.println(DASHES);
    }
    public void showGoodbyeMessage() {
        System.out.println(DASHES + MESSAGE_GOODBYE);
    }
    public static void showMessage(String commonMessage) {
        System.out.println(DASHES+commonMessage);
    }
    /**
     * Display the result when take command execution from the user.
     */
    public void showAddMessage(String message) {
//        showToUser(ADD_PREFIX,message,getSuffix());
        showToUser(ADD_PREFIX,message,getSuffix());
    }
    public void showDeleteMessage(String message) {
        showToUser(DELETE_PREFIX,message,getSuffix());
    }
    public void showModifyMessage(String message) {
        showToUser(DONE_PREFIX,message,"");
    }
    public void showSearchMessage() {
        System.out.println(MESSAGE_SEARCH);
    }
    public void showLine() {System.out.println(DASHES);}

    /** Shows different error message(s) to the user when {@code DukeException} is triggered*/
    public void showError(String errorMessage) {
        System.out.println(DASHES + ERROR_PREFIX+errorMessage);
    }
    public void showLoadingError(){
        System.out.println( DASHES + ERROR_PREFIX+"No tasks file found, process to take new tasks\n"+DASHES);
    }
    public static String showIncorrectIndex(String taskStatus){
        return "The description of a "+taskStatus+" cannot be empty.";
    }
    public void showEmptyTask(){
        System.out.println( DASHES + ERROR_PREFIX+"No tasks in the list, process to take new tasks");
    }
    public static String inValidInput(){
        return " I'm sorry, but I don't know what that means :-(";
    }
    public static String outOfIndex(int taskCount){
        return "There are only "+taskCount+" tasks, please enter the correct task index";
    }

    /** Shows single task to the user
     * @param Prefix print before description
     * @param Message print the description of a task
     * @param Suffix print after the description
     * */
    public void showToUser(String Prefix, String Message, String Suffix) {
        System.out.println(DASHES
                        +Prefix + "\n"
                        +Message
                        +Suffix
                        );
    }
    /** Shows a list of tasks to the user, formatted as an indexed list.
     *  Formats a string as a viewable indexed list item.
     * */
    public void showToUserAllTasks(TaskList taskList){
        System.out.print(DASHES);
        for(int i = 0; i< taskList.taskCount();i++){
            System.out.println(i+1 + "."+taskList.getTasks(i).toString());
        }
    }
}
