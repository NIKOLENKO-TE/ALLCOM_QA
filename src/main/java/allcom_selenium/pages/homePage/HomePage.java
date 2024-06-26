//HomePage.java
package allcom_selenium.pages.homePage;

import allcom_selenium.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class HomePage extends BasePage {
    public static final String LANGUAGE_SELECTOR = "[data-testid='language-text-set']";
    public static final String LANGUAGE_ITEM_XPATH = "//li[contains(text(), '%s')]";
    public static final Duration WAIT_SEC = Duration.ofSeconds(5);
    public static final String HOME_PAGE_URL = "http://allcom.itvm.com.ua:5173";
    public HomePage() {
    }
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[@class='home_page__title--h2']")
    public WebElement homePageTitleAuctions;

    public boolean isHomePageTitleAuctionsDisplayed() {
        return homePageTitleAuctions.isDisplayed();
    }
    @FindBy(css = "[data-testid='login_link']")
    WebElement loginLink;
    public void clickOnLoginLink() {
        click(loginLink);
    }
}