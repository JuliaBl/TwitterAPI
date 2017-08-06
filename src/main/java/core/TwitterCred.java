package core;

import java.util.ResourceBundle;

public class TwitterCred {

    public static final String KEY = ResourceBundle.getBundle("twitter").getString("KEY");
    public static final String SECRET = ResourceBundle.getBundle("twitter").getString("SECRET");
    public static final String TOKEN = ResourceBundle.getBundle("twitter").getString("TOKEN");
    public static final String TOKEN_SECRET = ResourceBundle.getBundle("twitter").getString("TOKEN_SECRET");
    public static final String USER_NAME = ResourceBundle.getBundle("twitter").getString("USER_NAME");
    public static final String USER_PASSWORD = ResourceBundle.getBundle("twitter").getString("USER_PASSWORD");
    public static final String CHALLENGE_KEY = ResourceBundle.getBundle("twitter").getString("CHALLENGE_KEY");
}
