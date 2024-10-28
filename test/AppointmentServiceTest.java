import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * AppointmentServiceTest Class
 *
 * <p>
 * This class contains unit tests for the {@link AppointmentService} class. The
 * tests verify the correct functionality of methods for adding,
 * retrieving, updating, and deleting appointments, as well as ensuring that
 * validation rules are properly enforced.
 *
 * <p>
 * <strong>Note:</strong> These tests are written using the JUnit 5 framework.
 *
 * <p>
 * Created by: Stephen Chryn
 * Date: 29-Sept-24
 * Version: 1.0
 */
public class AppointmentServiceTest {

    /** The service instance to be tested. */
    private AppointmentService appointmentService;

    /** DateTimeFormatter for the date format used in the tests. */
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");

    /**
     * Setup method executed before each test.
     *
     * <p>
     * This method initializes a new instance of {@link AppointmentService} before each
     * test is run.
     */
    @BeforeEach
    public void setUp() {
        appointmentService = new AppointmentService();
    }

    /**
     * Tests the addition of a new appointment to the service.
     *
     * <p>
     * Makes sure that the appointment is correctly stored and that all its fields are
     * correctly set.
     */
    @Test
    public void testAddAppointment() {
        appointmentService.addAppointment(
            new Appointment("001", "11-Oct-25", "Description of appointment 1"));

        Appointment appointment = appointmentService.getAppointment("001");
        assertNotNull(appointment);
        assertEquals(LocalDate.parse("11-Oct-25", formatter), appointment.getDate());
        assertEquals("Description of appointment 1", appointment.getDescription());
    }

    /**
     * Tests that attempting to add an appointment with a duplicate ID throws an
     * exception.
     *
     * <p>
     * This ensures that the service enforces unique appointment IDs.
     */
    @Test
    public void testAddAppointment_DuplicateId() {
        Appointment appointment1 = new Appointment("001", "11-Oct-25", "Description of appointment 1");
        Appointment appointment2 = new Appointment("001", "12-Oct-25", "Description of appointment 2");

        appointmentService.addAppointment(appointment1);
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.addAppointment(appointment2);
        });
    }

    /**
     * Tests the retrieval of an appointment by a valid ID.
     *
     * <p>
     * Makes sure that the correct appointment is returned when a valid ID is provided.
     */
    @Test
    public void testGetAppointment_ValidId() {
        Appointment appointment = new Appointment("002", "12-Oct-25", "Description of appointment 2");
        appointmentService.addAppointment(appointment);

        Appointment retrievedAppointment = appointmentService.getAppointment("002");
        assertNotNull(retrievedAppointment);
        assertEquals(LocalDate.parse("12-Oct-25", formatter), retrievedAppointment.getDate());
    }

    /**
     * Tests the retrieval of an appointment by an invalid ID.
     *
     * <p>
     * Makes sure that an exception is thrown when an ID that does not exist in the
     * service is provided.
     */
    @Test
    public void testGetAppointment_InvalidId() {
        assertThrows(NoSuchElementException.class, () -> {
            appointmentService.getAppointment("999");
        });
    }

    /**
     * Tests the retrieval of all appointments.
     *
     * <p>
     * Makes sure that all appointments added to the service are returned in a list.
     */
    @Test
    public void testGetAllAppointments() {
        Appointment appointment1 = new Appointment("001", "11-Oct-25", "Description of appointment 1");
        Appointment appointment2 = new Appointment("002", "12-Oct-25", "Description of appointment 2");

        appointmentService.addAppointment(appointment1);
        appointmentService.addAppointment(appointment2);

        List<Appointment> allAppointments = appointmentService.getAllAppointments();
        assertEquals(2, allAppointments.size());
    }

    /**
     * Tests the update of an appointment with a valid ID.
     *
     * <p>
     * Makes sure that the appointment's fields are correctly updated when a valid ID is
     * provided.
     */
    @Test
    public void testUpdateAppointment_ValidId() {
        Appointment appointment = new Appointment("003", "13-Oct-25", "Description of appointment 3");
        appointmentService.addAppointment(appointment);

        appointmentService.updateAppointment("003", "14-Oct-25", "I need to load the dishwasher but am coding :)");

        Appointment updatedAppointment = appointmentService.getAppointment("003");
        assertEquals(LocalDate.parse("14-Oct-25", formatter), updatedAppointment.getDate());
        assertEquals("I need to load the dishwasher but am coding :)", updatedAppointment.getDescription());
    }

    /**
     * Tests the update of an appointment with an invalid ID.
     *
     * <p>
     * Makes sure that an exception is thrown when attempting to update an appointment with
     * an ID that does not exist.
     */
    @Test
    public void testUpdateAppointment_InvalidId() {
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.updateAppointment("999", "14-Oct-25", "I need to load the dishwasher but am coding :)");
        });
    }

    /**
     * Tests the deletion of an appointment by a valid ID.
     *
     * <p>
     * Makes sure that the appointment is successfully removed from the service.
     */
    @Test
    public void testDeleteAppointment_ValidId() {
        Appointment appointment = new Appointment("004", "14-Oct-25", "Description of appointment 4");
        appointmentService.addAppointment(appointment);

        appointmentService.deleteAppointment("004");
        assertThrows(NoSuchElementException.class, () -> {
            appointmentService.getAppointment("004");
        });
    }

    /**
     * Tests the deletion of an appointment by an invalid ID.
     *
     * <p>
     * Makes sure that an exception is thrown when attempting to delete an appointment with
     * an ID that does not exist.
     */
    @Test
    public void testDeleteAppointment_InvalidId() {
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.deleteAppointment("999");
        });
    }
}

