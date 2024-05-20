package allcom_testng.pages.userProfile;

import allcom_testng.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserProfilePage extends BasePage {
    BasePage basePage = new BasePage(driver);

    public UserProfilePage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[text()='About me']")
    public WebElement aboutMeLink;

    public WebElement aboutMe() {
        return aboutMeLink;
    }

    @FindBy(xpath = "//div[text()='Change password']")
    public WebElement changePasswordLink;

    public WebElement changePassword() {
        return changePasswordLink;
    }

@FindBy(xpath = "(//div[contains(text(),'Log Out')])[1]")
WebElement logOutLink;
    public WebElement logOut() {
        return  logOutLink;
    }
}