package jeff.storage;

import jeff.data.exception.JeffException;
import jeff.data.task.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {
    private Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    public void ensureDataDirectory() throws JeffException {
        try {
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            throw new JeffException("Failed to create data directory");
        }
    }

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
