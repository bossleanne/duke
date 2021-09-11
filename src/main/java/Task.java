import java.util.Arrays;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskStatus;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskStatus=" ";
    }

    public String getDescription() {
        return description;
    }

    public void setIsDone(boolean done){
        this.isDone = done;
    }

    public void setTaskStatus(String status){
        this.taskStatus = status;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskStatus(){
        return taskStatus;
    }

    public void markAsDone(){
        setIsDone(true);
    }


    public String toString() {
        return "["+getTaskStatus()+"]"+"["+getStatusIcon()+"] "+getDescription();
    }

}
