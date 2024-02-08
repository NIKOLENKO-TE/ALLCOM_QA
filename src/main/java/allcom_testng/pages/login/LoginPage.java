package allcom_testng.pages.login;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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


    @FindBy(name = "email")
    WebElement USER_EMAIL;
    @FindBy(name = "password")
    WebElement USER_PASSWORD;

    public void login(String email, String password) {
        type(USER_EMAIL, email);
        type(USER_PASSWORD, password);
    }
    public void loginAdmin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/login']")));
            wait.until(ExpectedConditions.visibilityOf(USER_EMAIL));
            wait.until(ExpectedConditions.visibilityOf(USER_PASSWORD));
            type(USER_EMAIL, "james-smith@mail.com");
            type(USER_PASSWORD, "Qwerty007!");
            clickLoginButton();
        } catch (TimeoutException e) {
            System.out.println("Login element not found. Skipping login.");
        }
    }
    @FindBy(css = "[data-testid='button_login']")
    private WebElement loginButton;

    public WebElement getLoginButton() {
        return loginButton;
    }
    public void clickLoginButton() {
        clickOnElement(BasePage.ElementType.DATA_TESTID, "button_login");
    }

    public void clickRegisterButton() {
        clickOnElement(BasePage.ElementType.DATA_TESTID, "button_register");
    }

    public void clickForgotPasswordButton() {
        clickOnElement(BasePage.ElementType.DATA_TESTID, "ButtonForgotPassword");
    }
}
