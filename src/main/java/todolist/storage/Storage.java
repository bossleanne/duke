package todolist.storage;

import todolist.data.TaskList;
import todolist.data.task.Deadline;
import todolist.data.task.Event;
import todolist.data.task.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage{

    public static String filePath;
    public File f;
    public TaskList taskList = new TaskList();

    public Storage(String filePath) {
        this.filePath = filePath;
        f = new File(filePath);
    }

    public void checkThePath() throws IOException {
        //get the full path of the file
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
        load();
    }

//    TODO: transform the data into tasks,void decipher()
    //decoder
    public ArrayList<Task> load() throws IOException,FileNotFoundException {
        checkThePath();
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
        return tasks;
    }
    //TODO: ask prof if the duplicate tasks need to consider
    //TODO: ask if need to overwrite the file everytime user load

    public static String textToAppend = "";
    public static String splitBy = " | ";


    public static void appendToFile(Task task) throws IOException {
        //create a new empty task list
        FileWriter fw = new FileWriter(filePath,true);
        textToAppend = task.getTaskStatus()+splitBy+getStatusNum(task.getIsDone())+splitBy+task.getDescription();
        //use get class to access different method
        if(task.getClass().getName().equals("Deadline")){
            fw.write(textToAppend+splitBy+Deadline.getBy());
        }
        else if(task.getClass().getName().equals("Event")){
            fw.write(textToAppend+splitBy+Event.getAt());
        }else{
            fw.write(textToAppend);
        }
        fw.write(System.lineSeparator());
        fw.close();
//        for(int i = 0; i < taskList.taskCount(); i++)
//        {
//            Task t = taskList.tasks.get(i);
//            textToAppend = t.getTaskStatus()+splitBy+getStatusNum(t.getIsDone())+splitBy+t.getDescription();
//            //use get class to access different method
//            if(t.getClass().getName().equals("todolist.data.task.Deadline")){
//                fw.write(textToAppend+splitBy+ Deadline.getBy());
//            }
//            else if(t.getClass().getName().equals("todolist.data.task.Event")){
//                fw.write(textToAppend+splitBy+ Event.getAt());
//            }else{
//                fw.write(textToAppend);
//            }
//        }
//        fw.write(System.lineSeparator());
//        fw.close();
    }

    public static String getStatusNum(Boolean isDone) {
        return (isDone ? "1" : "0"); // mark done task with X
    }

    public void modifyFile(int lineNum,boolean mode) throws IOException { //mode 0/1 done/delelte
        String joined_str = "";
        String tempPath = "temp.txt";
        FileWriter fw = new FileWriter(tempPath);

        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(filePath));
        String line = "";

        while((line = lineNumberReader.readLine())!=null){
            String lineContent = line;
            String[] components = lineContent.split(" \\| ");
            if(lineNumberReader.getLineNumber()==lineNum){
                if(!mode){
                    components[1] = "1";
                    joined_str = String.join(" | ", components);
                    fw.write(joined_str+"\n");
                }
                else{//delete
                    lineNumberReader.skip(0);
                }
            }
            else{
                fw.write(lineContent+"\n");
            }
        }
        lineNumberReader.close();
        fw.close();
        fileMoving(filePath,tempPath);
    }

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