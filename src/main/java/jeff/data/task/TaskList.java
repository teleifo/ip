package jeff.data.task;

import jeff.data.exception.JeffException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> TASKS = new ArrayList<>();

    public TaskList() {}

    public TaskList(ArrayList<Task> tasks) {
        TASKS.addAll(tasks);
    }

    public void addTask(Task t) {
        TASKS.add(t);
    }

    public Task getTask(int index) throws JeffException {
        if (index < 0) throw new JeffException("Task ID must be larger than 0!");
        if (size() == 0) throw new JeffException("The task list is empty!");
        if (index >= size()) throw new JeffException("List only has " + size() + " item(s)");

        return TASKS.get(index);
    }

    public ArrayList<Task> getTasks() {
        return TASKS;
    }

    public Task removeTask(int index) throws JeffException {
        if (index < 0) throw new JeffException("Task ID must be larger than 0!");
        if (size() == 0) throw new JeffException("The task list is empty!");
        if (index >= size()) throw new JeffException("List only has " + size() + " item(s)");

        return TASKS.remove(index);
    }

    public int size() {
        return TASKS.size();
    }

    public boolean isEmpty() {
        return TASKS.isEmpty();
    }
}
