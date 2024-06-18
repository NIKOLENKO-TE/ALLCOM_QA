package allcom_testng.pages.passwordReset;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.homePage.HomePage;
import org.openqa.selenium.WebDriver;

import static allcom_testng.pages.homePage.HomePage.HOME_PAGE_URL;

public class RestorePasswordWaitPage extends BasePage {
  public static final String RESTORE_PASSWORD_WAIT_PAGE_URL = HOME_PAGE_URL + "/restore_password_wait";

  public RestorePasswordWaitPage(WebDriver driver) {
    super(driver);
  }

}
