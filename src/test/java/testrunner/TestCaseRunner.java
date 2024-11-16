package testrunner;

import org.testng.annotations.Test;

import base.Base;
import pageobjects.DashboardPage;
import pageobjects.LoginPage;
import pageobjects.PatientDetailsPage;
import pageobjects.RegisterPatientPage;

public class TestCaseRunner extends Base {

	@Test(description = "To Test the complete flow of registering a patient, "
			+ "verifying details, managing visits, uploading attachments, and deleting the patient.")
	public void testPatientWorkflow() {

		LoginPage.getInstance()
				.enterUsername(LoginPage.usernameInput)
				.enterPassword(LoginPage.passwordInput)
				.selectLocation(LoginPage.locationToSelect)
				.clickLogin();

		DashboardPage.getInstance()
				.verifyDashboardTitle(DashboardPage.title)
				.navigateToRegisterPatientPage();

		RegisterPatientPage.getInstance()
				.enterPatientName(RegisterPatientPage.nameInput, RegisterPatientPage.familNameInput)
				.clickNextButton()
				.selectGender(RegisterPatientPage.genderInput)
				.clickNextButton()
				.selectBirthDate(RegisterPatientPage.birthDateInput, RegisterPatientPage.monthInput,
						RegisterPatientPage.birthYearInput)
				.clickNextButton()
				.enterAddressDetails(RegisterPatientPage.address1Input, RegisterPatientPage.address2Input,
						RegisterPatientPage.cityInput, RegisterPatientPage.stateInput, RegisterPatientPage.countryInput,
						RegisterPatientPage.postalcodeInput)
				.clickNextButton()
				.enterPhoneNumber(RegisterPatientPage.phoneNumberInput)
				.clickNextButton(2)
				.verifyPatientDetails(RegisterPatientPage.nameInput, RegisterPatientPage.familNameInput,
						RegisterPatientPage.genderInput, RegisterPatientPage.birthDateInput,
						RegisterPatientPage.address1Input, RegisterPatientPage.address2Input,
						RegisterPatientPage.cityInput, RegisterPatientPage.stateInput, RegisterPatientPage.countryInput,
						RegisterPatientPage.postalcodeInput, RegisterPatientPage.phoneNumberInput)
				.confirmPatientRegistration(RegisterPatientPage.patientDetailPageTitle);

		PatientDetailsPage.getInstance()
				.startAndConfirmVisit()
				.uploadFile(PatientDetailsPage.testDataFilePath, PatientDetailsPage.captionInput)
				.verifyToasterMessage(PatientDetailsPage.uploadSuccesToaster)
				.navigateToPatientProfile()
				.verifyAttachmentPresence()
				.verifyRecentVisitEntry()
				.deletePatientAndVerify(PatientDetailsPage.patientDeleteReason, PatientDetailsPage.deletedPatientToaster)
				.searchAndVerifyDeletedPatient(PatientDetailsPage.patientID, PatientDetailsPage.patientRecordResult);
	}
}
