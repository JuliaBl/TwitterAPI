package util;


import org.json.JSONException;
import org.json.JSONObject;

public class JsonParseHelper {


    public static String getValueFromJsonBy(String json, final String value) {
        try {
            return String.valueOf(new JSONObject(json.substring(json.indexOf("{"))).get(value));
        } catch (JSONException e) {
            return null;
        }

    }

}
