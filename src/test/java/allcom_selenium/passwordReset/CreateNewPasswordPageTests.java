package allcom_selenium.passwordReset;

import allcom_selenium.TestBaseSE;
import allcom_selenium.pages.BasePage;
import allcom_selenium.pages.DataProviderClass;
import allcom_selenium.pages.passwordReset.CreateNewPasswordPage;
import allcom_selenium.pages.registration.RegisterPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static allcom_selenium.pages.passwordReset.CreateNewPasswordPage.CREATE_NEW_PASSWORD_PAGE_URL;

public class CreateNewPasswordPageTests extends TestBaseSE {
    BasePage basePage;
    RegisterPage registerPage;
    CreateNewPasswordPage createNewPasswordPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(driver);
        createNewPasswordPage = new CreateNewPasswordPage(driver);
        registerPage = new RegisterPage(driver);
        basePage.goToPage(CREATE_NEW_PASSWORD_PAGE_URL);
        basePage.isCurrentPage(CREATE_NEW_PASSWORD_PAGE_URL, true);
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
