package core.client;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import core.Config;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TwitterClient extends BaseClient{

    public TwitterClient(String key, String secret, String token, String tokenSecret) {
        super(key, secret, token, tokenSecret);
    }

    public String getHomeTimeLine()throws OAuthCommunicationException,OAuthExpectationFailedException, OAuthMessageSignerException, IOException{
       String url = Config.getBasicUrlApi() + "statuses/home_timeline.json";
       get(url);
       return getResponse();
    }

    /**
     * Destroy tweet by id
     * @param id
     * @param trimUser
     * @return response json or null
     * @throws IOException
     * @throws OAuthCommunicationException
     * @throws OAuthExpectationFailedException
     * @throws OAuthMessageSignerException
     */
    public String postDestroyStatusById(@NotNull String id, @Nullable String trimUser)
            throws IOException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        String url = Config.getBasicUrlApi() + "statuses/destroy/" + id + ".json";
        Map<String, String> parametersMap = new HashMap<>();
        parametersMap.put("trim_user", trimUser);
        post(url + getParameters(parametersMap));
        return getResponse();
 }

    /**
     * Twitter post new Tweet
     * @param status
     * @param updateInReplyToStatusId
     * @return response json or null
     * @throws IOException
     * @throws OAuthCommunicationException
     * @throws OAuthExpectationFailedException
     * @throws OAuthMessageSignerException
     */
    public String postStatus(@NotNull String status, @Nullable String updateInReplyToStatusId)
            throws IOException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        String url = Config.getBasicUrlApi() + "statuses/update.json";
        Map<String, String> parametersMap = new HashMap<>();
        parametersMap.put("status", status.replaceAll(" ", "%20").replaceAll(",", "%27"));
        parametersMap.put("in_reply_to_status_id", updateInReplyToStatusId);

       post(url + getParameters(parametersMap));
       return getResponse();
    }


    private String getParameters(Map<String, String> parametersMap) {
        String parameters = "?";
        for (Map.Entry<String, String> entry : parametersMap.entrySet()) {
            if (entry.getValue() != null) {
                parameters += String.format("%s=%s&", entry.getKey(), entry.getValue());
            }
        }
        return parameters;
    }
}
