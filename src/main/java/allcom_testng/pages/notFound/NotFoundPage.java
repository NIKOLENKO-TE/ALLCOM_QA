package allcom_testng.pages.notFound;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NotFoundPage extends BasePage {
    public NotFoundPage(WebDriver driver) {
        super(driver);
    }

    public static String notFoundPageURL() {
        return HomePage.homePageURL() + "/404";
    }
    @FindBy(className = "error__content")
    WebElement errorContent;
    public WebElement getErrorContent() {
        return errorContent;
    }

}
