package todolist.parser;

import todolist.ui.DukeException;
import todolist.utils.Matching;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ParserDate {
    /**
     * Parses user date input
     * @param input get the date and time string
     * @return a new date format string that rejoin the date and time
     * @throws DukeException for invalid date and time input
     */
    public String parseDate(String input) throws DukeException{
        String[] newDateTime = input.split(" ");
        for(int i =0; i< newDateTime.length; i++) {
            if (isStrDate(newDateTime[i])){
                newDateTime[i] = convertStrToDate(newDateTime[i]);
            } else if(isStrTime(newDateTime[i])) {
                newDateTime[i] = convertStrToTime(newDateTime[i]);
            }else{
                throw new DukeException("No valid date or time founded, please re-enter. e.g 21/08/2021 1800");
            }
        }
        return String.join(",", newDateTime);
    }
    /**
     * Get the date string from user input
     */
    public boolean isStrDate(String dateStr){
        String regexDate = "\\d{1,2}\\/\\d{1,2}\\/\\d{1,4}";
        String strDate = Matching.getMatch(dateStr,regexDate);
        if(strDate.equals("-1")){
            return false;
        }
        return true;
    }
    /**
     * Get the time string from user input
     */
    public boolean isStrTime(String timeStr){
        String regexTime = "\\d{4}$";
        String strTime = Matching.getMatch(timeStr,regexTime);
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
    public String convertStrToDate(String dateStr) throws DukeException{
        try{
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate date = LocalDate.parse(dateStr, format);
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
    public String convertStrToTime(String timeStr) throws DukeException {
        try{
            DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm");
            LocalTime dateTime = LocalTime.parse(timeStr,format);
            return dateTime.format(DateTimeFormatter.ofPattern("h:mm a"));
        }catch (Exception e){
            throw new DukeException("Wrong time format, please re-enter a 4 digit time. e.g 1800");
        }
    }

//    /**
//     * A match function to match the regex and user input
//     * @param input matcher in string
//     * @param regex pattern in string
//     * @return matched string
//     */
//    public String getMatch(String input, String regex) {
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(input);
//        if (matcher.find()) {
//            return matcher.group();
//        } else {
//            return "-1";
//        }
//    }
}
