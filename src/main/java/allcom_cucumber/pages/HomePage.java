package allcom_cucumber.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage{
    public static final Duration WAIT_SEC = Duration.ofSeconds(10);
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[@class='home_page__title--h2']")
    WebElement homePageTitleAuctions;

    public HomePage isHomePageTitleAuctionsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_SEC);
        wait.until(ExpectedConditions.visibilityOf(homePageTitleAuctions));
        isElementDisplayed(homePageTitleAuctions);
        return this;
    }
    @FindBy(css = "[data-testid='login_link']")
    WebElement loginLink;
    public LoginPage clickOnLoginLink() {
        click(loginLink);
        return new LoginPage(driver);
    }
}
