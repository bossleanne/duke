package todolist.data.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    Task t1 = new Todo("buy milk");
    Task t2 = new Event("go shopping","5/12/2021");
    Task t3 = new Deadline("final submission","2/12/2021 1300");
    Task t4 = new Deadline("quiz","1500");

    @Test
    public void getDescriptionTest(){
        assertEquals("buy milk", t1.getDescription());
        assertEquals("go shopping", t2.getDescription());
        assertEquals("final submission", t3.getDescription());
    }
    @Test
    public void getTimeTest(){
        assertEquals("5/12/2021", t2.getTime());
        assertEquals("2/12/2021 1300", t3.getTime());
        assertEquals("1500", t4.getTime());
    }
}
