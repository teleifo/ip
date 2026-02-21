package jeff.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Provides utility methods for common validations and parsing checks.
 * <p>
 * Currently includes methods to validate whether a string represents an integer,
 * a date, or a date-time in ISO format.
 */
public class ValidationUtils {
    private ValidationUtils() {}

    /**
     * Checks if a string can be parsed as an integer.
     *
     * @param str the string to check
     * @return {@code true} if {@code str} is a valid integer, {@code false} otherwise
     */
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

    /**
     * Checks if a string can be parsed as a {@link LocalDate} in ISO format (yyyy-MM-dd).
     *
     * @param str the string to check
     * @return {@code true} if {@code str} is a valid date, {@code false} otherwise
     */
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

    /**
     * Checks if a string can be parsed as a {@link LocalDateTime} in ISO format (yyyy-MM-ddTHH:mm).
     *
     * @param str the string to check
     * @return {@code true} if {@code str} is a valid date-time, {@code false} otherwise
     */
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
