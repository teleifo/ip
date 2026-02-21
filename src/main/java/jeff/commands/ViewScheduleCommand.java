package jeff.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import jeff.data.task.Task;
import jeff.data.task.TaskList;
import jeff.storage.Storage;

public class ViewScheduleCommand extends Command {
    private String query;

    public ViewScheduleCommand(String query) {
        this.query = query;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            return "The task list is currently empty!";
        }

        LocalDate date = LocalDate.parse(query);

        ArrayList<Task> found = tasks.viewSchedule(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

        if (found.isEmpty()) {
            return "No tasks found on " + date.format(formatter) + "!";
        }

        StringBuilder temp = new StringBuilder();
        temp.append("Here's your schedule for " + date.format(formatter) + ":\n");

        for (int i = 1; i <= found.size(); i++) {
            temp.append(i).append(". ").append(found.get(i - 1));
            if (i != found.size()) {
                temp.append("\n");
            }
        }

        return temp.toString();
    }
}
