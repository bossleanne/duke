package todolist.ui;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {
    @Test
    void shouldIgnore() {
        assertEquals(true,Ui.shouldIgnore(" "));
        assertEquals(false,Ui.shouldIgnore("test"));
    }
}