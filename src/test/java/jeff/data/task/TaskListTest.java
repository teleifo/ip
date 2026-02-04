package jeff.data.task;

import jeff.data.exception.JeffException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void getTask_validId_success() throws JeffException {
        ArrayList<Task> list = new ArrayList<Task>();
        ToDo task = new ToDo("Homework");
        list.add(task);
        assertEquals(task, (new TaskList(list)).getTask(0));
    }

    @Test
    public void getTask_invalidId_exceptionThrown() {
        try {
            ArrayList<Task> list = new ArrayList<Task>();
            ToDo task = new ToDo("Homework");
            list.add(task);
            assertEquals(task, (new TaskList(list)).getTask(1));
        } catch (JeffException e) {
            assertEquals("List only has 1 item(s)", e.getMessage());
        }
    }
}
