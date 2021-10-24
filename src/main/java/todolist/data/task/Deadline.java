package todolist.data.task;

public class Deadline extends Task {

    public static String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static String getBy(){
        return by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}