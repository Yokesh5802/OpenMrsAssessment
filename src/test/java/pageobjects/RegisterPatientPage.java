package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import utility.Utility;

public class RegisterPatientPage extends Base {
	public RegisterPatientPage() {
		PageFactory.initElements(driver, this);
	}

	// RegisterPatientPage Test Data

	public static String nameInput = "Yoki";
	public static String familNameInput = "Ben";
	public static String genderInput = "Male";
	public static String birthDateInput = "5";
	public static String monthInput = "November";
	public static String birthYearInput = "1988";
	public static String address1Input = "Agra";
	public static String address2Input = "Strand Rd";
	public static String cityInput = "Agra";
	public static String stateInput = "Uttar Pradesh";
	public static String countryInput = "India";
	public static String postalcodeInput = "282003";
	public static String phoneNumberInput = "8976543211";
	public static String patientDetailPageTitle = "OpenMRS Electronic Medical Record";
	
	// RegisterPatientPage Objects

	@FindBy(name = "givenName")
	public WebElement givenName_we;

	@FindBy(name = "familyName")
	public WebElement familyName_we;

	@FindBy(id = "next-button")
	public WebElement nextButton_we;

	@FindBy(xpath = "//select[@id='gender-field']//option")
	public List<WebElement> genderList_we;

	@FindBy(id = "birthdateDay-field")
	public WebElement birthDateDay_we;

	@FindBy(xpath = "//select[@id='birthdateMonth-field']//option")
	public List<WebElement> birthdateMonthList_we;

	@FindBy(id = "birthdateYear-field")
	public WebElement birthdateYear_we;

	@FindBy(id = "address1")
	public WebElement address1_we;

	@FindBy(id = "address2")
	public WebElement address2_we;

	@FindBy(id = "cityVillage")
	public WebElement cityVillage_we;

	@FindBy(id = "stateProvince")
	public WebElement stateProvince_we;

	@FindBy(id = "country")
	public WebElement country_we;

	@FindBy(id = "postalCode")
	public WebElement postalCode_we;

	@FindBy(name = "phoneNumber")
	public WebElement phoneNumber_we;

	@FindBy(xpath = "//span[text()='Name: ']//parent::p")
	public WebElement patientName_we;

	@FindBy(xpath = "//span[text()='Gender: ']//parent::p")
	public WebElement patientGender_we;

	@FindBy(xpath = "//span[text()='Birthdate: ']//parent::p")
	public WebElement patientBirthdate_we;

	@FindBy(xpath = "//span[text()='Address: ']//parent::p")
	public WebElement patientAddress_we;

	@FindBy(xpath = "//span[text()='Phone Number: ']//parent::p")
	public WebElement patientPhoneNumber_we;

	@FindBy(id = "submit")
	public WebElement confirmButton_we;
	
	
	// RegisterPatientPage Actions
	
	public RegisterPatientPage clickNextButton() {
		Utility.click(nextButton_we);
		return this;
	}

	public RegisterPatientPage enterPatientName(String firstName, String lastName) {
		Utility.sendKeys(givenName_we, firstName, "Entereded name as: " + firstName);
		Utility.sendKeys(familyName_we, lastName, "Entereded family name as: " + lastName);
		return this;
	}

	public RegisterPatientPage selectGender(String genderSelect) {
		for (WebElement gender : genderList_we) {
			if (Utility.getText(gender).contains(genderSelect)) {
				Utility.click(gender, "Selected gender as: " + genderSelect);
			}
		}
		return this;
	}

	public RegisterPatientPage selectBirthDate(String brithdateValue, String birthmonthValue, String birthyearValue) {
		Utility.sendKeys(birthDateDay_we, brithdateValue, "Entereded the birthdate as: " + brithdateValue);
		for (WebElement month : birthdateMonthList_we) {
			if (Utility.getText(month).contains(birthmonthValue)) {
				Utility.click(month, "Selected month as: " + birthmonthValue);
			}
		}
		Utility.sendKeys(birthdateYear_we, birthyearValue, "Entereded birth year as: " + birthyearValue);
		return this;
	}

	public RegisterPatientPage enterAddressDetails(String address1, String address2, String city, String state,
			String country, String postalcode) {
		Utility.sendKeys(address1_we, address1, "Entered address as: " + address1);
		Utility.sendKeys(address2_we, address2, "Entered address as: " + address2);
		Utility.sendKeys(cityVillage_we, city, "Entered City/Village as: " + city);
		Utility.sendKeys(stateProvince_we, state, "Entered State as: " + state);
		Utility.sendKeys(country_we, country, "Entered Country as: " + country);
		Utility.sendKeys(postalCode_we, postalcode, "Entered Postalcode as: " + postalcode);
		return this;
	}

	public RegisterPatientPage phoneNumber(String phonenumberValue) {
		Utility.sendKeys(phoneNumber_we, phonenumberValue, "Entered phonenumber as: " + phonenumberValue);
		return this;
	}

	public RegisterPatientPage detailsVerification(String expectedName, String expectedFamilyName, String expectedGender,
			String expectedBirthDate, String expectedAddress1, String expectedAddress2, String expectedCity,
			String expectedState, String expectedCountry, String expectedPostalCode, String expectedPhoneNumber) {
		Utility.logWithScreenshot("Verified Patient name...");
		Utility.verifyContainsMultipleText(Utility.getText(patientName_we), expectedName, expectedFamilyName);
		Utility.verifyContainsText(Utility.getText(patientGender_we), expectedGender, "Verified patient gender...");
		Utility.verifyContainsText(Utility.getText(patientBirthdate_we), expectedBirthDate,
				"Verified patient birthdate...");
		Utility.logWithScreenshot("Verified patient address...");
		Utility.verifyContainsMultipleText(Utility.getText(patientAddress_we), expectedAddress1, expectedAddress2,
				expectedCity, expectedState, expectedCountry, expectedPostalCode);
		Utility.verifyContainsText(Utility.getText(patientPhoneNumber_we), expectedPhoneNumber,
				"Verified patient phone number...");
		return this;
	}

	public RegisterPatientPage clickConfirmButton(String expectedTitle) {
		Utility.click(confirmButton_we);
		Utility.verifyContainsText(Utility.getTitle(), expectedTitle, "Redirected to Patient details page ...");
		return this;

	}

}
