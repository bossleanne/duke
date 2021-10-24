package todolist.data;

import todolist.data.task.Task;
import java.util.ArrayList;

public class TaskList {
    //a list of tasks, handle tasks
    //add / delete / done todolist.data.task
    public ArrayList<Task> tasks = new ArrayList<Task>();
    public Integer idx = 0;


    public TaskList() {}

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Integer taskCount(){
        return tasks.size();
    }

    public void addTask(Task T){
        tasks.add(T);
    }

    public void finishTask(int doneId){
        tasks.get(doneId).markAsDone();
    }

    public void deleteTask(int doneId){
        tasks.remove(doneId);
    }

    public Task getTasks(Integer doneId){
        return tasks.get(doneId);
    }

}
