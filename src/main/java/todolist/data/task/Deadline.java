package todolist.data.task;


public class Deadline extends Task {

    public static String by;

    public Deadline(String description, String by) {
        super(description);
        super.setTaskStatus("D");
        this.by = by;
    }


    @Override
    public String getTime(){
        return by;
    }

//    public static LocalDate strToDate(String date){

//        System.out.println();

        //conditions -
        // date 2/12/2019 || yyyy-mm-dd
        // date + time  2/12/2019 1800 ,
        // days mon tue || monday tuesday
        // days + time mon 3pm || mon 1500
        //
//    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + getTime() + ")";
    }
}