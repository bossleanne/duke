package todolist.data.task;

/**
 * Represents a Deadline task in the TodoList
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        super.setTaskStatus("D");
        this.by = by;
    }
    /**
     * Override the toString method in parents
     * @return time string
     */
    @Override
    public String getTime(){
        return by;
    }
    /**
     * Override the toString method in parents
     * @return a string of show content arranged in a formatted way
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + getTime() + ")";
    }
}