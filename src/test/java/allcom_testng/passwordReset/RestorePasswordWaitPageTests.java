package allcom_testng.passwordReset;

import allcom_testng.TestBaseSE;
import allcom_testng.pages.BasePage;
import allcom_testng.pages.login.LoginPage;
import allcom_testng.pages.passwordReset.RestorePasswordWaitPage;
import allcom_testng.pages.registration.RegisterPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static allcom_testng.pages.login.LoginPage.LOGIN_PAGE_URL;
import static allcom_testng.pages.passwordReset.RestorePasswordWaitPage.RESTORE_PASSWORD_WAIT_PAGE_URL;

public class RestorePasswordWaitPageTests extends TestBaseSE {
    BasePage basePage;
    RegisterPage registerPage;
    RestorePasswordWaitPage restorePasswordWaitPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        registerPage = new RegisterPage(app.driver);
        restorePasswordWaitPage = new RestorePasswordWaitPage(app.driver);
        basePage.goToPage(RESTORE_PASSWORD_WAIT_PAGE_URL);
        basePage.isCurrentPage(RESTORE_PASSWORD_WAIT_PAGE_URL, true);
    }
   @Test
    public void isLoginButtonPresent() {
       basePage.isElementPresent(BasePage.ElementType.DATA_TESTID,"button_login",true);
       registerPage.clickLoginButton();
       basePage.isCurrentPage(LOGIN_PAGE_URL,true);
    }
}
