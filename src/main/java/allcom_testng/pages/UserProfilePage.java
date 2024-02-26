package allcom_testng.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static allcom_testng.pages.HomePage.homePageURL;

public class UserProfilePage extends BasePage{
    private WebDriver driver;
    private static final Duration WAIT_SECONDS = Duration.ofSeconds(5);
    public UserProfilePage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static String userProfilePageURL() {
        return homePageURL() + "/user/my_account/about_me";
    }

    public void clickOnMyAuctions() {
        clickOnElement(ElementType.XPATH, "//div[text()='My auctions']");
    }
    public void clickOnWishlist() {
        clickOnElement(ElementType.XPATH, "//div[text()='Wishlist']");
    }
    public void logout() {
        new WebDriverWait(driver, WAIT_SECONDS)
                .until(ExpectedConditions
                        .visibilityOf((WebElement) By.xpath("//div[text()='Log Out']")))
                .click();
    }
    public void clickOnAboutMe() {
        clickOnElement(ElementType.XPATH, "//div[text()='About me']");
    }
    public void clickOnChangePassword() {
        clickOnElement(ElementType.XPATH, "//div[text()='Change password']");
    }
}