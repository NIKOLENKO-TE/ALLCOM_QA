package allcom_selenium.passwordReset;

import allcom_selenium.TestBaseSE;
import allcom_selenium.pages.BasePage;
import allcom_selenium.pages.passwordReset.RestorePasswordWaitPage;
import allcom_selenium.pages.registration.RegisterPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static allcom_selenium.pages.login.LoginPage.LOGIN_PAGE_URL;
import static allcom_selenium.pages.passwordReset.RestorePasswordWaitPage.RESTORE_PASSWORD_WAIT_PAGE_URL;

public class RestorePasswordWaitPageTests extends TestBaseSE {
    BasePage basePage;
    RegisterPage registerPage;
    RestorePasswordWaitPage restorePasswordWaitPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(driver);
        registerPage = new RegisterPage(driver);
        restorePasswordWaitPage = new RestorePasswordWaitPage(driver);
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
