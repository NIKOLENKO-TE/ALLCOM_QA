package allcom.tests.login;

import allcom.pages.BasePage;
import allcom.pages.DataProviderClass;
import allcom.pages.login.LoginPage;
import allcom.pages.passwordReset.RestorePasswordPage;
import allcom.pages.registration.RegisterPage;
import allcom.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginNegativeTests extends TestBase {
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

    @Test(dataProvider = invalidLoginData, dataProviderClass = DataProviderClass.class)
    public void loginWithInvalidData(String invalidEmail, String invalidPassword) {
        loginPage.login(invalidEmail, invalidPassword);
        loginPage.clickLoginButton();
        basePage.isValidationErrorPresent(true);
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
    public void clickOnRegisterButtonWOData() {
        loginPage.clickRegisterButton();
        basePage.isCurrentPage(RegisterPage.registerPageURL(), true);
    }
}