import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

/**
 * TaskServiceTest Class
 *
 * <p>
 * This class contains unit tests for the {@link TaskService} class. The
 * tests verify the correct functionality of methods for adding,
 * retrieving, updating, and deleting tasks, as well as ensuring that
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
public class TaskServiceTest {

  /** The service instance to be tested. */
  private TaskService taskService;

  /**
   * Setup method executed before each test.
   *
   * <p>
   * This method initializes a new instance of {@link TaskService} before each
   * test is run.
   */
  @BeforeEach
  public void setUp() {
    taskService = new TaskService();
  }

  /**
   * Tests the addition of a new task to the service.
   *
   * <p>
   * Makes sure that the task is correctly stored and that all its fields are
   * correctly set.
   */
  @Test
  public void testAddTask() {
    taskService.addTask(
        new Task("001", "Task1", "Description of task 1"));

    Task task = taskService.getTask("001");
    assertNotNull(task);
    assertEquals("Task1", task.getName());
    assertEquals("Description of task 1", task.getDescription());
  }

  /**
   * Tests that attempting to add a task with a duplicate ID throws an
   * exception.
   *
   * <p>
   * This ensures that the service enforces unique task IDs.
   */
  @Test
  public void testAddTask_DuplicateId() {
    Task task1 = new Task("001", "Task1", "Description of task 1");
    Task task2 = new Task("001", "Task2", "Description of task 2");

    taskService.addTask(task1);
    assertThrows(IllegalArgumentException.class, () -> {
      taskService.addTask(task2);
    });
  }

  /**
   * Tests the retrieval of a task by a valid ID.
   *
   * <p>
   * Makes sure that the correct task is returned when a valid ID is provided.
   */
  @Test
  public void testGetTask_ValidId() {
    Task task = new Task("002", "Task2", "Description of task 2");
    taskService.addTask(task);

    Task retrievedTask = taskService.getTask("002");
    assertNotNull(retrievedTask);
    assertEquals("Task2", retrievedTask.getName());
  }

  /**
   * Tests the retrieval of a task by an invalid ID.
   *
   * <p>
   * Makes sure that null is returned when an ID that does not exist in the
   * service
   * is provided.
   */
  @Test
  public void testGetTask_InvalidId() {
    Task retrievedTask = taskService.getTask("999");
    assertNull(retrievedTask);
  }

  /**
   * Tests the retrieval of all tasks.
   *
   * <p>
   * Makes sure that all tasks added to the service are returned in a list.
   */
  @Test
  public void testGetAllTasks() {
    Task task1 = new Task("001", "Task1", "Description of task 1");
    Task task2 = new Task("002", "Task2", "Description of task 2");

    taskService.addTask(task1);
    taskService.addTask(task2);

    List<Task> allTasks = taskService.getAllTasks();
    assertEquals(2, allTasks.size());
  }

  /**
   * Tests the update of a task with a valid ID.
   *
   * <p>
   * Makes sure that the task's fields are correctly updated when a valid ID is
   * provided.
   */
  @Test
  public void testUpdateTask_ValidId() {
    Task task = new Task("003", "Task3", "Description of task 3");
    taskService.addTask(task);

    taskService.updateTask("003", "Dishes", "I need to load the dishwasher but am coding :)");

    Task updatedTask = taskService.getTask("003");
    assertEquals("Dishes", updatedTask.getName());
    assertEquals("I need to load the dishwasher but am coding :)", updatedTask.getDescription());
  }

  /**
   * Tests the update of a task with an invalid ID.
   *
   * <p>
   * Makes sure that an exception is thrown when attempting to update a task with
   * an ID that does not exist.
   */
  @Test
  public void testUpdateTask_InvalidId() {
    assertThrows(IllegalArgumentException.class, () -> {
      taskService.updateTask("999", "Dishes", "I need to load the dishwasher but am coding :)");
    });
  }

  /**
   * Tests the deletion of a task by a valid ID.
   *
   * <p>
   * Makes sure that the task is successfully removed from the service.
   */
  @Test
  public void testDeleteTask_ValidId() {
    Task task = new Task("004", "Task4", "Description of task 4");
    taskService.addTask(task);

    taskService.deleteTask("004");
    assertNull(taskService.getTask("004"));
  }

  /**
   * Tests the deletion of a task by an invalid ID.
   *
   * <p>
   * Makes sure that an exception is thrown when attempting to delete a task with
   * an ID that does not exist.
   */
  @Test
  public void testDeleteTask_InvalidId() {
    assertThrows(IllegalArgumentException.class, () -> {
      taskService.deleteTask("999");
    });
  }
}
