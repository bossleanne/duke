package todolist.data.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {


    Event event1 = new Event("event project meeting","3/11/2021");


    @Test
    public void getTimeTest(){
        assertEquals("3/11/2021",event1.getTime());
    }

}
