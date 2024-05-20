package allcom_testng.pages.header;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.homePage.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class SearchBox extends BasePage {
    public SearchBox(WebDriver driver) {
        super(driver);
    }
    public static String searchResultURL() {
        return HomePage.homePageURL() + "/?";
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
        return By.cssSelector("[data-testid='searchBottom']");
    }

    public void assertSearchBoxInputText(String expectedText) {
        Assert.assertEquals(getSearchBoxInput().getAttribute("value"), expectedText, "Search box input text is not correct");
    }
}
