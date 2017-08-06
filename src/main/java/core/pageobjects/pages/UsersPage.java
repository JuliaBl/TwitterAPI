package core.pageobjects.pages;


import  static core.elements.Element.getElement;

import core.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsersPage {

    private static final By LATEST_TWEET_ITEM_LOCATOR_XPATH = By.xpath("//*[@id='stream-items-id']/li");
    private static final By LATEST_TWEET_ITEM_TEXT_LOCATOR_XPATH = By.xpath("//p[contains(@class,'text')]");
    private static final By DROP_DOWN_BUTTON_LOCATOR_XPATH = By.xpath("//button[contains(@class,'actionButton') and contains(@class,'dropdown')]");
    private static final By DROP_DOWN_DELETE_BUTTON_LOCATOR_XPATH = By.xpath("//*[contains(@class,'actionDelete')]//button");
    private static final By DELETE_ALERT_MODAL_BUTTON_LOCATOR_XPATH = By.xpath("//button[contains(@class,'delete')]");
    private static final By EDIT_PROFILE_BUTTON_XPATH = By.xpath("//button[contains(text()='Edit profile')]");


    public UsersPage(){
        getElement(EDIT_PROFILE_BUTTON_XPATH).isElementVisible();
    }

    private WebElement findTheLatestTweet() {
    return getElement(LATEST_TWEET_ITEM_LOCATOR_XPATH).getWrappedElement();
    }

     private UsersPage openLatestTweetDropdown() {
        findTheLatestTweet()
                .findElement(DROP_DOWN_BUTTON_LOCATOR_XPATH).click();
         return this;
    }

    private UsersPage selectDeleteFromDropdown() {
        getElement(DROP_DOWN_DELETE_BUTTON_LOCATOR_XPATH).click();
        return this;
    }

    private UsersPage confirmDelete() {
        getElement(DELETE_ALERT_MODAL_BUTTON_LOCATOR_XPATH).click();
        return this;

    }

    public String getLatestTweetMessage() {
        return getElement(LATEST_TWEET_ITEM_TEXT_LOCATOR_XPATH).getText();
     }

    public UsersPage removeTheLatestTweet() {

        openLatestTweetDropdown();
        selectDeleteFromDropdown();
        confirmDelete();
        return this;
    }
}

