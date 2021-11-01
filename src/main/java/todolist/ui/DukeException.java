package todolist.ui;
/**
 * Signals that the given data does not fulfill Duke constraints.
 */
public class DukeException extends Exception {
    /**
     * @param message shows failed constraints' information
     */
    public DukeException(String message){
        super(message);
    }
}
