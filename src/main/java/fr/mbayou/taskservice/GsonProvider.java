package fr.mbayou.taskservice;

import com.google.gson.Gson;

public final class GsonProvider {

    private static final Gson gson = new Gson();

    public static Gson getGeson() {
        return gson;
    }
}
