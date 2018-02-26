package fr.mbayou.taskservice;

import spark.ResponseTransformer;

/**
 * Json transformer, use to transform response to JSON
 */
public class JsonTransformer implements ResponseTransformer {

    @Override
    public String render(Object model) {
        return GsonProvider.getGson().toJson(model);
    }

}