package fr.mbayou.taskservice;

import com.google.common.base.Strings;
import fr.mbayou.taskservice.service.ServiceFactory;
import fr.mbayou.taskservice.web.api.TaskController;
import org.eclipse.jetty.http.HttpStatus;

import static spark.Spark.*;

/**
 * Application class
 */

public class App {

    /**
     * Main (entry point)
     *
     * @param args argument pass to application
     *
     * @throws Exception when an error occurred
     */
    public static void main(final String[] args) throws Exception {

        port(8080);
        ipAddress("127.0.0.1");

        // Bind check header
        before((request, response) -> {
            if (Strings.isNullOrEmpty(request.headers("Accept-Charset"))
                    || !request.headers("Accept-Charset").toLowerCase().equals("utf-8")
                    || Strings.isNullOrEmpty(request.headers("Content-Type"))
                    || !request.headers("Content-Type").toLowerCase().equals("application/json")) {
                halt(HttpStatus.UNSUPPORTED_MEDIA_TYPE_415);
            }
        });

        // Bind controller
        new TaskController(ServiceFactory.getUserService());

        // Bind exceptions
        exception(IllegalArgumentException.class, (exception, request, response) -> {
            response.status(HttpStatus.BAD_REQUEST_400);
            response.body(exception.getMessage());
        });

        // Use the number format exception to check if ID is parsable into long
        // So in this case I return a 404
        exception(NumberFormatException.class, (exception, request, response) -> {
            response.status(HttpStatus.NOT_FOUND_404);
        });

        after((request, response) -> {
            response.type("application/json");
            response.header("Accept-Charset", "UTF-8");
        });
    }
}
