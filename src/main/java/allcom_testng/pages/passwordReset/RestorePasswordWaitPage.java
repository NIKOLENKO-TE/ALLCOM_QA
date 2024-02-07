package allcom_testng.pages.passwordReset;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class RestorePasswordWaitPage extends BasePage {
    public RestorePasswordWaitPage(WebDriver driver) {
        super(driver);
    }
    public static String restorePasswordWaitPageURL() {
        return HomePage.homePageURL() + "/restore_password_wait";
    }
}
