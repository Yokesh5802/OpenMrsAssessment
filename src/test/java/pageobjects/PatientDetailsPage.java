package pageobjects;

import java.io.File;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.Base;
import utility.Utility;

public class PatientDetailsPage extends Base {

	public PatientDetailsPage() {
		PageFactory.initElements(driver, this);
	}

	// PatientDetailsPage test data

	public static String patientID;
	public static String patientDetailPageTitle = "OpenMRS Electronic Medical Record";
	public static String captionInput = "Test Image";
	public static String uploadSuccesToaster = "The attachment was successfully uploaded.";
	public static String deletedPatientToaster = "Patient has been deleted successfully";
	public static String testDataFilePath = System.getProperty("user.dir") + File.separator + "Test Data"
			+ File.separator + "openMrsTestData.png";
	public static String patientDeleteReason = "Test";
	public static String patientRecordResult = "No matching records found";

	// PatientDetailsPage Objects
	@FindBy(xpath = "//div[normalize-space(text())='Start Visit']")
	public WebElement startVist_we;

	@FindBy(id = "start-visit-with-visittype-confirm")
	public WebElement startVistCfmbtn_we;

	@FindBy(id = "attachments.attachments.visitActions.default")
	public WebElement attachement_we;

	@FindBy(css = "input[type='file']")
	public WebElement fileInput_we;

	@FindBy(xpath = "//h3[text()='Caption']//following::textarea")
	public WebElement caption_we;

	@FindBy(xpath = "//button[text()='Upload file']")
	public WebElement uploadFileButton_we;

	@FindBy(xpath = "//div[@class='toast-container toast-position-top-right']//following-sibling::p")
	public WebElement toasterMessage_we;

	@FindBy(xpath = "//span[@class='PersonName-givenName']")
	public WebElement patientProfile_we;

	@FindBy(id = "org.openmrs.module.coreapps.deletePatient")
	public WebElement deleteButton_we;

	@FindBy(id = "delete-reason")
	public WebElement deleteReason_we;

	@FindBy(xpath = "//div[@id='delete-patient-creation-dialog']//button[text()='Confirm']")
	public WebElement deleteConfirmButton_we;

	@FindBy(xpath = "//em[text()='Patient ID']//following-sibling::span")
	public WebElement patientID_we;

	@FindBy(id = "patient-search")
	public WebElement patientSearch_we;

	@FindBy(xpath = "//tr[@class='odd']//td")
	public WebElement noRecord_we;

	@FindBy(css = ".att_thumbnails-container")
	public WebElement attachedImg_we;

	@FindBy(xpath = "//visitbyencountertype//li/a")
	public WebElement date_we;

	@FindBy(xpath = "//visitbyencountertype//div")
	public WebElement attachementTag_we;

	// PatientDetailsPage Actions

	public PatientDetailsPage startAndConfirmVist() {
		Utility.click(startVist_we, "Clicked on Start Visit");
		Utility.click(startVistCfmbtn_we, "Clicked on Confirm the visit");
		return this;
	}

	public PatientDetailsPage uploadFile(String path, String captionValue) {
		Utility.click(attachement_we, "Clicked on Attachment");
		Utility.sendKeys(fileInput_we, path);
		Utility.sendKeys(caption_we, captionValue, "Enter caption as" + captionValue);
		Utility.click(uploadFileButton_we);
		return this;
	}

	public PatientDetailsPage verfiyToaster(String expectedToaster) {
		Utility.explicitWait(toasterMessage_we);
		Utility.verifyContainsText(Utility.getText(toasterMessage_we), expectedToaster, "Verified toaster message...");
		return this;
	}

	public PatientDetailsPage clickPatientProfile() {
		Utility.click(patientProfile_we);
		return this;
	}

	public PatientDetailsPage atttachmentPresence() {
		Assert.assertEquals(attachedImg_we.isDisplayed(), true);
		Utility.logWithScreenshot("Verified attachement is attached ");
		return this;
	}

	public PatientDetailsPage recentVistEntrie() {
		Utility.verifyCurrentDate(date_we, Utility.dateFormat1);
		Utility.verifyContainsText(Utility.getText(attachementTag_we), "Attachment Upload",
				"Verified current date and  Attachment Upload tag");
		return this;
	}

	public PatientDetailsPage deletePatient(String reason, String expectedToaster) {
		patientID = Utility.getText(patientID_we);
		Utility.click(deleteButton_we);
		Utility.sendKeys(deleteReason_we, "Enter reason as " + reason);
		Utility.click(deleteConfirmButton_we);
		Utility.explicitWait(toasterMessage_we);
		Utility.verifyContainsText(Utility.getText(toasterMessage_we), expectedToaster, "Verified toaster message");
		return this;
	}

	public PatientDetailsPage findPatientRecord(String patienId, String expectedRecord) {

		Utility.sendKeys(patientSearch_we, patienId, "Search patiend id as: " + patienId);
		Utility.delay(1000);
		System.out.println(Utility.getText(noRecord_we));
		Utility.verifyContainsText(Utility.getText(noRecord_we), expectedRecord, "The deleted patient not listed");
		return this;
	}

}
