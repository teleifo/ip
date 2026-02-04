package jeff.commands;

import jeff.data.task.TaskList;
import jeff.storage.Storage;
import jeff.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList tasklist, Storage storage) {
        ui.showReply("Bye. Hope to see you again soon!");
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
