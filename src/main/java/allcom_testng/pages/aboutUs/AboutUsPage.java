package allcom_testng.pages.aboutUs;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.homePage.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AboutUsPage extends BasePage {

    public AboutUsPage(WebDriver driver) {
        super(driver);
    }

    public static String aboutUsPageURL() {
        return HomePage.homePageURL() + "/about_us";
    }

    @FindBy(className = "about-us-header")
    WebElement aboutUsHeader;

    public WebElement getAboutUsHeader() {
        return aboutUsHeader;
    }

    @FindBy(className = "about-us-content")
    WebElement aboutUsContent;

    public WebElement getAboutUsContent() {
        return aboutUsContent;
    }
    @FindBy(className = "leaflet-container")
    WebElement aboutUsMapContainer;

    public WebElement getAboutUsMapContainer() {
        return aboutUsMapContainer;
    }
}
