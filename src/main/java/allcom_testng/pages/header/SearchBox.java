package allcom_testng.pages.header;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SearchBox extends BasePage {
    public SearchBox(WebDriver driver) {
        super(driver);
    }
    public static String searchResultURL() {
        return HomePage.homePageURL() + "/?#222";
    }
    @FindBy(className = "search__container")
    WebElement searchBox;

    public WebElement getSearchBox() {
        return searchBox;
    }
    @FindBy(className = "search__input")
    WebElement searchBoxInput;

    public WebElement getSearchBoxInput() {
        return searchBoxInput;
    }

    public By getSearchButtonBottom() {
        By locator = By.cssSelector("[data-testid='searchBottom']");
        isElementPresent(BasePage.ElementType.DATA_TESTID, "searchBottom", true);
        return locator;
    }
    public void validateClickable(By element, org.openqa.selenium.Dimension resolution, Duration waitSeconds) {
        driver.manage().window().setSize(resolution);
        WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void assertSearchBoxInputText(String expectedText) {
        Assert.assertEquals(getSearchBoxInput().getAttribute("value"), expectedText, "Search box input text is not correct");
    }
}
