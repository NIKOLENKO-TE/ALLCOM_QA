package allcom_testng.tests.registration;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.registration.RegisterPage;
import allcom_testng.pages.DataProviderClass;
import allcom_testng.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterPageNegativeTests extends TestBase {
    BasePage basePage;
    RegisterPage registerPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        registerPage = new RegisterPage(app.driver);
        basePage.goToPage(RegisterPage.registerPageURL());
        basePage.isCurrentPage(RegisterPage.registerPageURL(),true);
    }

    @Test(dataProvider = "invalidTextData", dataProviderClass = DataProviderClass.class)
    public void validateFirstNameWithIncorrectData(String invalidFirstName) {
        basePage.validateField(registerPage.getFirstNameField(), invalidFirstName, true);
    }

    @Test(dataProvider = "invalidTextData", dataProviderClass = DataProviderClass.class)
    public void validateLastNameWithIncorrectData(String invalidLastName) {
        basePage.validateField(registerPage.getLastNameField(), invalidLastName, true);
    }

    @Test(dataProvider = "invalidPhoneData", dataProviderClass = DataProviderClass.class)
    public void validatePhoneWithIncorrectData(String invalidPhoneNumber) {
        basePage.validateField(registerPage.getPhoneNumberField(), invalidPhoneNumber, true);
    }

    @Test(dataProvider = "invalidEMailData", dataProviderClass = DataProviderClass.class)
    public void validateEmailWithIncorrectData(String invalidEmail) {
        basePage.validateField(registerPage.getEmailField(), invalidEmail, true);
    }

    @Test(dataProvider = "invalidPasswordData", dataProviderClass = DataProviderClass.class)
    public void validatePasswordWithIncorrectData(String invalidPassword) {
        basePage.validateField(registerPage.getPasswordField(), invalidPassword, true);
    }

    @Test(dataProvider = "invalidPasswordData", dataProviderClass = DataProviderClass.class)
    public void validatePasswordConfirmWithIncorrectData(String invalidPasswordConfirm) {
        basePage.validateField(registerPage.getPasswordConfirmField(), invalidPasswordConfirm, true);
    }

    @Test
    public void validateComparePasswordsEqualityNegativeData() {
        registerPage.comparePasswordsEquality("Qwertyuiop@1", "Qwertyuiop@2", false);
    }

    @Test(dataProvider = "invalidCompanyNameTextData", dataProviderClass = DataProviderClass.class)
    public void validateCompanyNameWithIncorrectData(String invalidCompanyName) {
        registerPage.setCheckboxFirma();
        basePage.type(registerPage.getCompanyNameField(), invalidCompanyName);
        basePage.isValidationErrorPresent(true);
    }

    @Test(dataProvider = "invalidPositionData", dataProviderClass = DataProviderClass.class)
    public void validateCompanyPositionWithIncorrectData(String invalidCompanyPosition) {
        registerPage.setCheckboxFirma();
        basePage.type(registerPage.getPositionField(), invalidCompanyPosition);
        basePage.isValidationErrorPresent(true);
    }

    @Test(dataProvider = "invalidTaxData", dataProviderClass = DataProviderClass.class)
    public void validateTaxWithIncorrectData(String invalidTaxNumber) {
        registerPage.setCheckboxFirma();
        basePage.type(registerPage.getTaxNumberField(), invalidTaxNumber);
        basePage.isValidationErrorPresent(true);
    }

    @Test(dataProvider = "invalidPostalData", dataProviderClass = DataProviderClass.class)
    public void validatePostalWithIncorrectData(String invalidPostalNumber) {
        registerPage.setCheckboxFirma();
        basePage.type(registerPage.getPostIndexField(), invalidPostalNumber);
        basePage.isValidationErrorPresent(true);
    }

    @Test(dataProvider = "invalidTextData", dataProviderClass = DataProviderClass.class)
    public void validateCityWithIncorrectData(String invalidCity) {
        registerPage.setCheckboxFirma();
        basePage.type(registerPage.getCityField(), invalidCity);
        basePage.isValidationErrorPresent(true);
    }

    @Test(dataProvider = "invalidStreetData", dataProviderClass = DataProviderClass.class)
    public void validateStreetWithIncorrectData(String invalidStreet) {
        registerPage.setCheckboxFirma();
        basePage.type(registerPage.getStreetField(), invalidStreet);
        basePage.isValidationErrorPresent(true);
    }

    @Test(dataProvider = "invalidHouseNumberData", dataProviderClass = DataProviderClass.class)
    public void validateHouseNumberWithIncorrectData(String invalidHouseNumber) {
        registerPage.setCheckboxFirma();
        basePage.type(registerPage.getHouseNumberField(), invalidHouseNumber);
        basePage.isValidationErrorPresent(true);
    }
}