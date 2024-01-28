package allcom.tests.passwordReset;

import allcom.pages.BasePage;
import allcom.pages.login.LoginPage;
import allcom.pages.passwordReset.RestorePasswordWaitPage;
import allcom.pages.registration.RegisterPage;
import allcom.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RestorePasswordWaitPageTests extends TestBase {
    BasePage basePage;
    RegisterPage registerPage;
    RestorePasswordWaitPage restorePasswordWaitPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        registerPage = new RegisterPage(app.driver);
        restorePasswordWaitPage = new RestorePasswordWaitPage(app.driver);
        basePage.goToPage(RestorePasswordWaitPage.restorePasswordWaitPageURL());
        basePage.isCurrentPage(RestorePasswordWaitPage.restorePasswordWaitPageURL(), true);
    }
   @Test
    public void isLoginButtonPresent() {
       basePage.isElementPresent(BasePage.ElementType.DATA_TESTID,"button_login",true);
       registerPage.clickLoginButton();
       basePage.isCurrentPage(LoginPage.loginPageURL(),true);
    }
}
