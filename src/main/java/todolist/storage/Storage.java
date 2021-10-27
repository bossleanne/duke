package todolist.storage;

import todolist.data.TaskList;
import todolist.data.task.Deadline;
import todolist.data.task.Event;
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
//    public static TaskList taskList;


    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static void checkThePath() throws IOException {
        //get the full path of the file
        f = new File(filePath);
        String tmpPath = f.getAbsolutePath();
        String path = "";
        String file = "";
        int lastSlashPos = tmpPath.lastIndexOf('/');

        path = tmpPath.substring(0, lastSlashPos);
        file = tmpPath.substring(lastSlashPos + 1, tmpPath.length());

        //check the validity of the path
        if (!Files.isDirectory(Paths.get(path))) {
            System.out.println(path + " does not exists, Do you want to create new one? Y/n");
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            if (line.toLowerCase().equals("y")) {
                File newfile = new File(path);
                System.out.println(f.getParent() + " has has been created");
                newfile.mkdir();
            }
        }
        if (f.createNewFile()) {
            System.out.println(file + " has been created.");
        }
    }

    public static ArrayList<Task> load() throws IOException, DukeException {

//        checkThePath();
        ArrayList<Task> loadTasks = new ArrayList<>();

        File f = new File(filePath);

        Scanner s = new Scanner(f);

        while (s.hasNext()) {

            String line = s.nextLine();
            String[] components = line.split(" \\| ");
            String taskStatus = components[0];
            boolean isDone = getBooleanNum(components[1]);
            System.out.println("loadTask.getIsDone()  "+components[1]);
            System.out.println("isDone  "+isDone);
            String taskDescription = components[2];

            Task loadTask;

            switch (taskStatus) {
                case "T":
                    loadTask = Parser.createToDo("todo "+taskDescription);
                    break;
                case "E":
                    loadTask = Parser.createEvent("event "+taskDescription);
                    break;
                case "D":
                    loadTask = Parser.createDeadline("deadline "+taskDescription);
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


    public static String appendToFile(Task task) throws IOException {

        textToAppend = task.getTaskStatus()+
                splitBy+getStringNum(task.getIsDone())+
                splitBy+task.getDescription()+
                System.lineSeparator();

        if(task.getClass().getName().equals("Deadline")){
            textToAppend = textToAppend+splitBy+Deadline.getBy();
        }
        else if(task.getClass().getName().equals("Event")){
            textToAppend = textToAppend+splitBy+Event.getAt();
        }

        return textToAppend;

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

//    public void modifyFileDelete(int lineNum) throws IOException {
//        String joined_str = "";
//        String tempPath = "temp.txt";
//        FileWriter fw = new FileWriter(tempPath);
//
//        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(filePath));
//        String line = "";
//
//        while((line = lineNumberReader.readLine())!=null){
//            String lineContent = line;
//            String[] components = lineContent.split(" \\| ");
//            if(lineNumberReader.getLineNumber()==lineNum){
//                if(!mode){
//                    components[1] = "1";
//                    joined_str = String.join(" | ", components);
//                    fw.write(joined_str+"\n");
//                }
//                else{//delete
//                    lineNumberReader.skip(0);
//                }
//            }
//            else{
//                fw.write(lineContent+"\n");
//            }
//        }
//        lineNumberReader.close();
//        fw.close();
//        fileMoving(filePath,tempPath);
//    }
//
//    public void modifyFileDone(int lineNum) throws IOException { //mode 0/1 done/delelte
//        String joined_str = "";
//        String tempPath = "temp.txt";
//        FileWriter fw = new FileWriter(tempPath);
//
//        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(filePath));
//        String line = "";
//
//        while((line = lineNumberReader.readLine())!=null){
//            String lineContent = line;
//            String[] components = lineContent.split(" \\| ");
//            if(lineNumberReader.getLineNumber()==lineNum){
//                if(!mode){
//                    components[1] = "1";
//                    joined_str = String.join(" | ", components);
//                    fw.write(joined_str+"\n");
//                }
//                else{//delete
//                    lineNumberReader.skip(0);
//                }
//            }
//            else{
//                fw.write(lineContent+"\n");
//            }
//        }
//        lineNumberReader.close();
//        fw.close();
//        fileMoving(filePath,tempPath);
//    }

//    public void fileMoving(String src, String dest) throws IOException{
//        File f = new File(src);
//        if(f.exists()){
//            Files.delete(Paths.get(src));
//            Files.copy(Paths.get(dest), Paths.get(src));
//        }else{
//            Files.copy(Paths.get(dest), Paths.get(src));
//        }
//        Files.delete(Paths.get(dest));
//    }

}