import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Appointment Class
 *
 * <p>
 * This class represents the Appointment entity with fields for appointment ID,
 * date, and description. It provides methods to get and set these fields, while
 * enforcing certain validation rules to ensure data integrity.
 *
 * <p>
 * <strong>Note:</strong> The appointment ID is immutable once assigned, while
 * other fields can be updated.
 *
 * <p>
 * Created by: Stephen Chryn
 * Date: 29-Sept-24
 * Version: 1.0
 */
public class Appointment {

    /**
     * Unique identifier for the appointment, immutable and limited to 10
     * characters.
     */
    private final String id;

    /** First date of the appointment, limited to after today. */
    private LocalDate date;

    /** Description of the appointment, limited to 50 characters. */
    private String description;

    /**
     * Constructor for Appointment.
     *
     * @param id          The unique ID for the appointment (must not be null or
     *                    exceed 10 characters).
     * @param date        The first date of the appointment (must not be null or
     *                    before today, must be in dd-MMM-yy format).
     * @param description The description of the appointment (must not be null or
     *                    exceed 50 characters).
     * @throws IllegalArgumentException if any validation rules are violated.
     */
    public Appointment(
        String id,
        String date,
        String description) {
        if (id == null || id.length() > 10) {
            throw new IllegalArgumentException("Invalid Appointment ID");
        }
        this.id = id;

        this.setDate(date);
        this.setDescription(description);
    }

    /**
     * Retrieves the appointment's unique ID.
     * 
     * @return The appointment ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the appointment's first date.
     * 
     * @return The first date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Retrieves the appointment's description.
     * 
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the appointment's first date.
     *
     * @param date The date string in "dd-MMM-yy" format.
     * @throws IllegalArgumentException if the date is null, in the incorrect format,
     *                                  or is before today.
     */
    public void setDate(String date) {
        if (date == null || !validateDate(date)) {
            throw new IllegalArgumentException("Invalid date");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
        this.date = LocalDate.parse(date, formatter);
    }

    /**
     * Sets the appointment's description.
     *
     * @param description The description (must not be null or exceed 50
     *                    characters).
     * @throws IllegalArgumentException if the description is null or exceeds 50
     *                                  characters.
     */
    public void setDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Invalid Description");
        }
        this.description = description;
    }

    /**
     * Verifies whether a string is a valid date and is after today.
     *
     * @param date The date for the appointment (must be in dd-MMM-yy format).
     * @return true if the date is valid and after today, false otherwise.
     */
    private static boolean validateDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
            LocalDate inputDate = LocalDate.parse(date, formatter);
            LocalDate today = LocalDate.now();
            return inputDate.isAfter(today);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}

