package todolist.data.task;


public class Todo extends Task {
    protected boolean isDone;

    public Todo(String description) {
        super(description);
        super.setTaskStatus("T");
        isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

//    @Override
//    public String toString() {
//        return super.toString();
//    }
}