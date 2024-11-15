package listener;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import base.Base;

public class SuiteListener implements ITestListener, IAnnotationTransformer {

	public static void takeScreenshot(String result) {

		TakesScreenshot screenshot = (TakesScreenshot) Base.driver;

		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File(
				System.getProperty("user.dir") + File.separator + "Screenshot" + File.separator + result + ".png");
		System.out.println(destinationFile.toString());

		try {
			FileUtils.copyFile(sourceFile, destinationFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSuccess(ITestResult result) {

		String fileName = result.getMethod().getMethodName().toString() + "_pass";

		takeScreenshot(fileName);

	}

	public void onTestFailure(ITestResult result) {
		String fileName = result.getMethod().getMethodName().toString() + "_fail";
		takeScreenshot(fileName);
	}

	public void transform(ITestAnnotation annotation,Class testClass, Constructor testConstructor, Method testMethod) {

		annotation.setRetryAnalyzer(RetryListener.class);

	}

}
