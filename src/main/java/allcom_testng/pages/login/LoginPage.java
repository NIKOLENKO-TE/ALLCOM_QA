//LoginPage.java
package allcom_testng.pages.login;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.HomePage;
import allcom_testng.pages.UserCredentials;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static allcom_testng.pages.BasePage.ElementType.CSS;
import static allcom_testng.pages.BasePage.ElementType.DATA_TESTID;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public static String loginPageURL() {
        return HomePage.homePageURL() + "/login";
    }
    public static String myAccountPageURL() {
        return HomePage.homePageURL() + "/user/my_account/about_me";
    }
    private static final Duration WAIT_SECONDS = Duration.ofSeconds(10);
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
        WebDriverWait wait = new WebDriverWait(driver, WAIT_SECONDS);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/login']")));
            wait.until(ExpectedConditions.visibilityOf(USER_EMAIL));
            wait.until(ExpectedConditions.visibilityOf(USER_PASSWORD));
            type(USER_EMAIL, ADMIN.getUsername());
            type(USER_PASSWORD, ADMIN.getPassword());
            clickLoginButton();
            wait.until(ExpectedConditions.urlToBe(myAccountPageURL()));
        } catch (TimeoutException e) {
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

    public boolean isLoginHeaderPresent() {
        return loginHeader.isDisplayed();
    }

    public void isUserLoggedIn(boolean expectedLoggedInState) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(WAIT_SECONDS)
                .pollingEvery(WAIT_MILLIS)
                .ignoring(NoSuchElementException.class);
        if (!expectedLoggedInState) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        WebElement signInButton = wait.until(driver -> driver.findElement(By.xpath("//span[@class='header__account--btn__text']")));
        String loginButtonText = signInButton.getText();
        boolean actualLoggedInState = !loginButtonText.equals("Sign In");
        Assert.assertEquals(actualLoggedInState, expectedLoggedInState, "Login state is not as expected");
    }

    public void userLoggedOut() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(WAIT_SECONDS)
                .pollingEvery(WAIT_MILLIS)
                .ignoring(NoSuchElementException.class);
        driver.get(LoginPage.myAccountPageURL());
        wait.until(ExpectedConditions.urlToBe(myAccountPageURL()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByFromType(CSS, "div[class='my_account__menu--list']")));
        clickOnElement(CSS, "div[class='my_account__menu--list']");
    }
}
