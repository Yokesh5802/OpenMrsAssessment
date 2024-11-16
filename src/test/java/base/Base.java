package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Base {

	public static WebDriver driver;
	public static Properties properties;
	public static ExtentReports extentReport;
	public static ExtentSparkReporter sparkReport;
	public static ExtentTest logger;
	public static String browser;

	@BeforeSuite
	public void setExtentReport() {

		File screenshotPath = new File(System.getProperty("user.dir") + File.separator + "Screenshot");

		try {
			FileUtils.cleanDirectory(screenshotPath);
		} catch (IOException e) {

			e.printStackTrace();
		}

		extentReport = new ExtentReports();
		sparkReport = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/openmrsreport.html");

		extentReport.attachReporter(sparkReport);
		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setDocumentTitle("OpenMRS Automation Report");
		sparkReport.config().setReportName("Automation Test Results");
		extentReport.setSystemInfo("Project Name", properties.getProperty("project"));
		extentReport.setSystemInfo("URL", properties.getProperty("url"));
	}

	@BeforeMethod
	// @Parameters("browser")
	public void initialization(@Optional("edge") String browserParam, ITestResult result, Method method) {

		// browser = browserParam;
		browser = properties.getProperty("browser").toLowerCase();

		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			throw new IllegalArgumentException("Invalid browser name: " + browser);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(properties.getProperty("url"));

		logger = extentReport.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());

	}

	@AfterMethod
	public void afterMethod(ITestResult result, Method method) {

		if (result.getStatus() == ITestResult.FAILURE) {

			logger.log(Status.FAIL, MarkupHelper.createLabel("Fail", ExtentColor.RED));
			logger.addScreenCaptureFromPath(System.getProperty("user.dir") + File.separator + "Screenshot"
					+ File.separator + result.getMethod().getMethodName() + "_fail.png");

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			logger.log(Status.PASS, MarkupHelper.createLabel("Pass", ExtentColor.GREEN));
			logger.addScreenCaptureFromPath(System.getProperty("user.dir") + File.separator + "Screenshot"
					+ File.separator + result.getMethod().getMethodName() + "_pass.png");

		} else if (result.getStatus() == ITestResult.SKIP) {

			logger.log(Status.SKIP, MarkupHelper.createLabel("Pass", ExtentColor.YELLOW));
		}
		logger.assignCategory(method.getDeclaringClass().toString().replace("TestRunner", "class: "));
		logger.assignCategory("browser: " + browser);
	}

	@AfterSuite
	public void afterSuite() {

		driver.quit();
		extentReport.flush();

	}

	public Base() {

		properties = new Properties();
		try {
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/Configuration/config.properties");
			properties.load(ip);
			System.out.println();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
