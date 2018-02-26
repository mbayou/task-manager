package fr.mbayou.taskservice.web.api;

import fr.mbayou.taskservice.GsonProvider;
import fr.mbayou.taskservice.JsonTransformer;
import fr.mbayou.taskservice.model.Task;
import fr.mbayou.taskservice.service.TaskService;
import org.eclipse.jetty.http.HttpStatus;

import static spark.Spark.*;

/**
 * Web access to tasks services
 */
public class TaskController {

    /**
     * Json transformer
     */
    private JsonTransformer transformer = new JsonTransformer();

    /**
     * Constructor and initiator of all routes
     *
     * @param taskService task's service
     */
    public TaskController(final TaskService taskService) {

        post("/tasks", (request, response) -> {
            final Task newTask = GsonProvider.getGson().fromJson(request.body(), Task.class);

            if (!taskService.isExists(newTask.id)) {
                taskService.createTask(newTask);
            } else {
                return halt(HttpStatus.CONFLICT_409);
            }

            return halt(HttpStatus.CREATED_201);
        });

        get("/tasks", (request, response) -> taskService.findTasks(), transformer);

        get("/tasks/:id", (request, response) -> {
            final long id = Long.valueOf(request.params(":id"));
            if (taskService.isExists(id)) {
                return taskService.getTask(id);
            } else {
                return halt(HttpStatus.NOT_FOUND_404);
            }
        }, transformer);

        delete("/tasks/:id", (request, response) -> {
            final long id = Long.valueOf(request.params(":id"));
            if (taskService.isExists(id)) {
                taskService.deleteTask(id);
            } else {
                return halt(HttpStatus.NOT_FOUND_404);
            }

            return halt(HttpStatus.NO_CONTENT_204);
        });
    }
}
