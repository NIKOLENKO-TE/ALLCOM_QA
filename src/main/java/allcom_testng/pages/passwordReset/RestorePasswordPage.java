package allcom_testng.pages.passwordReset;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.homePage.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RestorePasswordPage extends BasePage {
    public RestorePasswordPage(WebDriver driver) {
        super(driver);
    }

    public static String restorePasswordPageURL() {
        return HomePage.homePageURL() + "/restore_password";
    }

    @FindBy(name = "email")
    WebElement usernameField;

    public void emailToRestore(String email) {
        type(usernameField, email);
    }

    public void clickRestorePasswordButton() {
        clickOnElement(BasePage.ElementType.DATA_TESTID, "buttonRestore");
    }
}
