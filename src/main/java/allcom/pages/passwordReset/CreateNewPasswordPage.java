package allcom.pages.passwordReset;

import allcom.pages.BasePage;
import allcom.pages.HomePage;
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

    @FindBy(name = "email")
    WebElement usernameField;

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getPasswordConfirmField() {
        return passwordConfirmField;
    }
}
