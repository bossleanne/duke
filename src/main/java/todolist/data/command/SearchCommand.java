package todolist.data.command;

import todolist.data.TaskList;
import todolist.storage.Storage;
import todolist.ui.Ui;

import java.io.IOException;

public class SearchCommand extends Command{

    private static String search;

    public void setSearch(String search){
        this.search = search;
    }

    public static String getSearch(){
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
