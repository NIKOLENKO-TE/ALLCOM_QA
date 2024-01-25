package allcom.tests.passwordReset;

import allcom.pages.BasePage;
import allcom.pages.login.LoginPage;
import allcom.pages.passwordReset.RestorePasswordWaitPage;
import allcom.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RestorePasswordWaitPageTests extends TestBase {
    private BasePage basePage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(driver);
        new RestorePasswordWaitPage(driver);
        basePage.goToPage(RestorePasswordWaitPage.restorePasswordWaitPageURL());
        basePage.isCurrentPage(RestorePasswordWaitPage.restorePasswordWaitPageURL());
    }
   @Test
    public void isLoginButtonPresent() {
       Assert.assertTrue(basePage.isButtonPresent("button_login"));
       basePage.clickOnButton("button_login");
       basePage.isCurrentPage(LoginPage.loginPageURL());
    }
}
