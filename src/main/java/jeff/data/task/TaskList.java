package jeff.data.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import jeff.data.exception.JeffException;

/**
 * Represents a list of {@link Task} objects with utility methods for managing them.
 * <p>
 * Provides functionality to add, retrieve, remove, and inspect tasks. All methods
 * perform bounds and validity checks and throw {@link JeffException} for invalid operations.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

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
        this.tasks.addAll(tasks);
    }

    /**
     * Adds a task to the end of the task list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        int oldSize = getSize();
        tasks.add(task);

        assert getSize() == oldSize + 1 : "Task was not added correctly";
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index the index of the task to retrieve (0-based)
     * @return the task at the specified index
     * @throws JeffException if the index is invalid or the list is empty
     */
    public Task getTask(int index) throws JeffException {
        if (index < 0) {
            throw new JeffException("Task ID must be larger than 0!");
        }
        if (getSize() == 0) {
            throw new JeffException("The task list is empty!");
        }
        if (index >= getSize()) {
            throw new JeffException("List only has " + getSize() + " item(s)");
        }

        return tasks.get(index);
    }

    /**
     * Returns the internal list of tasks.
     *
     * @return an {@link ArrayList} containing all tasks in the TaskList
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Searches for tasks whose descriptions contain the given query string.
     *
     * @param query the string to search for in task descriptions
     * @return an {@link ArrayList} of tasks that match the query
     */
    public ArrayList<Task> findTasks(String query) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(query))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Task> viewSchedule(LocalDate date) {
        return tasks.stream()
                .filter(task -> occursOn(task, date))
                .sorted(this::compareTasks)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private boolean occursOn(Task task, LocalDate date) {
        if (task instanceof Deadline deadline) {
            return deadline.getBy().toLocalDate().equals(date);
        }

        if (task instanceof Event event) {
            LocalDate from = event.getFrom().toLocalDate();
            LocalDate to = event.getTo().toLocalDate();

            return (!date.isBefore(from) && !date.isAfter(to));
        }

        return false;
    }

    private int compareTasks(Task t1, Task t2) {
        boolean t1FullDay = isFullDay(t1);
        boolean t2FullDay = isFullDay(t2);

        if (t1FullDay && !t2FullDay) {
            return -1;
        }
        if (!t1FullDay && t2FullDay) {
            return 1;
        }

        LocalTime time1 = getTime(t1);
        LocalTime time2 = getTime(t2);

        return time1.compareTo(time2);
    }

    private boolean isFullDay(Task task) {
        if (task instanceof Deadline deadline) {
            return deadline.getIsFullDay();
        }

        if (task instanceof Event event) {
            return event.getIsFullDay();
        }

        return false;
    }

    private LocalTime getTime(Task task) {
        if (task instanceof Deadline deadline) {
            return deadline.getBy().toLocalTime();
        }

        if (task instanceof Event event) {
            return event.getFrom().toLocalTime();
        }

        return LocalTime.MIDNIGHT;
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index the index of the task to remove (0-based)
     * @return the task that was removed
     * @throws JeffException if the index is invalid or the list is empty
     */
    public Task removeTask(int index) throws JeffException {
        int oldSize = getSize();

        if (index < 0) {
            throw new JeffException("Task ID must be larger than 0!");
        }
        if (getSize() == 0) {
            throw new JeffException("The task list is empty!");
        }
        if (index >= getSize()) {
            throw new JeffException("List only has " + getSize() + " item(s)");
        }

        Task removed = tasks.remove(index);

        assert getSize() == oldSize - 1 : "Task was not removed correctly";

        return removed;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the TaskList
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Checks whether the task list is empty.
     *
     * @return {@code true} if the list contains no tasks, {@code false} otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
