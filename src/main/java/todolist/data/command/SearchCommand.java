package todolist.data.command;

import todolist.data.TaskList;
import todolist.data.task.Task;
import todolist.storage.Storage;
import todolist.ui.Ui;

import java.io.IOException;

public class SearchCommand extends Command{

    private String search;

    public SearchCommand(Task task) {
        super(task);
    }

    public void setSearch(String search){
        this.search = search;
    }

    public String getSearch(){
        return search;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {

        TaskList result = new TaskList();

        for(int i = 0; i< tasks.taskCount();i++){
            if(tasks.getTasks(i).getDescription() != null && tasks.getTasks(i).getDescription().contains(getSearch())){
                result.addTask(tasks.getTasks(i));
            }
        }

        ui.showToUserAllTasks(result);


    }

}
