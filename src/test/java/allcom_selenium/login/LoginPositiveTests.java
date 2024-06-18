// Test: LoginPositiveTests
package allcom_selenium.login;

import allcom_selenium.TestBaseSE;
import allcom_selenium.pages.BasePage;
import allcom_selenium.pages.DataProviderClass;
import allcom_selenium.pages.DataRandom;
import allcom_selenium.pages.login.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static allcom_selenium.pages.login.LoginPage.LOGIN_PAGE_URL;

public class LoginPositiveTests extends TestBaseSE {
    private final String validLoginData = "validLoginData";
    private BasePage basePage;
    private LoginPage loginPage;
    String randomEmail = DataRandom.getValidEmail();
    String randomPassword = DataRandom.getValidPassword();
    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        basePage.goToPage(LOGIN_PAGE_URL);
        basePage.isCurrentPage(LOGIN_PAGE_URL, true);
        driver.manage().window().maximize();
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