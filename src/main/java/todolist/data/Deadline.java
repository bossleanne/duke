package todolist;

/********
 * Created by IntelliJ IDEA.
 * User: Leanne.Sun
 * Date: 19/9/21
 * Time: 10:28 am
 * All rights reserved.
 */

public class Deadline extends Task {

    public static String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static String getBy(){
        return by;
    }

    @Override
    public String toString() {
        return "Got it. I've added this task: \n  "+ super.toString() + " (by: " + by + ")";
    }
}