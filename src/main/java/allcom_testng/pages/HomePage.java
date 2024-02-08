package allcom_testng.pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public static String homePageURL() {
        return "http://allcom.itvm.com.ua:5173";
    }
}