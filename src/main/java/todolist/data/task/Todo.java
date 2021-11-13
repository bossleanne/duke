package todolist.data.task;
/**
 * Represents a todo task in the TodoList
 */
public class Todo extends Task {
    protected boolean isDone;
    public static final String COMMAND_WORD = "Todo";

    public Todo(String description) {
        super(description);
        super.setTaskStatus("T");
        isDone = false;
    }
}