package todolist.parser;

import todolist.ui.DukeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserDate {
    /**
     * Parses user date input
     * @param input get the date and time string
     * @return a new date format string that rejoin the date and time
     * @throws DukeException for invalid date and time input
     */
    public static String parseDate(String input) throws DukeException{
        String[] newDateTime = input.split(" ");
        for(int i =0; i< newDateTime.length; i++) {
            if (newDateTime[i] != " ") {
                if (!isStrTime(newDateTime[i]) && !isStrDate(newDateTime[i])) {
                    throw new DukeException("No date or time founded, please re-enter");
                }else if (isStrDate(newDateTime[i])) {
                    newDateTime[i] = convertStrToDate(input);
                } else {
                    newDateTime[i] = convertStrToTime(input);
                    System.out.println("after "+newDateTime[i]);
                }
            }
        }
        return String.join(" ", newDateTime);
    }
    /**
     * Get the date string from user input
     */
    public static boolean isStrDate(String dateStr){
        System.out.println("before "+dateStr);
        String regexDate = "\\d{1,2}\\/\\d{1,2}\\/\\d{1,4}";
        String strDate = getMatch(dateStr,regexDate);
        if(strDate.equals("-1")){
            return false;
        }
        return true;
    }
    /**
     * Get the time string from user input
     */
    public static boolean isStrTime(String timeStr){
        String regexTime = "\\d{4}$|\\d{1,2}pm|\\d{1,2}am";
        String strTime = getMatch(timeStr,regexTime);
        if(strTime.equals("-1")){
            return false;
        }
        return true;
    }
    /**
     * Convert the date string from user input to a new format
     * @param dateStr string as d/M/yyyy format
     * @return date string as MMM dd yyyy format
     * @throws DukeException for invaild date format
     */
    public static String convertStrToDate(String dateStr) throws DukeException{
        try{
            String regexDate = "\\d{1,2}\\/\\d{1,2}\\/\\d{1,4}";
            String strDate = getMatch(dateStr,regexDate);
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(strDate, format);
            return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }catch (Exception e){
            throw new DukeException("Wrong Date format, please re-enter a d/M/yyyy format. e.g 30/10/2021");
        }
    }
    /**
     * Convert the time string from user input to a new format
     * @param timeStr string as HHmm format
     * @return time string h:mm a format
     * @throws DukeException for invaild time format
     */
    public static String convertStrToTime(String timeStr) throws DukeException {
        try{
            String regexTime = "\\d{4}$|\\d{1,2}pm|\\d{1,2}am";
            String strTime = getMatch(timeStr,regexTime);
            if(!strTime.contains("am")||!strTime.contains("pm")){
                DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm");
                LocalTime dateTime = LocalTime.parse(strTime,format);
                DateTimeFormatter reFormat = DateTimeFormatter.ofPattern("h:mm a");
                return dateTime.format(reFormat);
            }
            return timeStr;
        }catch (Exception e){
            throw new DukeException("Wrong time format, please re-enter a 4 digit time. e.g 1800");
        }
    }

//    public static boolean isDate(String input) {
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
//        try{
//            LocalDate.parse(input, format);
//        }catch(Exception e){
//            return false;
//        }
//        return true;
//    }
    /**
     * A match function to match the regex and user input
     * @param input string to take in the matcher
     * @param regex string to take in the pattern
     * @return matched string
     */
    public static String getMatch(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return "-1";
        }
    }
}
