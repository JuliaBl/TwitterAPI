package core.pageobjects.pages;


import  static core.elements.Element.getElement;
import org.openqa.selenium.By;


public class MainPage {

    private static final By NEW_TWEET_BUTTON_LOCATOR_ID = By.id("global-new-tweet-button");
    private static final By USER_PROFILE_DROPDOWN_BUTTON_LOCATOR_ID = By.id("user-dropdown");
    private static final By USER_SIGN_OUT_BUTTON_LOCATOR_ID = By.id("signout-button");
    private static final By NEW_TWEET_MODAL_TEXT_AREA_LOCATOR_ID = By.id("tweet-box-global");
    private static final By NEW_TWEET_SUBMIT_BUTTON_LOCATOR_ID = By.xpath("//button[contains(@class,'js-tweet-btn') and not(contains(@class,'disabled'))]");
    private static final By CURRENT_USER_BUTTON_LOCATOR_CLASSNAME = By.xpath("//b[contains(@class, 'fullname')]");
    private static final By ALERT_MESSAGE_TEXT_LOCATOR_XPATH = By.xpath("//*[@id='message-drawer']//span[@class='message-text']");
    private static final By CLOSE_DIALOG_BUTTON_LOCATOR_XPATH = By.xpath("//*[contains(@id,'dialog')]/button");

   public MainPage(){
       getElement(NEW_TWEET_BUTTON_LOCATOR_ID).isElementVisible();
      }

    public void signOut() {
       getElement(USER_SIGN_OUT_BUTTON_LOCATOR_ID).click();
   }

   public MainPage clickNewTweet(){
       getElement(NEW_TWEET_BUTTON_LOCATOR_ID).click();
        return this;
   }

   public MainPage inputNewMessageTweet(String message){
       getElement(NEW_TWEET_MODAL_TEXT_AREA_LOCATOR_ID).sendKeys(message);
       return this;
   }

    public MainPage submitNewTweet() {
       getElement(NEW_TWEET_SUBMIT_BUTTON_LOCATOR_ID).click();

       return new MainPage();
    }

    public UsersPage goToUsersPage() {
      getElement(USER_PROFILE_DROPDOWN_BUTTON_LOCATOR_ID).click();
      getElement(CURRENT_USER_BUTTON_LOCATOR_CLASSNAME).click();
       return new UsersPage();
    }

    public MainPage openUserNavigationDropdownPanel() {
       getElement(USER_PROFILE_DROPDOWN_BUTTON_LOCATOR_ID).click();
       return this;
    }


    public String getAllertMessageText() {
    return getElement(ALERT_MESSAGE_TEXT_LOCATOR_XPATH).getText();
     }

    public MainPage closeTweetDialog() {
        getElement(CLOSE_DIALOG_BUTTON_LOCATOR_XPATH).click();
        return this;
    }
}

