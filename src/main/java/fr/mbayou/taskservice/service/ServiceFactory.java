package fr.mbayou.taskservice.service;

import fr.mbayou.taskservice.service.impl.TaskServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Service factory
 */
public final class ServiceFactory {

    /**
     * Service instances
     */
    private static Map<Class, Object> services = new HashMap<>();

    /**
     * Return an instance of task service
     *
     * @return an instance of task service
     */
    public static TaskService getUserService() {
        if (!services.containsKey(TaskService.class)) {
            services.put(TaskService.class, new TaskServiceImpl());
        }
        return (TaskService) services.get(TaskService.class);
    }
}
