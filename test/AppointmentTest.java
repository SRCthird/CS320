import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * AppointmentTest Class
 *
 * <p>This class contains unit tests for the {@link Appointment} class. The tests verify the correct
 * functionality of the getter and setter methods, ensuring that valid data is accepted and that
 * invalid data is properly rejected with appropriate exceptions.
 *
 * <p><strong>Note:</strong> These tests are written using the JUnit 5 framework
 *
 * <p>Created by: Stephen Chryn
 * Date: 29-Sept-24
 * Version: 1.0
 */
public class AppointmentTest {

    /** The appointment instance to be tested. */
    private Appointment appointment;

    /** DateTimeFormatter for the date format used in the tests. */
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");

    /**
     * Setup method executed before each test.
     *
     * <p>This method initializes a new instance of {@link Appointment} with predefined values
     * before each test is run.
     */
    @BeforeEach
    public void setUp() {
        appointment = new Appointment("1234567890", "11-Oct-25", "Description of appointment");
    }

    /**
     * Tests the getter for the appointment ID.
     *
     * <p>Makes sure that the appointment ID is correctly returned.
     */
    @Test
    public void testGetAppointmentId() {
        assertEquals("1234567890", appointment.getId());
    }

    /**
     * Tests the getter for the date.
     *
     * <p>Makes sure that the date is correctly returned.
     */
    @Test
    public void testGetDate() {
        LocalDate expectedDate = LocalDate.parse("11-Oct-25", formatter);
        assertEquals(expectedDate, appointment.getDate());
    }

    /**
     * Tests the getter for the description.
     *
     * <p>Makes sure that the description is correctly returned.
     */
    @Test
    public void testGetDescription() {
        assertEquals("Description of appointment", appointment.getDescription());
    }

    /**
     * Tests the setter for the date with valid data.
     *
     * <p>Makes sure that the date is correctly updated when valid data is provided.
     */
    @Test
    public void testSetDate_Valid() {
        String newDate = "15-Nov-25";
        appointment.setDate(newDate);
        LocalDate expectedDate = LocalDate.parse(newDate, formatter);
        assertEquals(expectedDate, appointment.getDate());
    }

    /**
     * Tests the setter for the description with valid data.
     *
     * <p>Makes sure that the description is correctly updated when valid data is provided.
     */
    @Test
    public void testSetDescription_Valid() {
        appointment.setDescription("I need to load the dishwasher but am coding :)");
        assertEquals("I need to load the dishwasher but am coding :)", appointment.getDescription());
    }

    /**
     * Tests the setter for the date with invalid data.
     *
     * <p>Makes sure that an exception is thrown when attempting to set an invalid date.
     */
    @Test
    public void testSetDate_Invalid() {
        // Test for null date
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setDate(null);
        });

        // Test for a date before today
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setDate("01-Jan-23");
        });

        // Test for an incorrectly formatted date
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setDate("2024-10-15");
        });
    }

    /**
     * Tests the setter for the description with invalid data.
     *
     * <p>Makes sure that an exception is thrown when attempting to set an invalid description.
     */
    @Test
    public void testSetDescription_Invalid() {
        // Test for null description
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setDescription(null);
        });

        // Test for description exceeding 50 characters
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setDescription("This Description Is Way Too Long to be the description of an appointment");
        });
    }
}

