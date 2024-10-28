/**
 * Contact Class
 *
 * <p>This class represents the Contact entity with fields for contact ID, first name, last name,
 * phone number, and address. It provides methods to get and set these fields, while enforcing
 * certain validation rules to ensure data integrity.
 *
 * <p><strong>Note:</strong> The contact ID is immutable once assigned, while other fields can be
 * updated. Phone numbers are stored in the format ###-###-#### but can be created without the hyphens.
 *
 * <p>Created by: Stephen Chryn
 * Date: 22-Sept-24
 * Version: 1.0
 */
public class Contact {
  
  /** Unique identifier for the contact, immutable and limited to 10 characters. */
  private final String contactId;
  
  /** First name of the contact, limited to 10 characters. */
  private String firstName;
  
  /** Last name of the contact, limited to 10 characters. */
  private String lastName;
  
  /** Phone number of the contact, stored in the format ###-###-####. */
  private String phone;
  
  /** Address of the contact, limited to 30 characters. */
  private String address;

  /**
   * Constructor for Contact.
   *
   * @param contactId The unique ID for the contact (must not be null or exceed 10 characters).
   * @param firstName The first name of the contact (must not be null or exceed 10 characters).
   * @param lastName The last name of the contact (must not be null or exceed 10 characters).
   * @param phone The phone number of the contact (must be a valid 10-digit number).
   * @param address The address of the contact (must not be null or exceed 30 characters).
   * @throws IllegalArgumentException if any validation rules are violated.
   */
  public Contact(
      String contactId,
      String firstName,
      String lastName,
      String phone,
      String address) {
    if (contactId == null || contactId.length() > 10) {
      throw new IllegalArgumentException("Invalid Contact ID");
    }
    this.contactId = contactId;

    this.setFirstName(firstName);
    this.setLastName(lastName);
    this.setPhone(phone);
    this.setAddress(address);
  }

  /** 
   * Retrieves the contact's unique ID.
   * 
   * @return The contact ID.
   */
  public String getContactId() {
    return contactId;
  }

  /** 
   * Retrieves the contact's first name.
   * 
   * @return The first name.
   */
  public String getFirstName() {
    return firstName;
  }

  /** 
   * Retrieves the contact's last name.
   * 
   * @return The last name.
   */
  public String getLastName() {
    return lastName;
  }

  /** 
   * Retrieves the contact's phone number.
   * 
   * @return The phone number.
   */
  public String getPhone() {
    return phone;
  }

  /** 
   * Retrieves the contact's address.
   * 
   * @return The address.
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the contact's first name.
   *
   * @param firstName The first name (must not be null or exceed 10 characters).
   * @throws IllegalArgumentException if the first name is null or exceeds 10 characters.
   */
  public void setFirstName(String firstName) {
    if (firstName == null || firstName.length() > 10) {
      throw new IllegalArgumentException("Invalid First Name");
    }
    this.firstName = firstName;
  }

  /**
   * Sets the contact's last name.
   *
   * @param lastName The last name (must not be null or exceed 10 characters).
   * @throws IllegalArgumentException if the last name is null or exceeds 10 characters.
   */
  public void setLastName(String lastName) {
    if (lastName == null || lastName.length() > 10) {
      throw new IllegalArgumentException("Invalid Last Name");
    }
    this.lastName = lastName;
  }

  /**
   * Sets the contact's phone number.
   *
   * @param phone The phone number (must be a valid 10-digit number).
   * @throws IllegalArgumentException if the phone number is null or invalid.
   */
  public void setPhone(String phone) {
    if (phone == null) {
      throw new IllegalArgumentException("Phone Number cannot be null");
    }
    String newPhone = convertToValidPhoneNumber(phone);
    if (!isValidPhoneNumber(newPhone)) {
      throw new IllegalArgumentException("Invalid Phone Number format");
    }
    
    this.phone = newPhone;
  }

  /**
   * Sets the contact's address.
   *
   * @param address The address (must not be null or exceed 30 characters).
   * @throws IllegalArgumentException if the address is null or exceeds 30 characters.
   */
  public void setAddress(String address) {
    if (address == null || address.length() > 30) {
      throw new IllegalArgumentException("Invalid Address");
    }
    this.address = address;
  }

  /**
   * Validates the phone number format.
   *
   * @param phoneNumber The phone number to validate.
   * @return True if the phone number matches the ###-###-#### format, false otherwise.
   */
  public static boolean isValidPhoneNumber(String phoneNumber) {
    // Regex to match the phone number format ###-###-####
    return phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}");
  }

  /**
   * Converts a raw phone number input to the standard ###-###-#### format.
   *
   * @param input The raw phone number input (can contain non-digit characters).
   * @return The formatted phone number.
   * @throws IllegalArgumentException if the input does not contain exactly 10 digits.
   */
  public static String convertToValidPhoneNumber(String input) {
    // Remove all non-digit characters from the input
    String digitsOnly = input.replaceAll("\\D", "");

    // Check if the resulting string has exactly 10 digits
    if (digitsOnly.length() == 10) {
      // Format the string into ###-###-#### format
      return digitsOnly.replaceAll("(\\d{3})(\\d{3})(\\d{4})", "$1-$2-$3");
    } else {
      throw new IllegalArgumentException("Invalid Phone Number");
    }
  }
}

