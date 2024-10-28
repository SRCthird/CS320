import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

/**
 * ContactServiceTest Class
 *
 * <p>
 * This class contains unit tests for the {@link ContactService} class. The
 * tests verify the correct functionality of methods for adding,
 * retrieving, updating, and deleting contacts, as well as ensuring that
 * validation rules are properly enforced.
 *
 * <p>
 * <strong>Note:</strong> These tests are written using the JUnit 5 framework.
 *
 * <p>
 * Created by: Stephen Chryn
 * Date: 22-Sept-24
 * Version: 1.0
 */
public class ContactServiceTest {

  /** The service instance to be tested. */
  private ContactService contactService;

  /**
   * Setup method executed before each test.
   *
   * <p>
   * This method initializes a new instance of {@link ContactService} before each
   * test is run.
   */
  @BeforeEach
  public void setUp() {
    contactService = new ContactService();
  }

  /**
   * Tests the addition of a new contact to the service.
   *
   * <p>
   * Makes sure that the contact is correctly stored and that all its fields are
   * correctly set.
   */
  @Test
  public void testAddContact() {
    contactService.addContact(
        new Contact("001", "John", "Doe", "1234567890", "123 Main St"));

    Contact contact = contactService.getContact("001");
    assertNotNull(contact);
    assertEquals("John", contact.getFirstName());
    assertEquals("Doe", contact.getLastName());
    assertEquals("123-456-7890", contact.getPhone());
    assertEquals("123 Main St", contact.getAddress());
  }

  /**
   * Tests that attempting to add a contact with a duplicate ID throws an
   * exception.
   *
   * <p>
   * This ensures that the service enforces unique contact IDs.
   */
  @Test
  public void testAddContact_DuplicateId() {
    Contact contact1 = new Contact("001", "John", "Doe", "1234567890", "123 Main St");
    Contact contact2 = new Contact("001", "Jane", "Smith", "0987654321", "456 Elm St");

    contactService.addContact(contact1);
    assertThrows(IllegalArgumentException.class, () -> {
      contactService.addContact(contact2);
    });
  }

  /**
   * Tests the retrieval of a contact by a valid ID.
   *
   * <p>
   * Makes sure that the correct contact is returned when a valid ID is provided.
   */
  @Test
  public void testGetContact_ValidId() {
    Contact contact = new Contact("002", "Jane", "Smith", "0987654321", "456 Elm St");
    contactService.addContact(contact);

    Contact retrievedContact = contactService.getContact("002");
    assertNotNull(retrievedContact);
    assertEquals("Jane", retrievedContact.getFirstName());
  }

  /**
   * Tests the retrieval of a contact by an invalid ID.
   *
   * <p>
   * Makes sure that null is returned when an ID that does not exist in the service
   * is provided.
   */
  @Test
  public void testGetContact_InvalidId() {
    Contact retrievedContact = contactService.getContact("999");
    assertNull(retrievedContact);
  }

  /**
   * Tests the retrieval of all contacts.
   *
   * <p>
   * Makes sure that all contacts added to the service are returned in a list.
   */
  @Test
  public void testGetAllContacts() {
    Contact contact1 = new Contact("001", "John", "Doe", "1234567890", "123 Main St");
    Contact contact2 = new Contact("002", "Jane", "Smith", "0987654321", "456 Elm St");

    contactService.addContact(contact1);
    contactService.addContact(contact2);

    List<Contact> allContacts = contactService.getAllContacts();
    assertEquals(2, allContacts.size());
  }

  /**
   * Tests the update of a contact with a valid ID.
   *
   * <p>
   * Makes sure that the contact's fields are correctly updated when a valid ID is
   * provided.
   */
  @Test
  public void testUpdateContact_ValidId() {
    Contact contact = new Contact("003", "John", "Doe", "1234567890", "123 Main St");
    contactService.addContact(contact);

    contactService.updateContact("003", "Johnny", "Doe", "5555555555", "789 Oak St");

    Contact updatedContact = contactService.getContact("003");
    assertEquals("Johnny", updatedContact.getFirstName());
    assertEquals("Doe", updatedContact.getLastName());
    assertEquals("555-555-5555", updatedContact.getPhone());
    assertEquals("789 Oak St", updatedContact.getAddress());
  }

  /**
   * Tests the update of a contact with an invalid ID.
   *
   * <p>
   * Makes sure that an exception is thrown when attempting to update a contact with
   * an ID that does not exist.
   */
  @Test
  public void testUpdateContact_InvalidId() {
    assertThrows(IllegalArgumentException.class, () -> {
      contactService.updateContact("999", "Johnny", "Doe", "5555555555", "789 Oak St");
    });
  }

  /**
   * Tests the deletion of a contact by a valid ID.
   *
   * <p>
   * Makes sure that the contact is successfully removed from the service.
   */
  @Test
  public void testDeleteContact_ValidId() {
    Contact contact = new Contact("004", "John", "Doe", "1234567890", "123 Main St");
    contactService.addContact(contact);

    contactService.deleteContact("004");
    assertNull(contactService.getContact("004"));
  }

  /**
   * Tests the deletion of a contact by an invalid ID.
   *
   * <p>
   * Makes sure that an exception is thrown when attempting to delete a contact with
   * an ID that does not exist.
   */
  @Test
  public void testDeleteContact_InvalidId() {
    assertThrows(IllegalArgumentException.class, () -> {
      contactService.deleteContact("999");
    });
  }
}
