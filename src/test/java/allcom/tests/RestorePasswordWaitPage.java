package allcom.tests;

import allcom.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class RestorePasswordWaitPage extends BasePage {
    public RestorePasswordWaitPage(WebDriver driver) {
        super(driver);
    }
    public static String restorePasswordWaitPageURL() {
        return "https://deploy-preview-34--incredible-snickerdoodle-514a2d.netlify.app/restore_password_wait";
    }
}
