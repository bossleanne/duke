package todolist.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Matching {
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
