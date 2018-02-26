package fr.mbayou.taskservice.service;

import fr.mbayou.taskservice.model.Task;

import java.util.Collection;

/**
 * Contains method to perform task management
 */
public interface TaskService {

    /**
     * Create task (should be a Data transfer object)
     *
     * @param task task to save
     */
    void createTask(Task task);

    /**
     * Retrieve a task from its identifier
     *
     * @param id task's identifier
     *
     * @return the task or null if doesn't exist
     */
    Task getTask(long id);

    /**
     * Retrieves all tasks
     *
     * @return collection of all tasks in memory
     */
    Collection<Task> findTasks();

    /**
     * Delete a specific task from identifier
     *
     * @param id task's identifier
     */
    void deleteTask(long id);

    /**
     * Check if a specific task exists
     *
     * @param id task's identifier
     *
     * @return true means task with this id exists
     */
    boolean isExists(long id);
}
