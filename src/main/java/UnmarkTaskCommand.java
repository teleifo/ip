public class UnmarkTaskCommand extends Command {
    private int index;

    public UnmarkTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, TaskList tasklist, Storage storage) {
        if (tasklist.isEmpty()) {
            ui.showReply("The task list is currently empty!");
            return;
        }

        try {
            Task t = tasklist.getTask(index - 1);
            t.updateDoneStatus(false);

            storage.saveTasks(tasklist);

            ui.showReply("Ok, I've marked this task as not done:\n" + t);
        } catch (JeffException e) {
            ui.showError(e.getMessage());
        }
    }
}
