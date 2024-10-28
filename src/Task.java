/**
 * Task Class
 *
 * <p>
 * This class represents the Task entity with fields for task ID, first name,
 * last name, phone number, and description. It provides methods to get
 * and set these fields, while enforcing certain validation rules to ensure
 * data integrity.
 *
 * <p>
 * <strong>Note:</strong> The task ID is immutable once assigned, while other
 * fields can be updated.
 *
 * <p>
 * Created by: Stephen Chryn
 * Date: 29-Sept-24
 * Version: 1.0
 */
public class Task {

  /** Unique identifier for the task, immutable and limited to 10 characters. */
  private final String id;

  /** First name of the task, limited to 10 characters. */
  private String name;

  /** Description of the task, limited to 50 characters. */
  private String description;

  /**
   * Constructor for Task.
   *
   * @param id          The unique ID for the task (must not be null or exceed 10
   *                    characters).
   * @param name        The first name of the task (must not be null or exceed 10
   *                    characters).
   * @param description The description of the task (must not be null or exceed 30
   *                    characters).
   * @throws IllegalArgumentException if any validation rules are violated.
   */
  public Task(
      String id,
      String name,
      String description) {
    if (id == null || id.length() > 10) {
      throw new IllegalArgumentException("Invalid Task ID");
    }
    this.id = id;

    this.setName(name);
    this.setDescription(description);
  }

  /**
   * Retrieves the task's unique ID.
   * 
   * @return The task ID.
   */
  public String getId() {
    return id;
  }

  /**
   * Retrieves the task's first name.
   * 
   * @return The first name.
   */
  public String getName() {
    return name;
  }

  /**
   * Retrieves the task's description.
   * 
   * @return The description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the task's first name.
   *
   * @param name The first name (must not be null or exceed 10 characters).
   * @throws IllegalArgumentException if the first name is null or exceeds 10
   *                                  characters.
   */
  public void setName(String name) {
    if (name == null || name.length() > 10) {
      throw new IllegalArgumentException("Invalid Name");
    }
    this.name = name;
  }

  /**
   * Sets the task's description.
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
}
