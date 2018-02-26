package fr.mbayou.taskservice;

import com.google.gson.Gson;

/**
 * Gson provider
 */
public final class GsonProvider {

    /**
     * Gson instance
     */
    private static final Gson gson = new Gson();

    /**
     * Getter of Gson instance
     *
     * @return Gson instance
     */
    public static Gson getGson() {
        return gson;
    }
}
