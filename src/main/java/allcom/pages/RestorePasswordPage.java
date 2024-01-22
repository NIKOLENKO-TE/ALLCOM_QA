package allcom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class RestorePasswordPage extends BasePage{
    public RestorePasswordPage(WebDriver driver) {
        super(driver);
    }
    public static String restorePasswordPageURL() {
        return "https://deploy-preview-34--incredible-snickerdoodle-514a2d.netlify.app/restore_password";
    }
    @FindBy(name = "email")
    WebElement usernameField;
    public void emailToRestore(String email) {
        type(usernameField, email);
    }
}
