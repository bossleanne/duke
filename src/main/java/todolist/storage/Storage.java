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

/**
 * Represents the file used to store todo list data
 * A <code>Storage</code> object that can export list of object to a txt file
 * and load the txt file to a list of tasks
 */

public class Storage{

    public static String filePath;
    public static File f;
    public static String textToAppend = "";
    public static String splitBy = " | ";
    public static ArrayList<String> logString = new ArrayList<String>();

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * @throws DukeException if the path is invalid
     */
    public static void checkThePath() throws DukeException {
        f = new File(filePath);
        String tmpPath = f.getAbsolutePath();
        int lastSlashPos = tmpPath.lastIndexOf('/');
        String path = tmpPath.substring(0, lastSlashPos);
        String file = tmpPath.substring(lastSlashPos + 1, tmpPath.length());

        if (!Files.isDirectory(Paths.get(path))) {
            File newFile = new File(path);
            newFile.mkdir();
        }

        try{
            f.createNewFile();
        }catch (IOException e){
            throw new DukeException("File not found");

        }
    }
    /**
     * @throws DukeException if the given file is not founded
     * and/or has no tasks inside the file
     */
    public static ArrayList<Task> load() throws DukeException {
        try {
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
                Parser.setLoadedTask();

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
        }catch (IOException e){
            throw new DukeException(e.getMessage());
        }
    }
    /**
     * Encodes all the {@code Task} in the {@code TaskList} into a list readable string presentation and storage as a txt file.
     * @throws IOException if the file not exist
     */
    public static void store(TaskList tasks) throws DukeException {
        try{
            checkThePath();
            FileWriter fw = new FileWriter(filePath);
            fw.write("");
            fw.close();
            for (Task task :tasks.tasks){
                String textToAppend = appendToFile(task);
                writeToFile(filePath,textToAppend);
            }
        }catch (IOException e){
            throw new DukeException("File not found");
        }
    }
    /**
     * Saves the {@code TodoList} data to the storage file.
     * @throws IOException if there were errors converting and/or storing data to file.
     */
    private static void writeToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Encodes the {@code Task} into a readable string representation.
     * TaskStatus | isDone | Description
     * @param task object
     * @return String based on the description inside task
     */
    public static String appendToFile(Task task){
        textToAppend = task.getTaskStatus()+
                splitBy+getStringNum(task.getIsDone());
        if(task.getTaskStatus().equals("D")){
            textToAppend += splitBy + task.getDescription()+" by "+task.getTime();
        }else if(task.getTaskStatus().equals("E")){
            textToAppend += splitBy + task.getDescription()+" at "+task.getTime();
        }else{
            textToAppend += splitBy+task.getDescription();
        }
        return textToAppend+System.lineSeparator();
    }

    public static void logCommand(String command){
        logString.add(command);
    }
    public static ArrayList<String> getLogCommand(){
        return logString;
    }

    /**
     * Convert [X] to 1 and [] to 0
     * @param isDone boolean
     * @return the task status in string 1 or 0
     */
    public static String getStringNum(Boolean isDone) {
        return (isDone ? "1" : "0"); // mark done task with X
    }

    /**
     * Convert 1 to [X] and 0 to 0
     * @param isDone string
     * @return the task status boolean true/false
     */
    public static boolean getBooleanNum(String isDone) {
        if(isDone.equals("1")){
            return true;
        }
        return false;
    }
}