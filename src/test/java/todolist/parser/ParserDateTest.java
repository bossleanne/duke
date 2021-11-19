package todolist.parser;

import org.junit.jupiter.api.Test;
import todolist.ui.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserDateTest {
    ParserDate pd = new ParserDate();
    String date1 = "5/12/2021";
    String date2 = "2/12/2021 1300";
    String date3 = "1500";

    @Test
    public void parseDateTest() throws DukeException {
        assertEquals("Dec 05 2021", pd.parseDate(date1));
        assertEquals("Dec 02 2021,1:00 pm", pd.parseDate(date2));
        assertEquals("3:00 pm", pd.parseDate(date3));
    }
}
