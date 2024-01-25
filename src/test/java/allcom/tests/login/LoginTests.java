package allcom.tests.login;
import allcom.pages.BasePage;
import allcom.pages.login.LoginPage;
import allcom.pages.registration.RegisterPage;
import allcom.pages.passwordReset.RestorePasswordPage;
import allcom.tests.DataProviderClass;
import allcom.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    private final String invalidLoginData = "invalidLoginData";
    private BasePage basePage;
    private LoginPage loginPage;
    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        basePage.goToPage(LoginPage.loginPageURL());
        basePage.isCurrentPage(LoginPage.loginPageURL());
    }
    @Test
    public void loginWithValidData() {
        loginPage.login("admin@gmail.com", "Qwertyuiop@1");
        basePage.clickOnButton("button_login");
        Assert.assertTrue(basePage.errorValidationIsPresent(false));
    }
    @Test
    public void loginWithInvalidEmail() {
        loginPage.login("admin@gmailcom", "Qwertyuiop@1");
        basePage.clickOnButton("button_login");
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
    @Test
    public void loginWithInvalidPassword() {
        loginPage.login("admin@gmail.com", "Qwertyuiop1");
        basePage.clickOnButton("button_login");
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
    @Test(dataProvider = invalidLoginData, dataProviderClass = DataProviderClass.class)
    public void loginWithInvalidData(String invalidEmail, String invalidPassword) {
        loginPage.login(invalidEmail, invalidPassword);
        basePage.clickOnButton("button_login");
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
    @Test
    public void clickOnForgotYourPasswordLabel() {
        basePage.clickOnLabel("forgot_password");
        basePage.isCurrentPage(RestorePasswordPage.restorePasswordPageURL());
    }
    @Test
    public void clickOnLoginButtonWOData() {
        basePage.clickOnButton("button_login");
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
    @Test
    public void clickOnRegisterButton() {
        basePage.clickOnButton("button_register");
        basePage.isCurrentPage(RegisterPage.registerPageURL());
    }
}