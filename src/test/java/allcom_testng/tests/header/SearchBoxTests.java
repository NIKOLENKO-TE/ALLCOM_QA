package allcom_testng.tests.header;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.DataProviderClass;
import allcom_testng.pages.HomePage;
import allcom_testng.pages.header.SearchBox;
import allcom_testng.tests.TestBase;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchBoxTests extends TestBase {
    private static final Dimension FULL_HD_RESOLUTION = new Dimension(1920, 1080);
    private static final Dimension LOW_RESOLUTION = new Dimension(990, 800);
    private static final Duration WAIT_SECONDS = Duration.ofSeconds(5);
    private WebDriverWait wait;
    BasePage basePage = new BasePage(app.driver);
    SearchBox searchBox = new SearchBox(app.driver);

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        searchBox = new SearchBox(app.driver);
        app.driver.manage().window().maximize();
        basePage.goToPage(HomePage.homePageURL());
        basePage.isCurrentPage(HomePage.homePageURL(), true);
        wait = new WebDriverWait(app.driver, WAIT_SECONDS);
    }

    @Test(priority = 1)
    public void searchBoxIsPresent() {
        Assert.assertNotNull(searchBox.getSearchBox(), "Search box is not present");
    }

    @Test(priority = 2)
    public void searchBoxIsPresentOnHighResolution() {
        app.driver.manage().window().setSize(FULL_HD_RESOLUTION);
        wait.until(ExpectedConditions.elementToBeClickable(searchBox.getSearchBoxInput()));
    }

    @Test(priority = 3)
    public void searchBoxIsAbsentOnLowResolution() {
        app.driver.manage().window().setSize(LOW_RESOLUTION);
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(searchBox.getSearchBoxInput())));
    }

    @Test(priority = 4)
    public void searchButtonBottomIsPresentOnLowResolution() {
        app.driver.manage().window().setSize(LOW_RESOLUTION);
        wait.until(ExpectedConditions.elementToBeClickable(searchBox.getSearchButtonBottom()));
    }

    @Test(priority = 5)
    public void searchButtonBottomIsAbsentOnHighResolution() {
        app.driver.manage().window().setSize(FULL_HD_RESOLUTION);
        basePage.isElementClickable(BasePage.ElementType.ID, "searchBottom", false);
    }

    @Test(priority = 6)
    public void searchBoxInputIsPresent() {
        Assert.assertNotNull(searchBox.getSearchBoxInput(), "Search box input is not present");
    }

    @Test(priority = 7)
    public void searchBoxInputTextTest() {
        String searchText = "some text to input test";
        basePage.type(searchBox.getSearchBoxInput(), searchText);
        searchBox.assertSearchBoxInputText(searchText);
    }

    @Test(priority = 8)
    public void searchBoxIsEmptyBeforeInput() {
        String searchText = "";
        Assert.assertEquals(searchBox.getSearchBoxInput().getAttribute("value"), searchText, "Search box is not empty before input");
    }

    @Test(priority = 9)
    public void searchBoxAcceptsLongInput() {
        String longSearchText = "This is a very long search text that should still be accepted by the search box";
        basePage.type(searchBox.getSearchBoxInput(), longSearchText);
        searchBox.assertSearchBoxInputText(longSearchText);
    }

    @Test(priority = 10)
    public void searchBoxAcceptsSpecialCharacters() {
        String specialCharacterText = "!@#$%^&*()";
        basePage.type(searchBox.getSearchBoxInput(), specialCharacterText);
        searchBox.assertSearchBoxInputText(specialCharacterText);
    }

    @Test(priority = 11)
    public void searchBoxIsClickableOnLowResolution() {
        basePage.isElementClickable(searchBox.getSearchButtonBottom(), LOW_RESOLUTION, true);
    }
    @Test(priority = 12)
    public void searchBoxIsNotClickableOnFullHDResolution() {
        basePage.isElementClickable(searchBox.getSearchButtonBottom(), FULL_HD_RESOLUTION, false);
    }
    @Test(priority = 12)
    public void searchBoxIsNotClickableOnFullHDResolution2() {
        basePage.isElementClickable(searchBox.getSearchButtonBottom(), FULL_HD_RESOLUTION, false);
    }
    @Test(priority = 12)
    public void searchButtonBottomIsClickable() {
        basePage.isElementClickable(searchBox.getSearchButtonBottom(), LOW_RESOLUTION,true);
    }

    @Test(priority = 13)
    public void searchRedirectsToCorrectPageTest() {
        app.driver.manage().window().setSize(FULL_HD_RESOLUTION);
        basePage.type(searchBox.getSearchBoxInput(), "text");
        searchBox.getSearchBoxInput().submit();
        basePage.isCurrentPage(SearchBox.searchResultURL(), true);
    }

    @Test(priority = 14, dataProvider = "invalidSearchData", dataProviderClass = DataProviderClass.class)
    public void searchBoxInputTest(String searchText) {
        basePage.type(searchBox.getSearchBoxInput(), searchText);
        searchBox.assertSearchBoxInputText(searchText);
    }

    @Test(priority = 15, dataProvider = "invalidSearchData", dataProviderClass = DataProviderClass.class)
    public void validateSearchWithIncorrectData(String invalidSearch) {
        basePage.type(searchBox.getSearchBoxInput(), invalidSearch);
        basePage.isValidationErrorPresent(false); // TODO change to true when validation is implemented
    }
}