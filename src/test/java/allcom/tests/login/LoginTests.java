package allcom.tests.login;

import allcom.pages.BasePage;
import allcom.pages.login.LoginPage;
import allcom.pages.registration.RegisterPage;
import allcom.pages.passwordReset.RestorePasswordPage;
import allcom.tests.DataProviderClass;
import allcom.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    private final String invalidLoginData = "invalidLoginData";
    private BasePage basePage;
    private LoginPage loginPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        loginPage = new LoginPage(app.driver);
        basePage.goToPage(LoginPage.loginPageURL());
        basePage.isCurrentPage(LoginPage.loginPageURL(), true);
    }

    @Test
    public void loginWithValidData() {
        loginPage.login("admin@gmail.com", "Qwertyuiop@1");
        loginPage.clickLoginButton();
        basePage.isValidationErrorPresent(false);
    }

    @Test
    public void loginWithInvalidEmail() {
        loginPage.login("admin@gmailcom", "Qwertyuiop@1");
        loginPage.clickLoginButton();
        basePage.isValidationErrorPresent(true);
    }

    @Test
    public void loginWithInvalidPassword() {
        loginPage.login("admin@gmail.com", "Qwertyuiop1");
        loginPage.clickLoginButton();
        basePage.isValidationErrorPresent(true);
    }

    @Test(dataProvider = invalidLoginData, dataProviderClass = DataProviderClass.class)
    public void loginWithInvalidData(String invalidEmail, String invalidPassword) {
        loginPage.login(invalidEmail, invalidPassword);
        loginPage.clickLoginButton();
        basePage.isValidationErrorPresent(true);
    }

    @Test
    public void clickOnForgotYourPasswordLabel() {
        loginPage.clickForgotPasswordButton();
        basePage.isCurrentPage(RestorePasswordPage.restorePasswordPageURL(), true);
    }

    @Test
    public void clickOnLoginButtonWOData() {
        loginPage.login("", "");
        loginPage.clickLoginButton();
        basePage.isValidationErrorPresent(true);
    }

    @Test
    public void clickOnRegisterButton() {
        loginPage.clickRegisterButton();
        basePage.isCurrentPage(RegisterPage.registerPageURL(), true);
    }
}