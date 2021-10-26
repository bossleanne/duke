package todolist.storage;

import todolist.data.task.Deadline;
import todolist.data.task.Event;
import todolist.data.task.Task;
import todolist.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage{

    public static String filePath;
    public File f;
//    public TaskList taskList = new TaskList();
    public static String textToAppend = "";
    public static String splitBy = " | ";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void checkThePath() throws IOException {
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

//    TODO: transform the data into tasks,void decipher()
    //decipher
    public ArrayList<Task> load() throws FileNotFoundException {

//        checkThePath(); // only this is true - or throw error
        ArrayList<Task> loadTasks = new ArrayList<>();

        File f = new File(filePath); // create a File for the given file path

        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {

            String line = s.nextLine();
            String[] components = line.split(" \\| ");
            String taskStatus = components[0];
            boolean isDone = Boolean.parseBoolean(components[1]);
            System.out.println("loadTask.getIsDone()  "+components[1]);
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
                    throw new IllegalStateException("Unexpected value: " + taskStatus);
            }
            loadTask.setIsDone(isDone);

            loadTasks.add(loadTask);
            System.out.println("Test!??!!====");
            System.out.println(loadTask.getIsDone());
            System.out.println(loadTask.getTaskStatus());
            System.out.println(loadTask.getDescription());
        }

        return loadTasks;
    }


    public static void appendToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        textToAppend = task.getTaskStatus()+
                splitBy+getStatusNum(task.getIsDone())+
                splitBy+task.getDescription()+
                System.lineSeparator();
        //use get class to access different method
        if(task.getClass().getName().equals("Deadline")){
            fw.write(textToAppend+splitBy+Deadline.getBy());
        }
        else if(task.getClass().getName().equals("Event")){
            fw.write(textToAppend+splitBy+Event.getAt());
        }else{
            fw.write(textToAppend);
        }
//        fw.write(System.lineSeparator());
        fw.close();
    }

    public static String getStatusNum(Boolean isDone) {
        return (isDone ? "1" : "0"); // mark done task with X
    }

//    public void modifyFileDelete(int lineNum) throws IOException { //mode 0/1 done/delelte
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

    public void fileMoving(String src, String dest) throws IOException{
        File f = new File(src);
        if(f.exists()){
            Files.delete(Paths.get(src));
            Files.copy(Paths.get(dest), Paths.get(src));
        }else{
            Files.copy(Paths.get(dest), Paths.get(src));
        }
        Files.delete(Paths.get(dest));
    }

}