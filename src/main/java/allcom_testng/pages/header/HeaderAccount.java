package allcom_testng.pages.header;

import allcom_testng.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderAccount extends BasePage {
    public HeaderAccount(WebDriver driver) {
        super(driver);
    }

    BasePage basePage = new BasePage(driver);
    @FindBy(xpath = "//a[@data-testid='myAccountTop_link'][normalize-space()='Sign In']")
    WebElement myAccountTopBeforeLoggedInLink;

    public WebElement myAccountTopBeforeLoggedIn() {
        return myAccountTopBeforeLoggedInLink;
    }

    //@FindBy(xpath = "//a[@data-testid='myAccountTop_link'][not(contains(.,'Sign In'))]")
    //@FindBy(xpath = "//*[@id=\"root\"]/header/div[1]/div/div/div[4]/ul/li[1]")
    //@FindBy(xpath = "(//li[@data-testid='myAccountTop'])[1]")
    //@FindBy(xpath = "/html/body/div/header/div[1]/div/div/div[4]/ul/li[1]/a")
    @FindBy(xpath = "/html/body/div/header/div[1]/div/div/div[4]/ul/li[1]")
    WebElement myAccountTopAfterLoggedInButton;

    public WebElement myAccountTopAfterLoggedIn() {
        return myAccountTopAfterLoggedInButton;
    }

    @FindBy(xpath = "(//li[@data-testid='wishlistTop'])[1]")
    private WebElement wishlistTopButtonLink;

    public WebElement wishlistTop() {
        return wishlistTopButtonLink;
    }

    @FindBy(xpath = "(//li[@data-testid='cartTop'])[1]")
    private WebElement cartTopButtonLink;

    public WebElement cartTop() {
        return cartTopButtonLink;
    }

    @FindBy(xpath = "(//span[@class='header__account--btn__text'])[1]")
    private WebElement myAccountTopButton;
    public WebElement myAccountTop() {
        return myAccountTopButton;
    }
}
