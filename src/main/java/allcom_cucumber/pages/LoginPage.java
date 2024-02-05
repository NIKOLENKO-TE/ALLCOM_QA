package allcom_cucumber.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "email")
    WebElement emailField;

    @FindBy(name = "password")
    WebElement passwordField;

    public LoginPage enterValidUsernamePassword(String email, String password) {
        type(emailField, email);
        type(passwordField, password);
        return this;
    }

    @FindBy(css = "[data-testid='button_login']")
    WebElement loginButton;

    public void clickOnLoginButton() {
        loginButton.click();
    }

    @FindBy(css = "p.login_register--header")
    WebElement loginHeader;

    public boolean isLoginHeaderPresent() {
        return loginHeader.isDisplayed();
    }
}
