package todolist.parser.time;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserDate {

    //todo throw error if format not matching
    public static String parseDate(String input ){
        String newDate = getDate(input);
        String newTime = getTime(input);

        return newDate+" "+newTime;
    }

    public static boolean isDate(String input){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        try{
            LocalDate.parse(input, format);
        }catch(Exception e){
            return false;
        }
        return true;
    }


    //  todo write a command list out all tasks in the same date/timing agenda 2021/10/23
    public static String getTime(String timeStr){
        String regexTime = "\\d{4}$|\\d{1,2}pm|\\d{1,2}am";
        String strTime = getMatch(timeStr,regexTime);
        if(!strTime.contains("am")||!strTime.contains("pm")){
            String newTime = convertStrToTime(strTime);
            return newTime;
        }
        //should throw error if time not within 24 hour
        return timeStr;
    }

//    public static boolean checkDate(){
    //todo
//      deadline date and time must bigger than now
    //  ask teacher if I need to makesure the date is bigger than the now for both event and deadline
    //todo get mon,tue,wed.. today, tomorrow etc
//    }

    public static String convertStrToTime(String strTime){

        DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm");
        LocalTime dateTime = LocalTime.parse(strTime,format);

        //convert time to other format
        DateTimeFormatter reFormat = DateTimeFormatter.ofPattern("h:mm a");
        String newDate = dateTime.format(reFormat);

        return newDate;
    }


    public static String getDate(String dateStr){
        String regexDate = "\\d{1,2}\\/\\d{1,2}\\/\\d{1,4}";
        String strDate = getMatch(dateStr,regexDate);
        String newDate = convertStrToDate(strDate);
        return newDate;
    }


    public static String convertStrToDate(String strDate){

        //read input string and convert to date format
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(strDate, format);

        //Display
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return formattedDate;

    }


    private static String getMatch(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group();
        } else {
            //throw error
            return "NOT FOUND";
        }
    }
}
