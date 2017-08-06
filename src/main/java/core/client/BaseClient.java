package core.client;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.commons.io.IOUtils;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class BaseClient {
    private OAuthConsumer consumer;
    private HttpURLConnection request;

    public BaseClient(String key, String secret, String token, String tokenSecret) {
        this.consumer = new DefaultOAuthConsumer(key, secret);
        this.consumer.setTokenWithSecret(token, tokenSecret);
    }

    protected BaseClient get(String url) throws IOException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        URL u = new URL(url);
        request = (HttpURLConnection) u.openConnection();
        consumer.sign(request);
        request.connect();
        return this;
    }

    protected BaseClient post(String url) throws IOException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        URL u = new URL(url);
        request = (HttpURLConnection) u.openConnection();
        request.setRequestMethod("POST");
        request.setDoOutput(true);
        consumer.sign(request);
        request.connect();
        return this;
    }

    public int getResponseCode() throws IOException {
        return request.getResponseCode();
    }

    protected String getResponse() {
        try {
            return IOUtils.toString(request.getInputStream(), "UTF-8");
        } catch (IOException e) {
            return null;
        }
    }
}
