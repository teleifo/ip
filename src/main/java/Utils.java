import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Utils {
    public static boolean isInteger(String str) {
        if (str == null || str.isBlank()) {
            return false;
        }

        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDate(String str) {
        if (str == null || str.isBlank()) {
            return false;
        }

        try {
            LocalDate.parse(str);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isDateTime(String str) {
        if (str == null || str.isBlank()) {
            return false;
        }

        try {
            LocalDateTime.parse(str);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
