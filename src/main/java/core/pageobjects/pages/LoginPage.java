package core.pageobjects.pages;


import  static core.elements.Element.getElement;

import core.elements.Element;
import org.openqa.selenium.By;

public class LoginPage{

    private static final By SIGN_IN_NAME_INPUT_LOCATOR_ID = By.id("signin-email");
    private static final By SIGN_IN_PASSWORD_INPUT_LOCATOR_ID = By.id("signin-password");
    private static final By SIGN_IN_BUTTON_LOCATOR_XPATH = By.xpath("//button[@type='submit' and contains(@class,('submit'))]");
    private static final By CHALENGE_RESPONSE_INPUT_LOCATOR_ID = By.id("challenge_response");
    private static final By CHALENGE_SUBMIT_INPUT_LOCATOR_ID = By.id("email_challenge_submit");

    private LoginPage inputLoginAndPassword(String userName, String userPassword){
      getElement(SIGN_IN_NAME_INPUT_LOCATOR_ID).sendKeys(userName);
      getElement(SIGN_IN_PASSWORD_INPUT_LOCATOR_ID).sendKeys(userPassword);
      return this;
   }

   private LoginPage clickLogIn(){
       getElement(SIGN_IN_BUTTON_LOCATOR_XPATH).click();
       return  this;
   }

    private LoginPage inputChallengeResponse(String challengeKey){
       getElement(CHALENGE_RESPONSE_INPUT_LOCATOR_ID).sendKeys(challengeKey);
       getElement(CHALENGE_SUBMIT_INPUT_LOCATOR_ID).click();


        return this;
    }

    public MainPage tryLoginWithCredentials(String userName, String userPassword, String challengeKey){
       inputLoginAndPassword(userName, userPassword)
                .clickLogIn();

       if(getElement(CHALENGE_RESPONSE_INPUT_LOCATOR_ID).isElementVisible()){
           inputChallengeResponse(challengeKey);
       }

        return new MainPage();
    }

}
