package testrunner;

import org.testng.annotations.Test;

import base.Base;
import pageobjects.DashboardPage;
import pageobjects.LoginPage;
import pageobjects.PatientDetailsPage;
import pageobjects.RegisterPatientPage;

public class TestCaseRunner extends Base {

	@Test(description = "")
	public void test01() {

		new LoginPage().enterUsername(LoginPage.usernameInput).enterPassword(LoginPage.passwordInput)
				.selectLocation(LoginPage.locationToSelect).clickLogin();

		new DashboardPage().verfiyDashboard(DashboardPage.title).clickRegisterPatientMenu();

		new RegisterPatientPage()
				.enterPatientName(RegisterPatientPage.nameInput, RegisterPatientPage.familNameInput)
				.clickNextButton().selectGender(RegisterPatientPage.genderInput).clickNextButton()
				.selectBirthDate(RegisterPatientPage.birthDateInput, RegisterPatientPage.monthInput,
						RegisterPatientPage.birthYearInput)
				.clickNextButton()
				.enterAddressDetails(RegisterPatientPage.address1Input, RegisterPatientPage.address2Input,
						RegisterPatientPage.cityInput, RegisterPatientPage.stateInput, RegisterPatientPage.countryInput,
						RegisterPatientPage.postalcodeInput)
				.clickNextButton().
				phoneNumber(RegisterPatientPage.phoneNumberInput).
				clickNextButton().
				clickNextButton()
				.detailsVerification(RegisterPatientPage.nameInput, RegisterPatientPage.familNameInput,
						RegisterPatientPage.genderInput, RegisterPatientPage.birthDateInput,
						RegisterPatientPage.address1Input, RegisterPatientPage.address2Input,
						RegisterPatientPage.cityInput, RegisterPatientPage.stateInput, RegisterPatientPage.countryInput,
						RegisterPatientPage.postalcodeInput, RegisterPatientPage.phoneNumberInput)
				.clickConfirmButton(RegisterPatientPage.patientDetailPageTitle);
		
		new PatientDetailsPage().
		startAndConfirmVist().
		uploadFile(PatientDetailsPage.testDataFilePath, PatientDetailsPage.captionInput)
		.verfiyToaster(PatientDetailsPage.uploadSuccesToaster)
		.clickPatientProfile().atttachmentPresence().recentVistEntrie().
		deletePatient(PatientDetailsPage.patientDeleteReason, PatientDetailsPage.deletedPatientToaster).
		findPatientRecord(PatientDetailsPage.patientID, PatientDetailsPage.patientRecordResult);
	}
}
