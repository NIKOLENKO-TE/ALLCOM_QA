package allcom.pages.passwordReset;

import allcom.pages.BasePage;
import allcom.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class RestorePasswordWaitPage extends BasePage {
    public RestorePasswordWaitPage(WebDriver driver) {
        super(driver);
    }
    public static String restorePasswordWaitPageURL() {
        return HomePage.homePageURL() + "/restore_password_wait";
    }
}
