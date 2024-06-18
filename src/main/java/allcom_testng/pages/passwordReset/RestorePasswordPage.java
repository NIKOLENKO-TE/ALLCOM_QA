package allcom_testng.pages.passwordReset;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.homePage.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static allcom_testng.pages.homePage.HomePage.HOME_PAGE_URL;

public class RestorePasswordPage extends BasePage {

  public static final String RESTORE_PASSWORD_PAGE_URL = HOME_PAGE_URL + "/restore_password";

  public RestorePasswordPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(name = "email")
  WebElement usernameField;

  public void emailToRestore(String email) {
    type(usernameField, email);
  }

  public void clickRestorePasswordButton() {
    clickOnElement(ElementType.DATA_TESTID, "buttonRestore");
  }
}
