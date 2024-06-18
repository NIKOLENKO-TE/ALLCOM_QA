package allcom_selenium.registration;

import allcom_selenium.TestBaseSE;
import allcom_selenium.pages.BasePage;
import allcom_selenium.pages.DataProviderClass;
import allcom_selenium.pages.DataRandom;
import allcom_selenium.pages.registration.RegisterPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static allcom_selenium.pages.registration.RegisterPage.REGISTER_PAGE_URL;

public class RegisterPagePositiveTests extends TestBaseSE {
    private BasePage basePage;
    private RegisterPage registerPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        registerPage = new RegisterPage(app.driver);
        basePage.goToPage(REGISTER_PAGE_URL);
        basePage.isCurrentPage(REGISTER_PAGE_URL, true);
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

    @Test(dataProvider = "validNewUserRegisterDataFromFile", dataProviderClass = DataProviderClass.class)
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

    @Test(dataProvider = "validNewFirmaRegisterDataFromFile", dataProviderClass = DataProviderClass.class)
    public void registerNewFirmaWithValidDataPositive(
        String firstName, String lastName, String email,
        String phoneNumber, String password, String passwordConfirm,
        String companyName, String position, String taxNumber,
        String postIndex, String city, String street, String houseNumber) {
        basePage.setCheckboxFirma();
        registerPage.registerFirma(firstName, lastName, phoneNumber, email, password, passwordConfirm,
            companyName, position, taxNumber, postIndex, city, street, houseNumber);
        registerPage.clickReadTermsCheckbox();
        registerPage.clickRegisterButton();
        basePage.isValidationErrorPresent(false);
    }

    @Test(invocationCount = 2)
    public void registerNewUserWithValidRandomDataPositive() {
        String validPassword = DataRandom.getValidPassword();
        registerPage.registerClient(
            DataRandom.getValidFirstName(),
            DataRandom.getValidLastName(),
            DataRandom.getValidPhoneNumber(),
            DataRandom.getValidEmail(),
            validPassword,
            validPassword
        );
        registerPage.clickReadTermsCheckbox();
        registerPage.clickRegisterButton();
        basePage.isValidationErrorPresent(false);
    }

    @Test(invocationCount = 2)
    public void registerNewFirmaWithValidRandomDataPositive() {
        String validPassword = DataRandom.getValidPassword();
        registerPage.registerFirma(
            DataRandom.getValidFirstName(),
            DataRandom.getValidLastName(),
            DataRandom.getValidPhoneNumber(),
            DataRandom.getValidEmail(),
            validPassword,
            validPassword, // Confirm password
            DataRandom.getValidCompanyName(),
            DataRandom.getValidPosition(),
            DataRandom.getValidTaxNumber(),
            DataRandom.getValidPostIndex(),
            DataRandom.getValidCity(),
            DataRandom.getValidStreet(),
            DataRandom.getValidHouseNumber()
        );
        registerPage.clickReadTermsCheckbox();
        registerPage.clickRegisterButton();
        basePage.isValidationErrorPresent(false);
    }

    @Test
    public void validateComparePasswordsEqualityPositiveData() {
        registerPage.comparePasswordsEquality("Qwertyuiop@1", "Qwertyuiop@1", true);
    }

}