//HomePage.java
package allcom_testng.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class HomePage extends BasePage {
    public static final String LANGUAGE_SELECTOR = "[data-testid='language-text-set']";
    public static final String LANGUAGE_ITEM_XPATH = "//li[contains(text(), '%s')]";
    public static final Duration WAIT_SEC = Duration.ofSeconds(5);
    public HomePage() {
    }
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public static String homePageURL() {
        return "http://allcom.itvm.com.ua:5173";
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