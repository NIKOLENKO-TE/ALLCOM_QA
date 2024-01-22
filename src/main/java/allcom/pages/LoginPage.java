package allcom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public static String loginPageURL() {
        return "https://deploy-preview-34--incredible-snickerdoodle-514a2d.netlify.app/login";
    }
    @FindBy(name = "email")
    WebElement usernameField;
    @FindBy(name = "password")
    WebElement passwordField;
    public void login(String email, String password) {
        type(usernameField, email);
        type(passwordField, password);
    }
}
