package todolist.data;
/**
 * Represent for TodoList's tasks
 */
import todolist.data.task.Task;
import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasks = new ArrayList<Task>();
    /**
     * Initiate an empty todolist tasks
     */
    public TaskList() {}
    /**
     * Initiate an empty todolist tasks
     * @param tasks tasks store in arraylist
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Adding a task to the TaskList
     */
    public void addTask(Task T){
        tasks.add(T);
    }
    /**
     * Deleting a task to the TaskList
     */
    public void deleteTask(int doneId){
        tasks.remove(doneId);
    }
    /**
     * Updating a task to the TaskList
     */
    public void finishTask(int doneId){
        tasks.get(doneId).markAsDone();
    }
    /**
     * Getting a task to the TaskList
     */
    public Task getTasks(Integer doneId){
        return tasks.get(doneId);
    }
    /**
     * Counting how many tasks inside the TaskList
     * @return total size of the tasklist
     */
    public Integer taskCount(){
        return tasks.size();
    }
}
