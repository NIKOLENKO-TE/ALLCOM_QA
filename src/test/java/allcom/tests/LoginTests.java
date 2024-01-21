package allcom.tests;

import allcom.pages.BasePage;
import allcom.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        BasePage basePage = new BasePage(driver);
        basePage.changeLanguage("English");
        basePage.goToPage(LoginPage.loginPageURL());
        basePage.isCurrentPage(LoginPage.loginPageURL());
    }
    @Test
    public void loginWithValidData() {
        new LoginPage(driver).login("admin@gmail.com", "Qwertyuiop@1");
        new LoginPage(driver).clickOnLoginButton();
        System.out.println("Login with valid data PRESSED");
    }
}