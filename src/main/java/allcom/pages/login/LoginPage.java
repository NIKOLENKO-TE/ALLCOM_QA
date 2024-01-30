package allcom.pages.login;

import allcom.pages.BasePage;
import allcom.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public static String loginPageURL() {
        return HomePage.homePageURL() + "/login";
    }
    @FindBy(name = "email")
    WebElement USER_EMAIL;
    @FindBy(name = "password")
    WebElement USER_PASSWORD;
    public void login(String email, String password) {
        type(USER_EMAIL, email);
        type(USER_PASSWORD, password);
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
