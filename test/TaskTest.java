import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * TaskTest Class
 *
 * <p>This class contains unit tests for the {@link Task} class. The tests verify the correct
 * functionality of the getter and setter methods, ensuring that valid data is accepted and that
 * invalid data is properly rejected with appropriate exceptions.
 *
 * <p><strong>Note:</strong> These tests are written using the JUnit 5 framework
 *
 * <p>Created by: Stephen Chryn
 * Date: 29-Sept-24
 * Version: 1.0
 */
public class TaskTest {

    /** The task instance to be tested. */
    private Task task;

    /**
     * Setup method executed before each test.
     *
     * <p>This method initializes a new instance of {@link Task} with predefined values
     * before each test is run.
     */
    @BeforeEach
    public void setUp() {
        task = new Task("1234567890", "Task Name", "Description of task");
    }

    /**
     * Tests the getter for the task ID.
     *
     * <p>Makes sure that the task ID is correctly returned.
     */
    @Test
    public void testGetTaskId() {
        assertEquals("1234567890", task.getId());
    }

    /**
     * Tests the getter for the name.
     *
     * <p>Makes sure that the name is correctly returned.
     */
    @Test
    public void testGetName() {
        assertEquals("Task Name", task.getName());
    }

    /**
     * Tests the getter for the description.
     *
     * <p>Makes sure that the description is correctly returned.
     */
    @Test
    public void testGetDescription() {
        assertEquals("Description of task", task.getDescription());
    }

    /**
     * Tests the setter for the name with valid data.
     *
     * <p>Makes sure that the name is correctly updated when valid data is provided.
     */
    @Test
    public void testSetName_Valid() {
        task.setName("Dishes");
        assertEquals("Dishes", task.getName());
    }

    /**
     * Tests the setter for the description with valid data.
     *
     * <p>Makes sure that the description is correctly updated when valid data is provided.
     */
    @Test
    public void testSetDescription_Valid() {
        task.setDescription("I need to load the dishwasher but am coding :)");
        assertEquals("I need to load the dishwasher but am coding :)", task.getDescription());
    }

    /**
     * Tests the setter for the name with invalid data.
     *
     * <p>Makes sure that an exception is thrown when attempting to set an invalid name.
     */
    @Test
    public void testSetName_Invalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            task.setName(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            task.setName("This Name Is Way Too Long to be the name of a task");
        });
    }

    /**
     * Tests the setter for the description with invalid data.
     *
     * <p>Makes sure that an exception is thrown when attempting to set an invalid description.
     */
    @Test
    public void testSetDescription_Invalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            task.setDescription(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            task.setDescription("This Name Is Way Too Long to be the description of a task");
        });
    }
}

