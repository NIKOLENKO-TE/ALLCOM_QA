package allcom.tests.registration;

import allcom.pages.BasePage;
import allcom.pages.DataProviderClass;
import allcom.pages.DataRandom;
import allcom.pages.registration.RegisterPage;
import allcom.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterPagePositiveTests extends TestBase {

    private final String validNewUserRegisterDataFromFile = "validNewUserRegisterDataFromFile";
    private final String validNewFirmaRegisterDataFromFile = "validNewFirmaRegisterDataFromFile";
    BasePage basePage;
    RegisterPage registerPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        registerPage = new RegisterPage(app.driver);
        basePage.goToPage(RegisterPage.registerPageURL());
        basePage.isCurrentPage(RegisterPage.registerPageURL(), true);
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
        String validFirstName = DataRandom.getValidFirstName();
        String validLastName = DataRandom.getValidLastName();
        String validEmail = DataRandom.getValidEmail();
        String validPhoneNumber = DataRandom.getValidPhoneNumber();
        String validPassword = DataRandom.getValidPassword();
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

    @Test(invocationCount = 30)
    public void registerNewFirmaWithValidRandomDataPositive() {
        basePage.setCheckboxFirma();
        String validFirstName = DataRandom.getValidFirstName();
        String validLastName = DataRandom.getValidLastName();
        String validEmail = DataRandom.getValidEmail();
        String validPhoneNumber = DataRandom.getValidPhoneNumber();
        String validPassword = DataRandom.getValidPassword();
        String validCompanyName = DataRandom.getValidCompanyName();
        String validPosition = DataRandom.getValidPosition();
        String validTaxNumber = DataRandom.getValidTaxNumber();
        String validPostIndex = DataRandom.getValidPostIndex();
        String validCity = DataRandom.getValidCity();
        String validStreet = DataRandom.getValidStreet();
        String validHouseNumber = DataRandom.getValidHouseNumber();
        basePage.validateField(registerPage.getFirstNameField(), validFirstName, false);
        basePage.validateField(registerPage.getLastNameField(), validLastName, false);
        basePage.validateField(registerPage.getEmailField(), validEmail, false);
        basePage.validateField(registerPage.getPhoneNumberField(), validPhoneNumber, false);
        basePage.validateField(registerPage.getPasswordField(), validPassword, false);
        basePage.validateField(registerPage.getPasswordConfirmField(), validPassword, false);
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

    @Test
    public void validateComparePasswordsEqualityPositiveData() {
        registerPage.comparePasswordsEquality("Qwertyuiop@1", "Qwertyuiop@1", true);
    }

}
