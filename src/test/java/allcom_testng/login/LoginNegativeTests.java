package allcom_testng.login;

import allcom_testng.TestBaseSE;
import allcom_testng.pages.BasePage;
import allcom_testng.pages.DataProviderClass;
import allcom_testng.pages.login.LoginPage;
import allcom_testng.pages.passwordReset.RestorePasswordPage;
import allcom_testng.pages.registration.RegisterPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginNegativeTests extends TestBaseSE {
    private final String invalidLoginData = "invalidLoginData";
    private BasePage basePage;
    private LoginPage loginPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        loginPage = new LoginPage(app.driver);
        basePage.goToPage(LoginPage.loginPageURL());
        basePage.isCurrentPage(LoginPage.loginPageURL(), true);
        app.driver.manage().window().maximize();
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