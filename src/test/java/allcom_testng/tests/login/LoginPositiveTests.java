package allcom_testng.tests.login;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.DataProviderClass;
import allcom_testng.pages.DataRandom;
import allcom_testng.pages.login.LoginPage;
import allcom_testng.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPositiveTests extends TestBase {
    private final String validLoginData = "validLoginData";
    private BasePage basePage;
    private LoginPage loginPage;
    String randomEmail = DataRandom.getValidEmail();
    String randomPassword = DataRandom.getValidPassword();
    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        loginPage = new LoginPage(app.driver);
        basePage.goToPage(LoginPage.loginPageURL());
        basePage.isCurrentPage(LoginPage.loginPageURL(), true);
        app.driver.manage().window().maximize();
    }

    @Test
    public void loginWithValidDataPositive() {
        loginPage.login("admin@gmail.com", "Qwertyuiop@1");
        loginPage.clickLoginButton();
        basePage.isValidationErrorPresent(false);
    }

    @Test(dataProvider = validLoginData, dataProviderClass = DataProviderClass.class)
    public void loginWithValidDataProviderPositive(String validEmail, String validPassword) {
        loginPage.login(validEmail, validPassword);
        loginPage.clickLoginButton();
        basePage.isValidationErrorPresent(false);
    }
    @Test(invocationCount = 30)
    public void loginUserWithValidRandomDataPositive() {
        loginPage.login(randomEmail, randomPassword);
        loginPage.clickLoginButton();
        basePage.isValidationErrorPresent(false);
    }
}