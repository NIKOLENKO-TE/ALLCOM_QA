package allcom_testng.passwordReset;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.passwordReset.RestorePasswordPage;
import allcom_testng.pages.DataProviderClass;
import allcom_testng.TestBaseSE;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RestorePasswordTests extends TestBaseSE {
    private BasePage basePage;
    private RestorePasswordPage restorePasswordPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        restorePasswordPage = new RestorePasswordPage(app.driver);
        basePage.goToPage(RestorePasswordPage.restorePasswordPageURL());
        basePage.isCurrentPage(RestorePasswordPage.restorePasswordPageURL(),true);
    }

    @Test
    public void restoreWithValidEmail() {
        restorePasswordPage.emailToRestore("admin@gmail.com");
        restorePasswordPage.clickRestorePasswordButton();
        basePage.isCurrentPage(RestorePasswordPage.restorePasswordPageURL(),true);
    }

    @Test(dataProvider = "invalidEMailData", dataProviderClass = DataProviderClass.class)
    public void restoreWithInvalidEmail(String email) {
        restorePasswordPage.emailToRestore(email);
        restorePasswordPage.clickRestorePasswordButton();
        basePage.isValidationErrorPresent(true);
    }
}
