package uiTests;


import core.TwitterCred;
import core.WebDriverManager;
import core.pageobjects.pages.LoginPage;
import core.pageobjects.pages.MainPage;
import core.pageobjects.pages.UsersPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class UITwitterTests {

    private LoginPage loginPage;
    private MainPage mainPage;
    private UsersPage usersPage;
    private String messageTweet;


    @BeforeClass
    public void setUp() {
       loginPage  = WebDriverManager.open();
    }

    @AfterClass
    public void tearDown() {
    //  WebDriverManager.close();

    }

    @Test
    public void testNewTweetAdd() {
        messageTweet = "UI_Tweet" + Math.abs(new Random().nextInt()) % 600 + 1;

        usersPage =
                loginPage
                        .tryLoginWithCredentials(TwitterCred.USER_NAME, TwitterCred.USER_PASSWORD, TwitterCred.CHALLENGE_KEY)
                        .clickNewTweet()
                        .inputNewMessageTweet(messageTweet)
                        .submitNewTweet()
                        .goToUsersPage();

        Assert.assertEquals(usersPage.getLatestTweetMessage(), messageTweet);

    }

    @Test(dependsOnMethods = "testNewTweetAdd")
    public void testLatestTweetDestroy() {

        String latestTweetMessage =
                usersPage
                        .removeTheLatestTweet()
                        .getLatestTweetMessage();

        Assert.assertFalse(latestTweetMessage.equals(messageTweet));
    }

    @Test(dependsOnMethods = "testLatestTweetDestroy")
    public void testTweetDuplicationError() {

        MainPage mainPage =
                new MainPage()
                        .clickNewTweet()
                        .inputNewMessageTweet(messageTweet)
                        .submitNewTweet()
                        .clickNewTweet()
                        .inputNewMessageTweet(messageTweet)
                        .submitNewTweet();

        Assert.assertEquals(mainPage.getAllertMessageText(), "You have already sent this Tweet.");


        mainPage.closeTweetDialog()
                .openUserNavigationDropdownPanel()
                .signOut();
    }

}


