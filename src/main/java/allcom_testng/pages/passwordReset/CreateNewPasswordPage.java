package allcom_testng.pages.passwordReset;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.homePage.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPasswordPage extends BasePage {
    @FindBy(name = "password")
    WebElement passwordField;
    @FindBy(name = "passwordConfirm")
    WebElement passwordConfirmField;

    public CreateNewPasswordPage(WebDriver driver) {
        super(driver);
    }

    public static String createNewPasswordPageURL() {
        return HomePage.homePageURL() + "/restore_password_new";
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getPasswordConfirmField() {
        return passwordConfirmField;
    }
    public void clickSetNewPasswordButton() {
        clickOnElement(BasePage.ElementType.DATA_TESTID, "buttonRestore");
    }
    public void isCountdownTimerPresent(boolean expectedStatus) {
        isElementPresent(ElementType.DATA_TESTID, "countdownTimer", expectedStatus);
    }
}
