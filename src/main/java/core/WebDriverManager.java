package core;


import core.pageobjects.pages.LoginPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import util.PropertyLoader;
import java.io.File;



public class WebDriverManager {
    private static WebDriver driver;

    public static WebDriver getInstanse() {

        if (driver == null) {
           setChromeDriver();
           driver = new ChromeDriver();
         }

        driver.manage().window().maximize();
        return driver;
    }

    private static void setChromeDriver() {
        final File file = new File(PropertyLoader.loadProperty("path.webDriver"));
        System.setProperty(PropertyLoader.loadProperty("webDriver"), file.getAbsolutePath());
    }

    public static LoginPage open() {

        WebDriverManager.getInstanse().get(Config.getBasicUrl());
        return new LoginPage();
    }

    protected Alert switchToAlert() {
        try {
            return WebDriverManager.getInstanse().switchTo().alert();
        } catch (NoAlertPresentException e) {
            return null;
        }
    }

     public static void close() {

        driver.close();
    }
}
