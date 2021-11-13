package todolist.data.task;
/**
 * Represents Task in the TodoList
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskStatus;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskStatus=" ";
    }

    /**
     * Getters and setters to access to the Task object
     */
    public String getDescription() {
        return description;
    }
    public String getTaskStatus(){
        return taskStatus;
    }
    public String getTime(){
        return "";
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    public void setIsDone(boolean done){
        this.isDone = done;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public void setTaskStatus(String status){
        this.taskStatus = status;
    }

    public void markAsDone(){
        setIsDone(true);
    }
    /**
     * Override the toString method in parents
     * @return a string of show content arranged in a formatted way
     */
    public String toString() {
        return "["+getTaskStatus()+"]"+"["+getStatusIcon()+"] "+getDescription();
    }

}
