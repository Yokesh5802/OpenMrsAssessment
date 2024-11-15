package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import utility.Utility;

/*
 * @author : Yokesh M
 */

public class LoginPage extends Base {

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Login Page Test Data

	public static String usernameInput = "Admin";
	public static String passwordInput = "Admin123";
	public static String locationToSelect = "Pharmacy";

	// Login Page Objects

	@FindBy(id = "username")
	public WebElement username_we;

	@FindBy(id = "password")
	public WebElement password_we;

	@FindBy(xpath = "//ul[@id='sessionLocation']//li")
	public List<WebElement> locationList_we;

	@FindBy(id = "loginButton")
	public WebElement logInButton_we;

	// Login Page Actions
	public LoginPage enterUsername(String username) {
		Utility.clear(username_we);
		Utility.sendKeys(username_we, username, "Entered Username as: " + username);
		return this;
	}

	public LoginPage enterPassword(String password) {
		Utility.clear(password_we);
		Utility.sendKeys(password_we, password, "Entered Password as: " + password);
		return this;
	}

	public LoginPage selectLocation(String location) {
		for (WebElement location_we : locationList_we) {
			if (location_we.getText().contains(location)) {
				Utility.click(location_we, "Selected location as: " + location);
			}
		}
		return this;
	}

	public LoginPage clickLogin() {
		Utility.click(logInButton_we, "clicked on Login");
		return this;
	}

}
