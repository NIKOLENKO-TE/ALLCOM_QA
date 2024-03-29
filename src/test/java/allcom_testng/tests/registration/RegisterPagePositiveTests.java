package allcom_testng.tests.registration;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.DataProviderClass;
import allcom_testng.pages.DataRandom;
import allcom_testng.pages.registration.RegisterPage;
import allcom_testng.tests.TestBase;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterPagePositiveTests extends TestBase {
    private final String validNewUserRegisterDataFromFile = "validNewUserRegisterDataFromFile";
    private final String validNewFirmaRegisterDataFromFile = "validNewFirmaRegisterDataFromFile";
    private BasePage basePage;
    private RegisterPage registerPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        registerPage = new RegisterPage(app.driver);
        basePage.goToPage(RegisterPage.registerPageURL());
        basePage.isCurrentPage(RegisterPage.registerPageURL(), true);
        app.driver.manage().window().maximize();
    }

    @Test
    public void registerClientWithValidDataPositiveTest() {
        registerPage.registerClient(
                "Ivanov",
                "Ivan",
                "160-5945-111",
                "admin@gmail.com",
                "Qwertyuiop@1",
                "Qwertyuiop@1");
        registerPage.clickReadTermsCheckbox();
        registerPage.clickRegisterButton();
        basePage.isValidationErrorPresent(false);
    }

    @Test
    public void registerFirmaWithValidDataPositiveTest() {
        basePage.setCheckboxFirma();
        registerPage.registerFirma(
                "Ivanov",
                "Ivan",
                "160-5945-111",
                "admin@gmail.com",
                "Qwertyuiop@1",
                "Qwertyuiop@1",
                "Firma",
                "Director",
                "1234567890",
                "12459",
                "Berlin",
                "Street",
                "79");
        registerPage.clickReadTermsCheckbox();
        registerPage.clickRegisterButton();
        basePage.isValidationErrorPresent(false);
    }

    @Test(dataProvider = validNewUserRegisterDataFromFile, dataProviderClass = DataProviderClass.class)
    public void registerNewUserWithValidDataPositive(String validFirstName, String validLastName, String validEmail, String validPhoneNumber, String validPassword) {
        basePage.validateField(registerPage.getFirstNameField(), validFirstName, false);
        basePage.validateField(registerPage.getLastNameField(), validLastName, false);
        basePage.validateField(registerPage.getEmailField(), validEmail, false);
        basePage.validateField(registerPage.getPhoneNumberField(), validPhoneNumber, false);
        basePage.validateField(registerPage.getPasswordField(), validPassword, false);
        basePage.validateField(registerPage.getPasswordConfirmField(), validPassword, false);
        registerPage.clickReadTermsCheckbox();
        registerPage.clickRegisterButton();
        basePage.isValidationErrorPresent(false);
    }

    @Test(dataProvider = validNewFirmaRegisterDataFromFile, dataProviderClass = DataProviderClass.class)
    public void registerNewFirmaWithValidDataPositive(
            String validFirstName, String validLastName, String validEmail,
            String validPhoneNumber, String validPassword, String validPasswordConfirm,
            String validCompanyName, String validPosition, String validTaxNumber,
            String validPostIndex, String validCity, String validStreet, String validHouseNumber) {
        basePage.setCheckboxFirma();
        basePage.validateField(registerPage.getFirstNameField(), validFirstName, false);
        basePage.validateField(registerPage.getLastNameField(), validLastName, false);
        basePage.validateField(registerPage.getEmailField(), validEmail, false);
        basePage.validateField(registerPage.getPhoneNumberField(), validPhoneNumber, false);
        basePage.validateField(registerPage.getPasswordField(), validPassword, false);
        basePage.validateField(registerPage.getPasswordConfirmField(), validPasswordConfirm, false);
        basePage.validateField(registerPage.getCompanyNameField(), validCompanyName, false);
        basePage.validateField(registerPage.getPositionField(), validPosition, false);
        basePage.validateField(registerPage.getTaxNumberField(), validTaxNumber, false);
        basePage.validateField(registerPage.getPostIndexField(), validPostIndex, false);
        basePage.validateField(registerPage.getCityField(), validCity, false);
        basePage.validateField(registerPage.getStreetField(), validStreet, false);
        basePage.validateField(registerPage.getHouseNumberField(), validHouseNumber, false);
        registerPage.clickReadTermsCheckbox();
        registerPage.clickRegisterButton();
        basePage.isValidationErrorPresent(false);
    }

    @Test(invocationCount = 30)
    public void registerNewUserWithValidRandomDataPositive() {
        String validPassword = DataRandom.getValidPassword();
        basePage.validateField(registerPage.getFirstNameField(), DataRandom.getValidFirstName(), false);
        basePage.validateField(registerPage.getLastNameField(), DataRandom.getValidLastName(), false);
        basePage.validateField(registerPage.getEmailField(), DataRandom.getValidEmail(), false);
        basePage.validateField(registerPage.getPhoneNumberField(), DataRandom.getValidPhoneNumber(), false);
        basePage.validateField(registerPage.getPasswordField(), validPassword, false);
        basePage.validateField(registerPage.getPasswordConfirmField(), validPassword, false);
        registerPage.clickReadTermsCheckbox();
        registerPage.clickRegisterButton();
        basePage.isValidationErrorPresent(false);
    }

    @Test(invocationCount = 30)
    public void registerNewFirmaWithValidRandomDataPositive() {
        String validPassword = DataRandom.getValidPassword();
        basePage.setCheckboxFirma();
        basePage.validateField(registerPage.getFirstNameField(), DataRandom.getValidFirstName(), false);
        basePage.validateField(registerPage.getLastNameField(), DataRandom.getValidLastName(), false);
        basePage.validateField(registerPage.getEmailField(), DataRandom.getValidEmail(), false);
        basePage.validateField(registerPage.getPhoneNumberField(), DataRandom.getValidPhoneNumber(), false);
        basePage.validateField(registerPage.getPasswordField(), validPassword, false);
        basePage.validateField(registerPage.getPasswordConfirmField(), validPassword, false);
        basePage.validateField(registerPage.getCompanyNameField(), DataRandom.getValidCompanyName(), false);
        basePage.validateField(registerPage.getPositionField(), DataRandom.getValidPosition(), false);
        basePage.validateField(registerPage.getTaxNumberField(), DataRandom.getValidTaxNumber(), false);
        basePage.validateField(registerPage.getPostIndexField(), DataRandom.getValidPostIndex(), false);
        basePage.validateField(registerPage.getCityField(), DataRandom.getValidCity(), false);
        basePage.validateField(registerPage.getStreetField(), DataRandom.getValidStreet(), false);
        basePage.validateField(registerPage.getHouseNumberField(), DataRandom.getValidHouseNumber(), false);
        registerPage.clickReadTermsCheckbox();
        registerPage.clickRegisterButton();
        basePage.isValidationErrorPresent(false);
    }

    @Test
    public void validateComparePasswordsEqualityPositiveData() {
        registerPage.comparePasswordsEquality("Qwertyuiop@1", "Qwertyuiop@1", true);
    }

}