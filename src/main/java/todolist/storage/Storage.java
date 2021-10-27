package todolist.storage;

import todolist.data.TaskList;
import todolist.data.task.Task;
import todolist.parser.Parser;
import todolist.ui.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage{

    public static String filePath;
    public static File f;
    public static String textToAppend = "";
    public static String splitBy = " | ";


    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static void checkThePath() throws IOException {

        f = new File(filePath);
        String tmpPath = f.getAbsolutePath();
        String path = "";
        String file = "";
        int lastSlashPos = tmpPath.lastIndexOf('/');

        path = tmpPath.substring(0, lastSlashPos);
        file = tmpPath.substring(lastSlashPos + 1, tmpPath.length());

        //check the validity of the path
        if (!Files.isDirectory(Paths.get(path))) {
            File newfile = new File(path);
            System.out.println(f.getParent() + " has has been created");
            newfile.mkdir();
        }

        if (f.createNewFile()) {
            System.out.println(file + " has been created.");
        }
    }

    public static ArrayList<Task> load() throws IOException, DukeException {

        ArrayList<Task> loadTasks = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String line = s.nextLine();
            String[] components = line.split(" \\| ");
            String taskStatus = components[0];
            boolean isDone = getBooleanNum(components[1]);
            String taskDescription = components[2];
            Task loadTask;

            switch (taskStatus) {
                case "T":
                    loadTask = Parser.createToDo(taskDescription);
                    break;
                case "E":
                    loadTask = Parser.createEvent(taskDescription);
                    break;
                case "D":
                    loadTask = Parser.createDeadline(taskDescription);
                    break;
                default:
                    throw new DukeException("Unexpected value: " + taskStatus);
            }
            loadTask.setIsDone(isDone);
            loadTasks.add(loadTask);
        }

        return loadTasks;
    }

    public static void store(TaskList tasks) throws IOException {
        checkThePath();
        String textToAppend = "";
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
        for (Task task :tasks.tasks){
            textToAppend = appendToFile(task);
            writeToFile(filePath,textToAppend);
        }
    }

    private static void writeToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(textToAppend);
        fw.close();
    }


    public static String appendToFile(Task task){

        textToAppend = task.getTaskStatus()+
                splitBy+getStringNum(task.getIsDone());
        if(task.getTaskStatus().equals("D")){
            textToAppend += splitBy + task.getDescription()+" by "+task.getTime();
        }
        else if(task.getTaskStatus().equals("E")){
            textToAppend += splitBy + task.getDescription()+" at "+task.getTime();
        }else{
            textToAppend += splitBy+task.getDescription();
        }
        return textToAppend+System.lineSeparator();

    }

    public static String getStringNum(Boolean isDone) {
        return (isDone ? "1" : "0"); // mark done task with X
    }

    public static boolean getBooleanNum(String isDone) {
        if(isDone.equals("1")){
            return true;
        }
        return false;
    }

}