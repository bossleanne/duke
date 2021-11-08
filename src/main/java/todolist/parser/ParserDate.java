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
        if (isStrDate(newDateTime[0]) && isStrTime(newDateTime[1])){
            newDateTime[0] = convertStrToDate(input);
            newDateTime[1] = convertStrToTime(input);
        }else if(isStrDate(newDateTime[0])){
            newDateTime[0] = convertStrToDate(input);
        }else if(isStrTime(newDateTime[1])){
            newDateTime[1] = convertStrToTime(input);
        } else if(!isStrDate(newDateTime[0]) && !isStrTime(newDateTime[1])){
            throw new DukeException("No valid date or time founded, please re-enter. e.g 21/08/2021 1800");
        }
//        for(int i =0; i< newDateTime.length; i++) {
//            if (isStrDate(newDateTime[i])){
//                newDateTime[i] = convertStrToDate(input);
//            } else if(isStrTime(newDateTime[i])) {
//                newDateTime[i] = convertStrToTime(input);
//            }else{
//                throw new DukeException("No valid date or time founded, please re-enter. e.g 21/08/2021 1800");
//            }
//        }
        return String.join(",", newDateTime);
    }
    /**
     * Get the date string from user input
     */
    public static boolean isStrDate(String dateStr){
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
        String regexTime = "\\d{4}$";
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
            String regexDate = "\\d{1,2}\\/\\d{1,2}\\/\\d{4}";
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
            String regexTime = "\\d{4}$";
            String strTime = getMatch(timeStr,regexTime);
            DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm");
            LocalTime dateTime = LocalTime.parse(strTime,format);
            DateTimeFormatter reFormat = DateTimeFormatter.ofPattern("h:mm a");
            return dateTime.format(reFormat);
        }catch (Exception e){
            throw new DukeException("Wrong time format, please re-enter a 4 digit time. e.g 1800");
        }
    }

    public static boolean isLogDate(String input){
        if(input.contains(",")){
            return true;
        }
        return false;
    }


    /**
     * Parses storage date input
     * @param input get the date and time string
     * @return a new date format string that rejoin the date and time
     * @throws DukeException for invalid date and time input
     */
    public static String parseStoredDate(String input){
        String[] newDateTime = input.split(",");
        if (newDateTime.length == 2){
           if(isStrTime(newDateTime[1])) {
               newDateTime[0] = convertDateToStr(input);
               newDateTime[1] = convertTimeToStr(input);
           }
        }else{
            if (isStrTime(newDateTime[0])){
                newDateTime[0] = convertTimeToStr(input);
            } else {
                newDateTime[0] = convertDateToStr(input);
            }
        }

        return String.join(" ", newDateTime);
    }
    /**
     * Convert the date string from user input to a new format
     * @param dateStr string as MMM dd yyyy format
     * @return date string as d/M/yyyy format
     */
    public static String convertDateToStr(String dateStr) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate dateTime = LocalDate.parse(dateStr,format);
        DateTimeFormatter reFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
        return dateTime.format(reFormat);
    }
    /**
     * Convert the time string from user input to a new format
     * @param timeStr string as h:mm format
     * @return time string HHmm a format
     */
    public static String convertTimeToStr(String timeStr) {
        DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("h:mm a");
        LocalTime dateTime = LocalTime.parse(timeStr,timeformat);
        DateTimeFormatter reFormat = DateTimeFormatter.ofPattern("HHmm");
        return dateTime.format(reFormat);
    }

    /**
     * A match function to match the regex and user input
     * @param input matcher in string
     * @param regex pattern in string
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
