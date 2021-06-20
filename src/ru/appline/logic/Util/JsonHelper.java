package ru.appline.logic.Util;

import com.google.gson.JsonObject;

public class JsonHelper {

    private static JsonObject jsonObject;
    private static JsonHelper jsonHelper;

    private JsonHelper() {

    }

    public static JsonHelper getInstance() {
        if (jsonHelper == null) {
            jsonHelper = new JsonHelper();
        }
        return jsonHelper;
    }

    public JsonHelper getJsonHelper() {
        if (jsonObject == null) {
            jsonObject = new JsonObject();
        }
        return this;
    }

    public JsonObject getMessage(String messageText) {
        jsonObject.remove("Result");
        jsonObject.addProperty("Message", messageText);
        return jsonObject;
    }

    public JsonObject getResult(Double result) {
        jsonObject.remove("Message");
        jsonObject.addProperty("Result", result);
        return jsonObject;
    }
}
