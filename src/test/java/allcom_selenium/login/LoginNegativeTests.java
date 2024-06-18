package allcom_selenium.login;

import allcom_selenium.TestBaseSE;
import allcom_selenium.pages.BasePage;
import allcom_selenium.pages.DataProviderClass;
import allcom_selenium.pages.login.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static allcom_selenium.pages.login.LoginPage.LOGIN_PAGE_URL;
import static allcom_selenium.pages.passwordReset.RestorePasswordPage.RESTORE_PASSWORD_PAGE_URL;
import static allcom_selenium.pages.registration.RegisterPage.REGISTER_PAGE_URL;

public class LoginNegativeTests extends TestBaseSE {
    private final String invalidLoginData = "invalidLoginData";
    private BasePage basePage;
    private LoginPage loginPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        loginPage = new LoginPage(app.driver);
        basePage.goToPage(LOGIN_PAGE_URL);
        basePage.isCurrentPage(LOGIN_PAGE_URL, true);
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
        basePage.isCurrentPage(RESTORE_PASSWORD_PAGE_URL, true);
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
        basePage.isCurrentPage(REGISTER_PAGE_URL, true);
    }
}