package apiTests;


import core.Config;
import core.TwitterCred;
import core.client.TwitterClient;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.JsonParseHelper;

import java.io.IOException;
import java.util.Random;


public class APITwitterTests {
    TwitterClient client;
    String text = "API_Tweet" + Math.abs(new Random().nextInt()) % 600 + 1;

    @BeforeMethod
    public void setUp() throws Exception {
        client = new TwitterClient(TwitterCred.KEY, TwitterCred.SECRET, TwitterCred.TOKEN, TwitterCred.TOKEN_SECRET);
    }

    @Test
    public void verifyHomeTimeLineFields() throws OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
       client.postStatus(text, null);
       String response = client.getHomeTimeLine();

        Assert.assertNotNull(JsonParseHelper.getValueFromJsonBy(response, "created_at"));
        Assert.assertEquals(JsonParseHelper.getValueFromJsonBy(response, "retweet_count"), "0");
        Assert.assertNotNull(JsonParseHelper.getValueFromJsonBy(response, "text"));
    }


    @Test()
    public void verifyStatusRemovedById() throws OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
     String destroyId = JsonParseHelper.getValueFromJsonBy(client.getHomeTimeLine(), "id_str");
     String destroyJson = client.postDestroyStatusById(destroyId, null);

     Assert.assertTrue(client.getResponseCode() == 200);
     Assert.assertTrue(JsonParseHelper.getValueFromJsonBy(destroyJson, "id_str").equals(destroyId));
    }

    @Test()
    public void verifyTextUpdated()throws OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException{
    String getIdStatus = JsonParseHelper.getValueFromJsonBy(client.getHomeTimeLine(), "id_str");
    client.postStatus(text, getIdStatus);

    Assert.assertEquals(text, JsonParseHelper.getValueFromJsonBy(client.getHomeTimeLine(), "text"));
    }

    @Test
    public void verifyDuplicateTweetError()throws OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException{
        client.postStatus(text, null);
        Assert.assertTrue(client.getResponseCode() == 200);

        client.postStatus(text, null);
        Assert.assertTrue(client.getResponseCode() == 403);
      }
}
