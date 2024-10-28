import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * ContactTest Class
 *
 * <p>This class contains unit tests for the {@link Contact} class. The tests verify the correct
 * functionality of the getter and setter methods, ensuring that valid data is accepted and that
 * invalid data is properly rejected with appropriate exceptions.
 *
 * <p><strong>Note:</strong> These tests are written using the JUnit 5 framework
 *
 * <p>Created by: Stephen Chryn
 * Date: 22-Sept-24
 * Version: 1.0
 */
public class ContactTest {

    /** The contact instance to be tested. */
    private Contact contact;

    /**
     * Setup method executed before each test.
     *
     * <p>This method initializes a new instance of {@link Contact} with predefined values
     * before each test is run.
     */
    @BeforeEach
    public void setUp() {
        contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
    }

    /**
     * Tests the getter for the contact ID.
     *
     * <p>Makes sure that the contact ID is correctly returned.
     */
    @Test
    public void testGetContactId() {
        assertEquals("1234567890", contact.getContactId());
    }

    /**
     * Tests the getter for the first name.
     *
     * <p>Makes sure that the first name is correctly returned.
     */
    @Test
    public void testGetFirstName() {
        assertEquals("John", contact.getFirstName());
    }

    /**
     * Tests the getter for the last name.
     *
     * <p>Makes sure that the last name is correctly returned.
     */
    @Test
    public void testGetLastName() {
        assertEquals("Doe", contact.getLastName());
    }

    /**
     * Tests the getter for the phone number.
     *
     * <p>Makes sure that the phone number is correctly returned in the expected format.
     */
    @Test
    public void testGetPhone() {
        assertEquals("123-456-7890", contact.getPhone());
    }

    /**
     * Tests the getter for the address.
     *
     * <p>Makes sure that the address is correctly returned.
     */
    @Test
    public void testGetAddress() {
        assertEquals("123 Main St", contact.getAddress());
    }

    /**
     * Tests the setter for the first name with valid data.
     *
     * <p>Makes sure that the first name is correctly updated when valid data is provided.
     */
    @Test
    public void testSetFirstName_Valid() {
        contact.setFirstName("Jane");
        assertEquals("Jane", contact.getFirstName());
    }

    /**
     * Tests the setter for the last name with valid data.
     *
     * <p>Makes sure that the last name is correctly updated when valid data is provided.
     */
    @Test
    public void testSetLastName_Valid() {
        contact.setLastName("Smith");
        assertEquals("Smith", contact.getLastName());
    }

    /**
     * Tests the setter for the phone number with valid data.
     *
     * <p>Makes sure that the phone number is correctly updated and formatted when valid data is provided.
     */
    @Test
    public void testSetPhone_Valid() {
        contact.setPhone("9876543210");
        assertEquals("987-654-3210", contact.getPhone());
        contact.setPhone("603-555-0101");
        assertEquals("603-555-0101", contact.getPhone());
    }

    /**
     * Tests the setter for the address with valid data.
     *
     * <p>Makes sure that the address is correctly updated when valid data is provided.
     */
    @Test
    public void testSetAddress_Valid() {
        contact.setAddress("456 Oak St");
        assertEquals("456 Oak St", contact.getAddress());
    }

    /**
     * Tests the setter for the first name with invalid data.
     *
     * <p>Makes sure that an exception is thrown when attempting to set an invalid first name.
     */
    @Test
    public void testSetFirstName_Invalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName("This Name Is Way Too Long");
        });
    }

    /**
     * Tests the setter for the last name with invalid data.
     *
     * <p>Makes sure that an exception is thrown when attempting to set an invalid last name.
     */
    @Test
    public void testSetLastName_Invalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setLastName(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setLastName("This Name Is Also Way Too Long");
        });
    }

    /**
     * Tests the setter for the phone number with invalid data.
     *
     * <p>Makes sure that an exception is thrown when attempting to set an invalid phone number.
     */
    @Test
    public void testSetPhone_Invalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone("abc123");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone("123-abc-7890");
        });
    }

    /**
     * Tests the setter for the address with invalid data.
     *
     * <p>Makes sure that an exception is thrown when attempting to set an invalid address.
     */
    @Test
    public void testSetAddress_Invalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setAddress(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setAddress("123456789012345678901234567890 +1 :)");
        });
    }
}

