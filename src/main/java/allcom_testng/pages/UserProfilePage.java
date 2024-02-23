package allcom_testng.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    @FindBy(xpath = "//div[text()='My auctions']")
    public WebElement myAuctions;
    public void clickOnMyAuctions() {
        myAuctions.click();
    }
    @FindBy(xpath = "//div[text()='Wishlist']")
    public WebElement wishlist;
    public void clickOnWishlist() {
        wishlist.click();
    }
    @FindBy(xpath = "//div[text()='Log Out']")
    public WebElement logOut;
    public void logout() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_SECONDS);
        wait.until(ExpectedConditions.visibilityOf(logOut));
        logOut.click();
    }
    @FindBy(xpath = "//div[text()='About me']")
    public WebElement aboutMe;
    public void clickOnAboutMe() {
        aboutMe.click();
    }
    @FindBy(xpath = "//div[text()='Change password']")
    public WebElement changePassword;
    public void clickOnChangePassword() {
        changePassword.click();
    }
}