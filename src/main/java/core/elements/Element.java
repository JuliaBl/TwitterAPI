package core.elements;


import core.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Element{
    private By locator;

    protected Element(By locator) {
        this.locator = locator;
    }

    private FluentWait<WebDriver> baseWait(){
       return new FluentWait(WebDriverManager.getInstanse())
                .withTimeout(60, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Element is not visible");
    }

    private WebElement waitFor(Function<WebElement, ExpectedCondition<WebElement>> condition){
        return baseWait().until(condition.apply(findElement()));
    }

    private WebElement waitForElement() {
        waitForDocStateReady();
        WebElement webElement = null;
        int sleepingTime = 2000;

        try {
            sleep(sleepingTime);
            webElement = findElement();

            if (webElement == null) {
                sleep(sleepingTime);
            }

            if (webElement != null && webElement.isDisplayed()) {
                return webElement;

            } else {
                sleep(sleepingTime);
            }
        } catch (WebDriverException e) {
            e.getMessage();
        }

        return webElement;
    }

    private WebElement findElement() {
        return WebDriverManager.getInstanse().findElement(locator);
    }

    private void sleep(long sleepingTime) {

        try {
            Thread.sleep(sleepingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Element getElement(By locator) {

        return new Element(locator);
    }

    public void click() {
        waitForElement().click();
    }

    public void sendKeys( String value) {
      waitFor(ExpectedConditions::visibilityOf).sendKeys(value);
    }

    public String getText() {
        return waitFor(ExpectedConditions::visibilityOf).getText();
    }

    public boolean isElementVisible() {
        try {
            WebElement element = WebDriverManager.getInstanse().findElement(locator);
            if (element != null) {
                return element.isDisplayed();
            }
        } catch (NoSuchElementException e) {
            return false;
        }
        return false;
    }


    public WebElement getWrappedElement() {
        return  waitFor(ExpectedConditions::visibilityOf);
    }

    private static void waitForDocStateReady() {
        new WebDriverWait(WebDriverManager.getInstanse(), 50).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}

