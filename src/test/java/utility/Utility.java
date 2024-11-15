package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.MediaEntityBuilder;

import base.Base;

public class Utility extends Base {

	public static String dateFormat1 = "dd.MMM.yyyy";

	public static void sendKeys(WebElement element, String value) {

		element.sendKeys(value);

	}

	public static void sendKeys(WebElement element, String value, String loggermessage) {

		element.sendKeys(value);
		logWithScreenshot(loggermessage);
	}

	public static void click(WebElement element) {

		element.click();

	}

	public static void click(WebElement element, String loggermessage) {

		element.click();
		logWithScreenshot(loggermessage);

	}

	public static void clear(WebElement element) {

		element.clear();

	}

	public static String getTitle() {

		return driver.getTitle();
	}

	public static String getText(WebElement element) {

		return element.getText();

	}

	public static void verifyContainsText(String actualText, String expectedTexts) {
		Assert.assertTrue(actualText.contains(expectedTexts), "Expected text not found: " + expectedTexts);
	}

	public static void verifyContainsText(String actualText, String expectedTexts, String loggermessage) {
		Assert.assertTrue(actualText.contains(expectedTexts), "Expected text not found: " + expectedTexts);
		logWithScreenshot(loggermessage);
	}

	public static void verifyContainsMultipleText(String actualText, String... expectedTexts) {
		for (String expected : expectedTexts) {
			Assert.assertTrue(actualText.contains(expected), "Expected text not found: " + expected);
		}
	}

	public static void explicitWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void delay(int Seconds) {
		try {
			Thread.sleep(Seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void verifyCurrentDate(WebElement dateElement, String dateFormat) {

		String currentDate = new SimpleDateFormat(dateFormat).format(new Date());
		String elementDateText = dateElement.getText().trim();
		Assert.assertEquals(elementDateText, currentDate, "The date in the element does not match the current date.");
	}

	public static String captureScreenshot(String screenshotName) {
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String screenshotPath = System.getProperty("user.dir") + "/Screenshot/Log" + screenshotName + "_" + timestamp
				+ ".png";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File(screenshotPath));
		} catch (IOException e) {
			System.out.println("Failed to capture screenshot: " + e.getMessage());
		}
		return screenshotPath;
	}

	public static void logWithScreenshot(String message) {
		String screenshotPath = captureScreenshot("LogScreenshot");
		try {
			logger.info(message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} catch (Exception e) {
			System.out.println("Failed to attach screenshot to log: " + e.getMessage());
		}
	}

}
