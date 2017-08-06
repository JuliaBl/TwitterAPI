package core;

public class Config {
    public static final String BASIC_URL = "https://twitter.com/";
    public static final String BASIC_URL_API = "https://api.twitter.com/1.1/";

    public static String getBasicUrl() {
        return BASIC_URL;
    }

    public static String getBasicUrlApi(){
        return BASIC_URL_API;
    }
}
