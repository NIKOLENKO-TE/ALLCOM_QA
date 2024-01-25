package allcom.tests.registration;

import allcom.pages.BasePage;
import allcom.pages.registration.RegisterPage;
import allcom.tests.DataProviderClass;
import allcom.tests.TestBase;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterPageTests extends TestBase {
    private BasePage basePage;
    private RegisterPage registerPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(driver);
        registerPage = new RegisterPage(driver);
        basePage.goToPage(RegisterPage.registerPageURL());
        basePage.isCurrentPage(RegisterPage.registerPageURL());
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
        basePage.setCheckbox("checkbox_read_terms");
        basePage.clickOnButton("button_register");
        Assert.assertTrue(basePage.errorValidationIsPresent(false));
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
        basePage.setCheckbox("checkbox_read_terms");
        basePage.clickOnButton("button_register");
        Assert.assertTrue(basePage.errorValidationIsPresent(false));
    }

    public void validateFieldWithIncorrectData(WebElement field, String invalidData) {
        registerPage.type(field, invalidData);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }

    @Test(dataProvider = "invalidTextData", dataProviderClass = DataProviderClass.class)
    public void validateFirstNameWithIncorrectData(String invalidFirstName) {
        validateFieldWithIncorrectData(registerPage.getFirstNameField(), invalidFirstName);
    }

    @Test(dataProvider = "invalidTextData", dataProviderClass = DataProviderClass.class)
    public void validateLastNameWithIncorrectData(String invalidLastName) {
        validateFieldWithIncorrectData(registerPage.getLastNameField(), invalidLastName);
    }

    @Test(dataProvider = "invalidPhoneData", dataProviderClass = DataProviderClass.class)
    public void validatePhoneWithIncorrectData(String invalidPhoneNumber) {
        validateFieldWithIncorrectData(registerPage.getPhoneNumberField(), invalidPhoneNumber);
    }

    @Test(dataProvider = "invalidEMailData", dataProviderClass = DataProviderClass.class)
    public void validateEmailWithIncorrectData(String invalidEmail) {
        validateFieldWithIncorrectData(registerPage.getEmailField(), invalidEmail);
    }

    @Test(dataProvider = "invalidPasswordData", dataProviderClass = DataProviderClass.class)
    public void validatePasswordWithIncorrectData(String invalidPassword) {
        validateFieldWithIncorrectData(registerPage.getPasswordField(), invalidPassword);
    }

    @Test(dataProvider = "invalidPasswordData", dataProviderClass = DataProviderClass.class)
    public void validatePasswordConfirmWithIncorrectData(String invalidPasswordConfirm) {
        validateFieldWithIncorrectData(registerPage.getPasswordConfirmField(), invalidPasswordConfirm);
    }

    public void validateComparePasswordsEquality(String password, String passwordConfirm, boolean expectedResult) {
        registerPage.type(registerPage.getPasswordField(), password);
        registerPage.type(registerPage.getPasswordConfirmField(), passwordConfirm);
        Assert.assertTrue(basePage.errorValidationIsPresent(expectedResult));
    }

    @Test
    public void validateComparePasswordsEqualityPositive() {
        validateComparePasswordsEquality("Qwertyuiop@1", "Qwertyuiop@1", false);
    }

    @Test
    public void validateComparePasswordsEqualityNegative() {
        validateComparePasswordsEquality("Qwertyuiop@1", "Qwertyuiop@2", true);
    }

    @Test(dataProvider = "invalidCompanyNameTextData", dataProviderClass = DataProviderClass.class)
    public void validateCompanyNameWithIncorrectData(String invalidCompanyName) {
        basePage.setCheckboxFirma();
        registerPage.type(registerPage.getCompanyNameField(), invalidCompanyName);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }

    @Test(dataProvider = "invalidPositionData", dataProviderClass = DataProviderClass.class)
    public void validateCompanyPositionWithIncorrectData(String invalidCompanyPosition) {
        basePage.setCheckboxFirma();
        registerPage.type(registerPage.getPositionField(), invalidCompanyPosition);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }

    @Test(dataProvider = "invalidTaxData", dataProviderClass = DataProviderClass.class)
    public void validateTaxWithIncorrectData(String invalidTaxNumber) {
        basePage.setCheckboxFirma();
        registerPage.type(registerPage.getTaxNumberField(), invalidTaxNumber);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }

    @Test(dataProvider = "invalidPostalData", dataProviderClass = DataProviderClass.class)
    public void validatePostalWithIncorrectData(String invalidPostalNumber) {
        basePage.setCheckboxFirma();
        registerPage.type(registerPage.getPostIndexField(), invalidPostalNumber);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }

    @Test(dataProvider = "invalidTextData", dataProviderClass = DataProviderClass.class)
    public void validateCityWithIncorrectData(String invalidCity) {
        basePage.setCheckboxFirma();
        registerPage.type(registerPage.getCityField(), invalidCity);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }

    @Test(dataProvider = "invalidStreetData", dataProviderClass = DataProviderClass.class)
    public void validateStreetWithIncorrectData(String invalidStreet) {
        basePage.setCheckboxFirma();
        registerPage.type(registerPage.getStreetField(), invalidStreet);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }

    @Test(dataProvider = "invalidHouseNumberData", dataProviderClass = DataProviderClass.class)
    public void validateHouseNumberWithIncorrectData(String invalidHouseNumber) {
        basePage.setCheckboxFirma();
        registerPage.type(registerPage.getHouseNumberField(), invalidHouseNumber);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
}
