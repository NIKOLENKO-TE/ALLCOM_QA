// Test: LoginPositiveTests
package allcom_testng.login;

import allcom_testng.TestBaseSE;
import allcom_testng.pages.BasePage;
import allcom_testng.pages.DataProviderClass;
import allcom_testng.pages.DataRandom;
import allcom_testng.pages.login.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static allcom_testng.pages.login.LoginPage.LOGIN_PAGE_URL;

public class LoginPositiveTests extends TestBaseSE {
    private final String validLoginData = "validLoginData";
    private BasePage basePage;
    private LoginPage loginPage;
    String randomEmail = DataRandom.getValidEmail();
    String randomPassword = DataRandom.getValidPassword();
    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        loginPage = new LoginPage(app.driver);
        basePage.goToPage(LOGIN_PAGE_URL);
        basePage.isCurrentPage(LOGIN_PAGE_URL, true);
        app.driver.manage().window().maximize();
    }

    @Test
    public void loginWithValidDataPositive() {
        loginPage.login("james-smith@mail.com", "Qwerty007!");
        loginPage.clickLoginButton();
        basePage.isValidationErrorPresent(false);
    }

    @Test(dataProvider = validLoginData, dataProviderClass = DataProviderClass.class)
    public void loginWithValidDataProviderPositive(String validEmail, String validPassword) {
        loginPage.login(validEmail, validPassword);
        loginPage.clickLoginButton();
        basePage.isValidationErrorPresent(false);
    }
    @Test(invocationCount = 5)
    public void loginUserWithValidRandomDataPositive() {
        loginPage.login(randomEmail, randomPassword);
        loginPage.clickLoginButton();
        basePage.isValidationErrorPresent(false);
    }
}