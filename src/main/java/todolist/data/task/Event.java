package todolist.data.task;

/**
 * Represents an Event task in the TodoList
 */
public class Event extends Task {

    protected static String at;

    public Event(String description, String at) {
        super(description);
        super.setTaskStatus("E");
        this.at = at;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Override the toString method in parents
     * @return time string
     */
    @Override
    public String getTime(){
        return at;
    }
    /**
     * Override the toString method in parents
     * @return a string of show content arranged in a formatted way
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + getTime() + ")";
    }
}
