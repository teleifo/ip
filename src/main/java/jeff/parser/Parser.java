package jeff.parser;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.View;

import jeff.Jeff;
import jeff.commands.Command;
import jeff.commands.DeadlineCommand;
import jeff.commands.DeleteTaskCommand;
import jeff.commands.EventCommand;
import jeff.commands.ExitCommand;
import jeff.commands.FindTasksCommand;
import jeff.commands.MarkTaskCommand;
import jeff.commands.ToDoCommand;
import jeff.commands.UnmarkTaskCommand;
import jeff.commands.ViewScheduleCommand;
import jeff.commands.ViewTaskListCommand;
import jeff.common.Utils;
import jeff.data.exception.JeffException;

public class Parser {
    public static final Pattern COMMAND_FORMAT = Pattern.compile(
            "(?<commandWord>\\S+)(?<arguments>.*)");

    public static Command parseCommand(String userInput) throws JeffException {
        Matcher matcher = COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new JeffException("Sorry, I don't know what that means!");
        }

        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments");

        switch (commandWord) {
        case "list":
            return handleListCommand();
        case "find":
            return handleFindCommand(arguments);
        case "schedule":
            return handleScheduleCommand(arguments);
        case "todo":
            return handleToDoCommand(arguments);
        case "deadline":
            return handleDeadlineCommand(arguments);
        case "event":
            return handleEventCommand(arguments);
        case "mark":
            return handleMarkCommand(arguments);
        case "unmark":
            return handleUnmarkCommand(arguments);
        case "delete":
            return handleDeleteCommand(arguments);
        case "bye":
            return handleByeCommand();
        default:
            throw new JeffException("Sorry, I don't know what that means!");
        }
    }

    private static ViewTaskListCommand handleListCommand() {
        return new ViewTaskListCommand();
    }

    private static FindTasksCommand handleFindCommand(String arguments) throws JeffException {
        arguments = arguments.trim();
        if (arguments.isEmpty()) {
            throw new JeffException("You need to provide a keyword/phrase.\nFormat: find [query]");
        }

        return new FindTasksCommand(arguments);
    }

    private static ViewScheduleCommand handleScheduleCommand(String arguments) throws JeffException {
        arguments = arguments.trim();
        if (arguments.isEmpty()) {
            throw new JeffException("You need to provide a date.\nFormat: schedule [date]");
        }
        if (!Utils.isDate(arguments)) {
            throw new JeffException("Schedule `date` should follow this format:\nyyyy-MM-dd");
        }

        return new ViewScheduleCommand(arguments);
    }

    private static ToDoCommand handleToDoCommand(String arguments) throws JeffException {
        arguments = arguments.trim();
        if (arguments.isEmpty()) {
            throw new JeffException("ToDo `description` cannot be empty.\nFormat: todo [description]");
        }

        return new ToDoCommand(arguments);
    }

    private static DeadlineCommand handleDeadlineCommand(String arguments) throws JeffException {
        String[] parts = arguments.split(" /by ", 2);
        if (parts.length < 2) {
            throw new JeffException(
                    "Deadline must have /by.\nFormat: deadline [description] /by [due by]");
        }

        String description = parts[0].trim();
        String by = parts[1].trim();
        boolean isFullDay = !by.contains(":");

        if (description.isEmpty()) {
            throw new JeffException(
                    "Deadline `description` cannot be empty.\nFormat: deadline [description] /by [due by]");
        }
        if (by.isEmpty()) {
            throw new JeffException(
                    "Deadline `by` cannot be empty.\nFormat: deadline [description] /by [due by]");
        }
        if (!Utils.isDate(by) && !Utils.isDateTime(by)) {
            throw new JeffException(
                    "Deadline `by` should follow this format:\nyyyy-MM-dd OR yyyy-MM-ddTHH:mm");
        }

        if (isFullDay) {
            by += "T00:00";
        }
        LocalDateTime byDate = LocalDateTime.parse(by);

        return new DeadlineCommand(description, byDate, isFullDay);
    }

    private static EventCommand handleEventCommand(String arguments) throws JeffException {
        String[] firstSplit = arguments.split(" /from ", 2);
        if (firstSplit.length < 2) {
            throw new JeffException(
                    "Event must have /from and /to.\nFormat: event [description] /from [from] /to [to]");
        }

        String[] secondSplit = firstSplit[1].split(" /to ", 2);
        if (secondSplit.length < 2) {
            throw new JeffException(
                    "Event must have /from and /to.\nFormat: event [description] /from [from] /to [to]");
        }

        String description2 = firstSplit[0].trim();
        String from = secondSplit[0].trim();
        String to = secondSplit[1].trim();
        boolean isFullDay2 = !from.contains(":") && !to.contains(":");

        if (description2.isEmpty()) {
            throw new JeffException(
                    "Event `description` cannot be empty.\nFormat: event [description] /from [from] /to [to]");
        }
        if (from.isEmpty()) {
            throw new JeffException(
                    "Event `from` cannot be empty.\nFormat: event [description] /from [from] /to [to]");
        }
        if (to.isEmpty()) {
            throw new JeffException(
                    "Event `to` cannot be empty.\nFormat: event [description] /from [from] /to [to]");
        }
        if (!Utils.isDate(from) && !Utils.isDateTime(from)) {
            throw new JeffException(
                    "Event `from` should follow this format:\nyyyy-MM-dd OR yyyy-MM-ddTHH:mm");
        }
        if (!Utils.isDate(to) && !Utils.isDateTime(to)) {
            throw new JeffException(
                    "Event `to` should follow this format:\nyyyy-MM-dd OR yyyy-MM-ddTHH:mm");
        }

        if (!from.contains(":")) {
            from += "T00:00";
        }
        if (!to.contains(":")) {
            to += "T00:00";
        }

        LocalDateTime fromDate = LocalDateTime.parse(from);
        LocalDateTime toDate = LocalDateTime.parse(to);

        if (fromDate.isAfter(toDate)) {
            throw new JeffException("Event `to` must be after `from`");
        }

        return new EventCommand(description2, fromDate, toDate, isFullDay2);
    }

    private static MarkTaskCommand handleMarkCommand(String arguments) throws JeffException {
        arguments = arguments.trim();
        if (!Utils.isInteger(arguments)) {
            throw new JeffException("You need to provide valid task ID!\nFormat: mark [task id]");
        }

        return new MarkTaskCommand(Integer.parseInt(arguments));
    }

    private static UnmarkTaskCommand handleUnmarkCommand(String arguments) throws JeffException {
        arguments = arguments.trim();
        if (!Utils.isInteger(arguments)) {
            throw new JeffException("You need to provide valid task ID!\nFormat: unmark [task id]");
        }

        return new UnmarkTaskCommand(Integer.parseInt(arguments));
    }

    private static DeleteTaskCommand handleDeleteCommand(String arguments) throws JeffException {
        arguments = arguments.trim();
        if (!Utils.isInteger(arguments)) {
            throw new JeffException("You need to provide valid task ID!\nFormat: delete [task id]");
        }

        return new DeleteTaskCommand(Integer.parseInt(arguments));
    }

    private static ExitCommand handleByeCommand() {
        return new ExitCommand();
    }
}
