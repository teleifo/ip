package jeff.parser;

import jeff.commands.Command;
import jeff.data.exception.JeffException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommand_delete_invalidId_exceptionThrown() {
        try {
            assertEquals(new Command(), Parser.parseCommand("delete a"));
        } catch (JeffException e) {
            assertEquals("You need to provide valid task ID!\nFormat: delete [task id]",
                    e.getMessage());
        }
    }
}
