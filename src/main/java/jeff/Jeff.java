package jeff;

import jeff.commands.Command;
import jeff.commands.ExitCommand;
import jeff.data.exception.JeffException;
import jeff.data.task.TaskList;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.ui.Ui;

import java.nio.file.Path;

public class Jeff {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    private static final Path FILE_PATH = Path.of("data", "tasks.txt");

    public Jeff() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);

        try {
            storage.ensureDataDirectory();
            tasks = new TaskList(storage.loadTasks());
        } catch (JeffException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showGreeting();
        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                ui.showDivider();

                Command c = Parser.parseCommand(userInput);
                c.execute(ui, tasks, storage);

                isExit = ExitCommand.isExit(c);
            } catch (JeffException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showDivider();
            }
        }
    }

    public static void main(String[] args)  {
        new Jeff().run();
    }
}
