import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * AppointmentService Class
 *
 * <p>
 * This class provides a service layer for managing a collection of {@link Appointment}
 * objects. It supports basic CRUD (Create, Read, Update, Delete) operations 
 * on appointments and stores them in a HashMap with the appointment ID as the key.
 *
 * <p>
 * <strong>Note:</strong> This service ensures that appointment IDs are unique and
 * provides methods to retrieve, update, and delete appointments based on their ID.
 *
 * <p>
 * Created by: Stephen Chryn
 * Date: 29-Sept-24
 * Version: 1.0
 */
public class AppointmentService {

    /** Map to store appointments with appointment ID as the key. */
    private final Map<String, Appointment> appointments;

    /**
     * Constructor for AppointmentService.
     *
     * <p>
     * Initializes the service with an empty appointment list.
     */
    public AppointmentService() {
        this.appointments = new HashMap<>();
    }

    /**
     * Adds a new appointment to the service.
     *
     * @param appointment The {@link Appointment} object to be added.
     * @throws IllegalArgumentException if an appointment with the same ID already exists or the appointment is null.
     */
    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment cannot be null.");
        }
        if (appointments.containsKey(appointment.getId())) {
            throw new IllegalArgumentException("Appointment ID already exists.");
        }
        appointments.put(appointment.getId(), appointment);
    }

    /**
     * Retrieves an appointment by its ID.
     *
     * @param id The ID of the appointment to retrieve.
     * @return The {@link Appointment} object associated with the given ID.
     * @throws NoSuchElementException if no such appointment exists.
     */
    public Appointment getAppointment(String id) {
        if (!appointments.containsKey(id)) {
            throw new NoSuchElementException("Appointment ID does not exist.");
        }
        return appointments.get(id);
    }

    /**
     * Retrieves all appointments in the service.
     *
     * @return A {@link List} of all {@link Appointment} objects stored in the service.
     */
    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments.values());
    }

    /**
     * Updates the fields of an existing appointment.
     *
     * @param id          The ID of the appointment to update.
     * @param date        The new date (optional).
     * @param description The new description (optional).
     * @return true if the update was successful, false otherwise.
     * @throws IllegalArgumentException if the appointment ID does not exist.
     */
    public boolean updateAppointment(String id, String date, String description) {
        Appointment appointment = appointments.get(id);
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment ID does not exist.");
        }

        if (date != null && !date.isEmpty()) {
            appointment.setDate(date);
        }
        if (description != null && !description.isEmpty()) {
            appointment.setDescription(description);
        }
        return true;
    }

    /**
     * Deletes an appointment by its ID.
     *
     * @param id The ID of the appointment to delete.
     * @return true if the appointment was successfully deleted, false otherwise.
     * @throws IllegalArgumentException if the appointment ID does not exist.
     */
    public boolean deleteAppointment(String id) {
        if (!appointments.containsKey(id)) {
            throw new IllegalArgumentException("Appointment ID does not exist.");
        }
        appointments.remove(id);
        return true;
    }
    
    /**
     * Checks if an appointment exists by its ID.
     *
     * @param id The ID of the appointment to check.
     * @return true if the appointment exists, false otherwise.
     */
    public boolean appointmentExists(String id) {
        return appointments.containsKey(id);
    }
}

