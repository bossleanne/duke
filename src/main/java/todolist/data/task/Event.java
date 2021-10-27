package todolist.data.task;

/********
 * Created by IntelliJ IDEA.
 * User: Leanne.Sun
 * Date: 19/9/21
 * Time: 10:28 am
 * All rights reserved.
 */

public class Event extends Task {

    protected static String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getDescription() {
        return description;
    }
    public static String getAt(){
        return at;
    }

    @Override
    public String getTime(){
        return at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + getTime() + ")";
    }
}
