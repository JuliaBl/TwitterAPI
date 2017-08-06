package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.ScreenshotException;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class ScreenshotHelper {
	private static final String SCREENSHOT_FOLDER = "target/screenshots/";
	private static final String SCREENSHOT_FORMAT = ".jpg";

	public static void createScreenshot(ITestResult result, WebDriver driver) {
		if (!result.isSuccess()) {
			try {
				WebDriver returned = new Augmenter().augment(driver);
				if (returned != null) {
					File f = ((TakesScreenshot) returned).getScreenshotAs(OutputType.FILE);
					try {
						FileUtils.copyFile(f,
							new File(SCREENSHOT_FOLDER + result.getName() + LocalDateTime.now() + SCREENSHOT_FORMAT));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} catch (ScreenshotException se) {
				se.printStackTrace();
			}
		}
	}
}
