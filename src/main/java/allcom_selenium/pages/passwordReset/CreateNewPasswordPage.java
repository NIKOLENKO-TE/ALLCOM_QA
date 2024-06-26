package allcom_selenium.pages.passwordReset;

import allcom_selenium.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static allcom_selenium.pages.homePage.HomePage.HOME_PAGE_URL;

public class CreateNewPasswordPage extends BasePage {
  @FindBy(name = "password")
  WebElement passwordField;
  @FindBy(name = "passwordConfirm")
  WebElement passwordConfirmField;

  public CreateNewPasswordPage(WebDriver driver) {
    super(driver);
  }

  public static final String CREATE_NEW_PASSWORD_PAGE_URL = HOME_PAGE_URL + "/restore_password_new";

  public WebElement getPasswordField() {
    return passwordField;
  }

  public WebElement getPasswordConfirmField() {
    return passwordConfirmField;
  }

  public void clickSetNewPasswordButton() {
    clickOnElement(ElementType.DATA_TESTID, "buttonRestore");
  }

  public void isCountdownTimerPresent(boolean expectedStatus) {
    isElementPresent(ElementType.DATA_TESTID, "countdownTimer", expectedStatus);
  }
}
