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
    public void deleteTask(int taskId){
        tasks.remove(taskId);
    }
    /**
     * Updating a task to the TaskList
     */
    public void finishTask(int taskId){
        tasks.get(taskId).markAsDone();
    }
    /**
     * Getting a task to the TaskList
     */
    public Task getTasks(int taskId){
        return tasks.get(taskId);
    }
    /**
     * Counting how many tasks inside the TaskList
     * @return total size of the tasklist
     */
    public Integer taskCount(){
        return tasks.size();
    }
}
