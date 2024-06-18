//LoginPage.java
package allcom_selenium.pages.login;

import allcom_selenium.pages.BasePage;
import allcom_selenium.pages.UserCredentials;
import allcom_selenium.pages.userProfile.UserProfilePage;
import allcom_selenium.pages.header.HeaderAccount;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;

import static allcom_selenium.pages.BasePage.ElementType.DATA_TESTID;
import static allcom_selenium.pages.homePage.HomePage.HOME_PAGE_URL;
@Getter
public class LoginPage extends BasePage {

  HeaderAccount headerAccount = new HeaderAccount(driver);
  UserProfilePage userProfilePage = new UserProfilePage(driver);

//  BasePage basePage = new BasePage(driver);

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public static final String LOGIN_PAGE_URL = HOME_PAGE_URL + "/login";
  public static final String MY_ACCOUNT_PAGE_URL = HOME_PAGE_URL + "/user/my_account/about_me";


  private static final Duration WAIT_SECONDS = Duration.ofSeconds(1);
  private static final Duration WAIT_MILLIS = Duration.ofMillis(200);
  public static final UserCredentials CLIENT_UNCHECKED = new UserCredentials("nikolenkote_800@gmail.com", "Qwertyuiop@1");
  public static final UserCredentials CLIENT_CHECKED = new UserCredentials("nikolenkote_900@gmail.com", "Qwertyuiop@1");
  public static final UserCredentials CLIENT_BLOCKED = new UserCredentials("nikolenkote_700@gmail.com", "Qwertyuiop@1");
  public static final UserCredentials CLIENT_NON_EXISTING = new UserCredentials("nikolenkote_600@gmail.com", "Qwertyuiop@1");
  public static final UserCredentials ADMIN = new UserCredentials("james-smith@mail.com", "Qwerty007!");
  @FindBy(name = "email")
  public WebElement USER_EMAIL;
  @FindBy(name = "password")
  public WebElement USER_PASSWORD;
  @FindBy(xpath = "//span[@class='header__account--btn__text']")
  public WebElement signInButtonText;

  public void login(String email, String password) {
    type(USER_EMAIL, email);
    type(USER_PASSWORD, password);
  }

  public void loginAdmin() {
    waitForSpinnerStop();
    try {
      waitUntilElementToBeClickable(USER_EMAIL);
      type(USER_EMAIL, ADMIN.getUsername());
      waitUntilElementToBeClickable(USER_PASSWORD);
      type(USER_PASSWORD, ADMIN.getPassword());
      clickLoginButton();
      isElementClickable(signInButtonText, true);
      String buttonText = signInButtonText.getText();
      if (buttonText.equals("Sign in")) {
        throw new AssertionError("Button text did not change after login");
      }
    } catch (TimeoutException | NoSuchElementException e) {
      System.out.println("Login element not found. Skipping login.");
    }
  }

  public void clickOnLoginButton() {
    clickOnElement(DATA_TESTID, "button_login");
  }

  public void clickLoginButton() {
    clickOnElement(DATA_TESTID, "button_login");
  }

  public void clickRegisterButton() {
    clickOnElement(DATA_TESTID, "button_register");
  }

  public void clickForgotPasswordButton() {
    clickOnElement(DATA_TESTID, "ButtonForgotPassword");
  }

  @FindBy(css = "p.login_register--header")
  WebElement loginHeader;

  public WebElement isLoginHeaderPresent() {
    return loginHeader;
  }

  public void isUserLoggedIn(boolean expectedLoggedInState) {
    waitForSpinnerStop();
    Instant start = Instant.now();
    WebElement myAccountButton = driver.findElement(By.xpath("(//span[@class='header__account--btn__text'])[1]"));
    String buttonText = myAccountButton.getText().trim();
    boolean actualLoggedInState = !buttonText.equals("Sign In");
    if (actualLoggedInState != expectedLoggedInState) {
      long timeTakenMillis = Duration.between(start, Instant.now()).toMillis();
      throw new AssertionError("\nLogin state did not change to expected state:" + "\nActual state: [" + actualLoggedInState + "]\nExpected state: [" + expectedLoggedInState + "]" + "\nTime taken: [" + timeTakenMillis + "] milliseconds");
    }
    System.out.println("User logged status: [" + actualLoggedInState + "]");
  }

  public void userLoggedOut() {
    WebDriverWait wait = new WebDriverWait(driver, WAIT_SECONDS);
    String buttonText = headerAccount.myAccountTop().getText().trim();
    boolean userLoggedIn = !buttonText.equals("Sign In");
    if (!userLoggedIn) {
      return;
    }
    driver.get(MY_ACCOUNT_PAGE_URL);
    click(userProfilePage.logOut());
    try {
      wait.until(ExpectedConditions.elementToBeClickable(headerAccount.myAccountTopBeforeLoggedIn()));
    } catch (TimeoutException e) {
      System.err.println("Failed to log out, element not found or not disappearing in :[" + WAIT_SECONDS.getSeconds() + "] seconds");
    }
  }
}
