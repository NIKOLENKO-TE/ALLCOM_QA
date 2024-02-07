package allcom_testng.tests.passwordReset;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.passwordReset.CreateNewPasswordPage;
import allcom_testng.pages.registration.RegisterPage;
import allcom_testng.pages.DataProviderClass;
import allcom_testng.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateNewPasswordPageTests extends TestBase {
    BasePage basePage;
    RegisterPage registerPage;
    CreateNewPasswordPage createNewPasswordPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        createNewPasswordPage = new CreateNewPasswordPage(app.driver);
        registerPage = new RegisterPage(app.driver);
        basePage.goToPage(CreateNewPasswordPage.createNewPasswordPageURL());
        basePage.isCurrentPage(CreateNewPasswordPage.createNewPasswordPageURL(), true);
    }

    @Test
    public void isCreateNewPasswordButtonPresent() {
        basePage.isValidationErrorPresent(false);
    }

    @Test(dataProvider = "invalidPasswordData", dataProviderClass = DataProviderClass.class)
    public void validatePasswordWithIncorrectData(String invalidPassword) {
        basePage.validateField(createNewPasswordPage.getPasswordField(), invalidPassword, true);
    }

    @Test(dataProvider = "invalidPasswordData", dataProviderClass = DataProviderClass.class)
    public void validatePasswordConfirmWithIncorrectData(String invalidPasswordConfirm) {
        basePage.validateField(createNewPasswordPage.getPasswordConfirmField(), invalidPasswordConfirm, true);
    }

    @Test
    public void validateComparePasswordsEqualityPositive() {
        registerPage.comparePasswordsEquality("Qwertyuiop@1", "Qwertyuiop@1", true);
    }

    @Test
    public void validateComparePasswordsEqualityNegative() {
        registerPage.comparePasswordsEquality("Qwertyuiop@1", "Qwertyuiop@2", false);
    }

    @Test
    public void isPasswordResetSuccessValidDataWoValidationCheck() {
        basePage.type(createNewPasswordPage.getPasswordField(), "Qwertyuiop@1");
        basePage.type(createNewPasswordPage.getPasswordConfirmField(), "Qwertyuiop@1");
        createNewPasswordPage.clickSetNewPasswordButton();
        basePage.isElementPresent(BasePage.ElementType.DATA_TESTID, "countdownTimer", true);

    }
    @Test
    public void isPasswordResetNotSuccessInvalidDataWoValidationCheck() {
        basePage.type(createNewPasswordPage.getPasswordField(), "Qwertyuiop@1");
        basePage.type(createNewPasswordPage.getPasswordConfirmField(), "Qwertyuiop@2");
        createNewPasswordPage.clickSetNewPasswordButton();
        basePage.isElementPresent(BasePage.ElementType.DATA_TESTID, "countdownTimer", false);

    }
    @Test
    public void isPasswordResetSuccessValidDataWithValidationCheck() {
        basePage.type(createNewPasswordPage.getPasswordField(), "Qwertyuiop@1");
        basePage.type(createNewPasswordPage.getPasswordConfirmField(), "Qwertyuiop@1");
        basePage.isValidationErrorPresent(false);
        createNewPasswordPage.clickSetNewPasswordButton();
        createNewPasswordPage.isCountdownTimerPresent(true);

    }
    @Test
    public void isPasswordResetNotSuccessInvalidDataWithValidationCheck() {
        basePage.type(createNewPasswordPage.getPasswordField(), "Qwertyuiop@1");
        basePage.type(createNewPasswordPage.getPasswordConfirmField(), "Qwertyuiop@2");
        basePage.isValidationErrorPresent(true);
    }
    @Test
    public void isPasswordResetNotSuccessInvalidDataWoValidationCheckWoTimer() {
        basePage.type(createNewPasswordPage.getPasswordField(), "Qwertyuiop@1");
        basePage.type(createNewPasswordPage.getPasswordConfirmField(), "Qwertyuiop@2");
        basePage.isValidationErrorPresent(true);
        createNewPasswordPage.clickSetNewPasswordButton();
        createNewPasswordPage.isCountdownTimerPresent(false);
    }
}
