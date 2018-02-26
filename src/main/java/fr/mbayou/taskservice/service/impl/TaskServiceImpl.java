package fr.mbayou.taskservice.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import fr.mbayou.taskservice.model.Task;
import fr.mbayou.taskservice.service.TaskService;
import org.joda.time.format.ISODateTimeFormat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link TaskService}
 */
public class TaskServiceImpl implements TaskService {

    /**
     * Task's storage
     */
    private final static Map<Long, Task> tasks = new HashMap<>();

    @Override
    public void createTask(final Task task) {
        // Check iso format and if all field is completed
        Preconditions.checkArgument(task.id != null, "Task's identifier isn't set");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(task.title), "Task's title isn't set");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(task.description), "Task's description isn't set");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(task.dueDate), "Task's dueDate isn't set");
        // Check date format
        try {
            ISODateTimeFormat.dateTimeNoMillis().withZoneUTC().parseMillis(task.dueDate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Task's dueDate format is incorrect. Expected format : 1977-04-22T06:00:00Z");
        }

        tasks.put(task.id, task);
    }

    @Override
    public Task getTask(final long id) {
        return tasks.get(id);
    }

    @Override
    public Collection<Task> findTasks() {
        return tasks.values();
    }

    @Override
    public void deleteTask(final long id) {
        tasks.remove(id);
    }

    @Override
    public boolean isExists(final long id) {
        return tasks.containsKey(id);
    }
}
