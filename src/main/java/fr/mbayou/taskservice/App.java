package fr.mbayou.taskservice;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.gson.GsonBuilder;
import fr.mbayou.taskservice.model.Task;
import org.eclipse.jetty.http.HttpStatus;
import org.joda.time.format.ISODateTimeFormat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

/**
 * Application class
 */

public class App {

    private final static Map<Long, Task> tasks = new HashMap<>();

    /**
     * Main (entry point)
     *
     * @param args argument pass to application
     *
     * @throws Exception when an error occurred
     */
    public static void main(final String[] args) throws Exception {
        String projectDir = System.getProperty("user.dir");
        String staticDir = "/src/main/resources/public";
        staticFiles.externalLocation(projectDir + staticDir);

        port(8080);

        post("/tasks", (request, response) -> {
            final Task newTask = GsonProvider.getGeson().fromJson(request.body(), Task.class);
            Preconditions.checkArgument(!tasks.containsKey(newTask.id), "Task with ID " + newTask.id + " already exist");

            tasks.put(newTask.id, newTask);

            return halt(HttpStatus.NO_CONTENT_204);
        });

        get("/tasks", (request, response) -> tasks.values(), new JsonTransformer());

        get("/tasks/:id", (request, response) -> {
            final long id = Long.valueOf(request.params(":id"));
            if (tasks.containsKey(id)) {
                return tasks.get(id);
            } else {
                return halt(HttpStatus.NOT_FOUND_404);
            }
        }, new JsonTransformer());

        delete("/tasks/:id", (request, response) -> {
            final long id = Long.valueOf(request.params(":id"));
            Preconditions.checkArgument(tasks.containsKey(id), "Task to delete doesn't exist");

            tasks.remove(id);

            return halt(HttpStatus.NO_CONTENT_204);
        });
    }
}
