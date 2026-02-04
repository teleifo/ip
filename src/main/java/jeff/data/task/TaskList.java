package jeff.data.task;

import jeff.data.exception.JeffException;

import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> TASKLIST = new ArrayList<>();

    public TaskList() {}

    public TaskList(ArrayList<Task> tasklist) {
        TASKLIST.addAll(tasklist);
    }

    public void addTask(Task t) {
        TASKLIST.add(t);
    }

    public Task getTask(int index) throws JeffException {
        if (index < 0) throw new JeffException("Task ID must be larger than 0!");
        if (size() == 0) throw new JeffException("The task list is empty!");
        if (index >= size()) throw new JeffException("List only has " + size() + " item(s)");

        return TASKLIST.get(index);
    }

    public ArrayList<Task> getTasks() {
        return TASKLIST;
    }

    public Task removeTask(int index) throws JeffException {
        if (index < 0) throw new JeffException("Task ID must be larger than 0!");
        if (size() == 0) throw new JeffException("The task list is empty!");
        if (index >= size()) throw new JeffException("List only has " + size() + " item(s)");

        return TASKLIST.remove(index);
    }

    public int size() {
        return TASKLIST.size();
    }

    public boolean isEmpty() {
        return TASKLIST.isEmpty();
    }
}
