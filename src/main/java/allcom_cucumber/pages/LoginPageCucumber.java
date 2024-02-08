package allcom_cucumber.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import allcom_testng.pages.BasePage;
import allcom_testng.pages.HomePage;

public class LoginPageCucumber extends BasePage {
    public LoginPageCucumber(WebDriver driver) {
        super(driver);
    }
    BasePage basePage = new BasePage(driver);
    @FindBy(name = "email")
    WebElement emailField;

    @FindBy(name = "password")
    WebElement passwordField;

    public LoginPageCucumber enterValidUsernamePassword(String email, String password) {
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
