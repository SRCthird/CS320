import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ContactService Class
 *
 * <p>This class provides a service layer for managing a collection of {@link Contact} objects.
 * It supports basic CRUD (Create, Read, Update, Delete) operations on contacts and stores them
 * in a HashMap with the contact ID as the key.
 *
 * <p><strong>Note:</strong> This service ensures that contact IDs are unique and provides
 * methods to retrieve, update, and delete contacts based on their ID.
 *
 * <p>Created by: Stephen Chryn
 * Date: 22-Sept-24
 * Version: 1.0
 */
public class ContactService {

    /** Map to store contacts with contact ID as the key. */
    private final Map<String, Contact> contacts;

    /**
     * Constructor for ContactService.
     *
     * <p>Initializes the service with an empty contact list.
     */
    public ContactService() {
        this.contacts = new HashMap<>();
    }

    /**
     * Adds a new contact to the service.
     *
     * @param contact The {@link Contact} object to be added.
     * @throws IllegalArgumentException if a contact with the same ID already exists.
     */
    public void addContact(Contact contact) {
        if (contacts.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException("Contact ID already exists.");
        }
        contacts.put(contact.getContactId(), contact);
    }

    /**
     * Retrieves a contact by its ID.
     *
     * @param contactId The ID of the contact to retrieve.
     * @return The {@link Contact} object associated with the given ID, or null if no such contact exists.
     */
    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }

    /**
     * Retrieves all contacts in the service.
     *
     * @return A {@link List} of all {@link Contact} objects stored in the service.
     */
    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts.values());
    }

    /**
     * Updates the fields of an existing contact.
     *
     * @param contactId The ID of the contact to update.
     * @param firstName The new first name (optional).
     * @param lastName The new last name (optional).
     * @param phone The new phone number (optional).
     * @param address The new address (optional).
     * @throws IllegalArgumentException if the contact ID does not exist.
     */
    public void updateContact(String contactId, String firstName, String lastName, String phone, String address) {
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID does not exist.");
        }

        if (firstName != null && !firstName.isEmpty()) {
            contact.setFirstName(firstName);
        }
        if (lastName != null && !lastName.isEmpty()) {
            contact.setLastName(lastName);
        }
        if (phone != null && !phone.isEmpty()) {
            contact.setPhone(phone);
        }
        if (address != null && !address.isEmpty()) {
            contact.setAddress(address);
        }
    }

    /**
     * Deletes a contact by its ID.
     *
     * @param contactId The ID of the contact to delete.
     * @throws IllegalArgumentException if the contact ID does not exist.
     */
    public void deleteContact(String contactId) {
        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID does not exist.");
        }
        contacts.remove(contactId);
    }
}

