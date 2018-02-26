package fr.mbayou.taskservice;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    @Override
    public String render(Object model) {
        return GsonProvider.getGeson().toJson(model);
    }

}