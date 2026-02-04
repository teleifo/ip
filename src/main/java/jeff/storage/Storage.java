package jeff.storage;

import jeff.data.exception.JeffException;
import jeff.data.task.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Handles reading and writing task data to a local file.
 * <p>
 * The {@code Storage} class provides methods to ensure the data directory exists,
 * save tasks to a file, and load tasks from a file, converting between {@link Task} objects
 * and their string representations for persistent storage.
 */
public class Storage {
    private Path filePath;

    /**
     * Constructs a {@code Storage} instance with the given file path.
     *
     * @param filePath the path to the file where tasks will be saved/loaded
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Ensures that the parent directory of the data file exists.
     *
     * @throws JeffException if the directory cannot be created
     */
    public void ensureDataDirectory() throws JeffException {
        try {
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            throw new JeffException("Failed to create data directory");
        }
    }

    /**
     * Saves the provided list of tasks to the data file.
     * <p>
     * Each task is converted to a string using {@link Task#toFileString()}.
     *
     * @param tasks the {@link TaskList} containing tasks to save
     * @throws JeffException if writing to the file fails
     */
    public void saveTasks(TaskList tasks) throws JeffException {
        ArrayList<String> lines = new ArrayList<>();

        for (Task t : tasks.getTasks()) {
            lines.add(t.toFileString());
        }

        try {
            Files.write(filePath, lines);
        } catch (IOException e) {
            throw new JeffException("Failed to save tasks to file");
        }
    }

    /**
     * Loads tasks from the data file.
     * <p>
     * Reads each line of the file, parses it according to its task type
     * (ToDo, Deadline, Event), and returns a list of {@link Task} objects.
     *
     * @return an {@link ArrayList} of tasks loaded from the file
     * @throws JeffException if the file does not exist, or if reading/parsing fails
     */
    public ArrayList<Task> loadTasks() throws JeffException {
        if (!Files.exists(filePath)) {
            throw new JeffException("Failed to load tasks from file");
        }

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            for (String line : Files.readAllLines(filePath)) {
                if (line.isBlank()) continue;

                String[] parts = line.split(" \\| ");

                switch (parts[0]) {
                case "T":
                    tasks.add(new ToDo(parts[2], parts[1].equals("1")));
                    break;
                case "D":
                    tasks.add(new Deadline(parts[2], parts[1].equals("1"),
                            LocalDateTime.parse(parts[4]), parts[3].equals("1")));
                    break;
                case "E":
                    String timeRange = parts[4].substring(5);
                    String[] fromTo = timeRange.split(" to ");

                    tasks.add(new Event(parts[2], parts[1].equals("1"),
                            LocalDateTime.parse(fromTo[0]), LocalDateTime.parse(fromTo[1]),
                            parts[3].equals("1")));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid task format");
                }
            }

            return tasks;
        } catch (IOException | IllegalArgumentException e) {
            throw new JeffException(e.getMessage());
        }
    }
}
