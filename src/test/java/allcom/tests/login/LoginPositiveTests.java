package allcom.tests.login;

import allcom.pages.BasePage;
import allcom.pages.DataProviderClass;
import allcom.pages.DataRandom;
import allcom.pages.login.LoginPage;
import allcom.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPositiveTests extends TestBase {
    private final String validLoginData = "validLoginData";
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
        String email = DataRandom.getValidEmail();
        String password = DataRandom.getValidPassword();
        loginPage.login(email, password);
        loginPage.clickLoginButton();
        basePage.isValidationErrorPresent(false);
    }
}