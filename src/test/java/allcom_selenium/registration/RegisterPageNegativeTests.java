package allcom_selenium.registration;

import allcom_selenium.TestBaseSE;
import allcom_selenium.pages.BasePage;
import allcom_selenium.pages.DataProviderClass;
import allcom_selenium.pages.registration.RegisterPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static allcom_selenium.pages.registration.RegisterPage.REGISTER_PAGE_URL;

public class RegisterPageNegativeTests extends TestBaseSE {
    BasePage basePage;
    RegisterPage registerPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(driver);
        registerPage = new RegisterPage(driver);
        basePage.goToPage(REGISTER_PAGE_URL);
        basePage.isCurrentPage(REGISTER_PAGE_URL,true);
        driver.manage().window().maximize();
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
