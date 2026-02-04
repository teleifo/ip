package jeff.data.task;

import jeff.data.exception.JeffException;

import java.util.ArrayList;

/**
 * Represents a list of {@link Task} objects with utility methods for managing them.
 * <p>
 * Provides functionality to add, retrieve, remove, and inspect tasks. All methods
 * perform bounds and validity checks and throw {@link JeffException} for invalid operations.
 */
public class TaskList {
    private final ArrayList<Task> TASKS = new ArrayList<>();

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {}

    /**
     * Constructs a {@code TaskList} initialized with the given list of tasks.
     *
     * @param tasks an ArrayList of tasks to initialize the TaskList with
     */
    public TaskList(ArrayList<Task> tasks) {
        TASKS.addAll(tasks);
    }

    /**
     * Adds a task to the end of the task list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        TASKS.add(task);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index the index of the task to retrieve (0-based)
     * @return the task at the specified index
     * @throws JeffException if the index is invalid or the list is empty
     */
    public Task getTask(int index) throws JeffException {
        if (index < 0) throw new JeffException("Task ID must be larger than 0!");
        if (size() == 0) throw new JeffException("The task list is empty!");
        if (index >= size()) throw new JeffException("List only has " + size() + " item(s)");

        return TASKS.get(index);
    }

    /**
     * Returns the internal list of tasks.
     *
     * @return an ArrayList containing all tasks in the TaskList
     */
    public ArrayList<Task> getTasks() {
        return TASKS;
    }

    /**
     * Searches for tasks whose descriptions contain the given query string.
     *
     * @param query the string to search for in task descriptions
     * @return an {@link ArrayList} of tasks that match the query
     */
    public ArrayList<Task> findTasks(String query) {
        ArrayList<Task> found = new ArrayList<>();

        for (Task task : TASKS) {
            if (task.getDescription().contains(query)) {
                found.add(task);
            }
        }

        return found;
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index the index of the task to remove (0-based)
     * @return the task that was removed
     * @throws JeffException if the index is invalid or the list is empty
     */
    public Task removeTask(int index) throws JeffException {
        if (index < 0) throw new JeffException("Task ID must be larger than 0!");
        if (size() == 0) throw new JeffException("The task list is empty!");
        if (index >= size()) throw new JeffException("List only has " + size() + " item(s)");

        return TASKS.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the TaskList
     */
    public int size() {
        return TASKS.size();
    }

    /**
     * Checks whether the task list is empty.
     *
     * @return {@code true} if the list contains no tasks, {@code false} otherwise
     */
    public boolean isEmpty() {
        return TASKS.isEmpty();
    }
}
