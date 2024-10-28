import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * TaskService Class
 *
 * <p>
 * This class provides a service layer for managing a collection of {@link Task}
 * objects. It supports basic CRUD (Create, Read, Update, Delete) operations 
 * on tasks and stores them in a HashMap with the task ID as the key.
 *
 * <p>
 * <strong>Note:</strong> This service ensures that task IDs are unique and
 * provides methods to retrieve, update, and delete tasks based on their ID.
 *
 * <p>
 * Created by: Stephen Chryn
 * Date: 29-Sept-24
 * Version: 1.0
 */
public class TaskService {

  /** Map to store tasks with task ID as the key. */
  private final Map<String, Task> tasks;

  /**
   * Constructor for TaskService.
   *
   * <p>
   * Initializes the service with an empty task list.
   */
  public TaskService() {
    this.tasks = new HashMap<>();
  }

  /**
   * Adds a new task to the service.
   *
   * @param task The {@link Task} object to be added.
   * @throws IllegalArgumentException if a task with the same ID already exists.
   */
  public void addTask(Task task) {
    if (tasks.containsKey(task.getId())) {
      throw new IllegalArgumentException("Task ID already exists.");
    }
    tasks.put(task.getId(), task);
  }

  /**
   * Retrieves a task by its ID.
   *
   * @param id The ID of the task to retrieve.
   * @return The {@link Task} object associated with the given ID, or null if no
   *         such task exists.
   */
  public Task getTask(String id) {
    return tasks.get(id);
  }

  /**
   * Retrieves all tasks in the service.
   *
   * @return A {@link List} of all {@link Task} objects stored in the service.
   */
  public List<Task> getAllTasks() {
    return new ArrayList<>(tasks.values());
  }

  /**
   * Updates the fields of an existing task.
   *
   * @param id          The ID of the task to update.
   * @param name        The new name (optional).
   * @param description The new description (optional).
   * @throws IllegalArgumentException if the task ID does not exist.
   */
  public void updateTask(String id, String name, String description) {
    Task task = tasks.get(id);
    if (task == null) {
      throw new IllegalArgumentException("Task ID does not exist.");
    }

    if (name != null && !name.isEmpty()) {
      task.setName(name);
    }
    if (description != null && !description.isEmpty()) {
      task.setDescription(description);
    }
  }

  /**
   * Deletes a task by its ID.
   *
   * @param id The ID of the task to delete.
   * @throws IllegalArgumentException if the task ID does not exist.
   */
  public void deleteTask(String id) {
    if (!tasks.containsKey(id)) {
      throw new IllegalArgumentException("Task ID does not exist.");
    }
    tasks.remove(id);
  }
}
