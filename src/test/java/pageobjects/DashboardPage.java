package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import utility.Utility;

public class DashboardPage extends Base {
	public DashboardPage() {
		PageFactory.initElements(driver, this);
	}

	// Dash board page test data
	public static String title = "Home";

	// Dash board page Objects
	@FindBy(xpath = "//a[contains(@href,'.registerPatient')]")
	public WebElement registerAPatient_we;

	// Dash board page actions

	public DashboardPage verfiyDashboard(String expected) {
		String actual = driver.getTitle();
		Utility.verifyContainsText(actual, expected, "Redirected to Dashboard page");
		return this;
	}

	public DashboardPage clickRegisterPatientMenu() {
		Utility.click(registerAPatient_we, "Clicked on Register a patient menu");
		return this;
	}

}
