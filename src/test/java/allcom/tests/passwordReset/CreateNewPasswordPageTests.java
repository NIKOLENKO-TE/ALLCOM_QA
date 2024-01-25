package allcom.tests.passwordReset;

import allcom.pages.BasePage;
import allcom.pages.passwordReset.CreateNewPasswordPage;
import allcom.tests.DataProviderClass;
import allcom.tests.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateNewPasswordPageTests extends TestBase {
    private BasePage basePage;
    private CreateNewPasswordPage createNewPasswordPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(driver);
        createNewPasswordPage = new CreateNewPasswordPage(driver);
        basePage.goToPage(CreateNewPasswordPage.createNewPasswordPageURL());
        basePage.isCurrentPage(CreateNewPasswordPage.createNewPasswordPageURL());
    }

    public void validateFieldWithIncorrectData(WebElement field, String invalidData) {
        createNewPasswordPage.type(field, invalidData);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }

    @Test
    public void isCreateNewPasswordButtonPresent() {
        Assert.assertTrue(basePage.isButtonPresent("button_restore"));
    }

    @Test(dataProvider = "invalidPasswordData", dataProviderClass = DataProviderClass.class)
    public void validatePasswordWithIncorrectData(String invalidPassword) {
        validateFieldWithIncorrectData(createNewPasswordPage.getPasswordField(), invalidPassword);
    }

    @Test(dataProvider = "invalidPasswordData", dataProviderClass = DataProviderClass.class)
    public void validatePasswordConfirmWithIncorrectData(String invalidPasswordConfirm) {
        validateFieldWithIncorrectData(createNewPasswordPage.getPasswordConfirmField(), invalidPasswordConfirm);
    }

    public void validateComparePasswordsEquality(String password, String passwordConfirm, boolean expectedResult) {
        createNewPasswordPage.type(createNewPasswordPage.getPasswordField(), password);
        createNewPasswordPage.type(createNewPasswordPage.getPasswordConfirmField(), passwordConfirm);
        Assert.assertTrue(basePage.errorValidationIsPresent(expectedResult));
    }

    @Test
    public void validateComparePasswordsEqualityPositive() {
        validateComparePasswordsEquality("Qwertyuiop@1", "Qwertyuiop@1", false);
    }

    @Test
    public void validateComparePasswordsEqualityNegative() {
        validateComparePasswordsEquality("Qwertyuiop@1", "Qwertyuiop@2", true);
    }

    @Test
    public void isPasswordResetSuccess() {
        String password = "Qwertyuiop@1";
        createNewPasswordPage.type(createNewPasswordPage.getPasswordField(), password);
        createNewPasswordPage.type(createNewPasswordPage.getPasswordConfirmField(), password);
        basePage.clickOnButton("button_restore");
        Assert.assertTrue(basePage.isElementPresent(By.cssSelector("[data-testid='countdownTimer']")));
    }
}
