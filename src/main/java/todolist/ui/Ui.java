package todolist.ui;

import todolist.data.TaskList;

import java.util.Scanner;

public class Ui {
    private String DASHES =
            "----------------------------------------"
            + "----------------------------------------\n";

    private String ADDEDPREFIX = "Got it. I've added this task: ";
    private String DELETEPREFIX = "Noted. I've removed this task: ";
    private String DONEEPREFIX = "Nice! I've marked this task as done: ";
    private static Integer TASKSIZE = 0;
    private static String SUFFIX = ("Now you have "+"%1$d"+" tasks in the list.");

    private String SEARCH = "Here are the matching tasks in your list:";

    private String MESSAGE_GOODBYE = " Bye. Hope to see you again soon!";

    public DukeException dukeException;


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
        return String.format(SUFFIX, getSize());
    }

    public void showGoodbyeMessage() {
        System.out.println(
            showLine()
            + MESSAGE_GOODBYE+"\n"
            + showLine());
    }

    public void showAddMessage(String message) {
        showToUser(ADDEDPREFIX,message,getSuffix());
    }

    public void showDeleteMessage(String message) {
        showToUser(DELETEPREFIX,message,getSuffix());
    }
    public void showModifyMessage(String message) {
        showToUser(DONEEPREFIX,message,"");
    }
//    public void showFindMessage(String message) {
//        showToUser(message);
//    }

    //DIVIDER+MSG+DIVIDER;
    public void showToUser(String Prefix, String Message, String Suffix) {
        System.out.println(
                showLine()
                +Prefix + "\n"
                +Message +"\n"
                +Suffix + "\n"
                +showLine()
        );
    }


    public void showToUserAllTasks(TaskList taskList){ //showTaskListView

        System.out.print(DASHES);
        System.out.println("Here are the tasks in your list:");

        for(int i = 0; i< taskList.taskCount();i++){
            System.out.println(i+1 + "."+taskList.getTasks(i).toString());
        }
        System.out.println(DASHES);
    }


    public void showError(String errorMessage) {
       System.out.println(errorMessage);
    }

    public void showLoadingError(){
        System.out.println("File not found");
    }
}
