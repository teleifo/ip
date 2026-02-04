public class MarkTaskCommand extends Command {
    private int index;

    public MarkTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, TaskList tasklist, Storage storage) {
        try {
            Task t = tasklist.getTask(index - 1);
            t.updateDoneStatus(true);

            storage.saveTasks(tasklist);

            ui.showReply("Ok, I've marked this task as done:\n" + t);
        } catch (JeffException e) {
            ui.showError(e.getMessage());
        }
    }
}
