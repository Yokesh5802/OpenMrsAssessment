package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import utility.Utility;

public class DashboardPage extends Base {
	private static DashboardPage instance;

    private DashboardPage() {
        PageFactory.initElements(driver, this);
    }

    public static DashboardPage getInstance() {
        if (instance == null) {
            synchronized (DashboardPage.class) { 
                if (instance == null) {
                    instance = new DashboardPage();
                }
            }
        }
        return instance;
    }
	// Dash board page test data
	public static String title = "Home";

	// Dash board page Objects
	@FindBy(xpath = "//a[contains(@href,'.registerPatient')]")
	public WebElement registerAPatient_we;

	// Dash board page actions

	public DashboardPage verifyDashboardTitle(String expected) {
		String actual = driver.getTitle();
		Utility.verifyContainsText(actual, expected, "Redirected to Dashboard page");
		return this;
	}

	public DashboardPage navigateToRegisterPatientPage() {
		Utility.click(registerAPatient_we, "Clicked on Register a patient menu");
		return this;
	}

}
