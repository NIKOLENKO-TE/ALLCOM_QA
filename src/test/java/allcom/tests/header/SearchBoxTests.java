package allcom.tests.header;

import allcom.pages.BasePage;
import allcom.pages.HomePage;
import allcom.pages.header.SearchBox;
import allcom.pages.DataProviderClass;
import allcom.tests.TestBase;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchBoxTests extends TestBase {
    private static final Dimension HIGH_RESOLUTION = new Dimension(1004, 800);
    private static final Dimension LOW_RESOLUTION = new Dimension(990, 800);
    private static final Duration WAIT_SECONDS = Duration.ofSeconds(5);
    private BasePage basePage;
    private SearchBox searchBox;
    private WebDriverWait wait;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        searchBox = new SearchBox(app.driver);
        basePage.goToPage(HomePage.homePageURL());
        basePage.isCurrentPage(HomePage.homePageURL(), true);
        app.driver.manage().window().maximize();
        wait = new WebDriverWait(app.driver, WAIT_SECONDS);
    }

    @Test
    public void searchBoxIsPresent() {
        Assert.assertNotNull(searchBox.getSearchBox(), "Search box is not present");
    }

    @Test
    public void searchBoxIsPresentOnHighResolution() {
        app.driver.manage().window().setSize(HIGH_RESOLUTION);
        wait.until(ExpectedConditions.elementToBeClickable(searchBox.getSearchBox()));
    }

    @Test
    public void searchBoxIsAbsentOnLowResolution() {
        app.driver.manage().window().setSize(LOW_RESOLUTION);
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(searchBox.getSearchBox())));
    }

    @Test
    public void searchButtonBottomIsPresentOnLowResolution() {
        app.driver.manage().window().setSize(LOW_RESOLUTION);
        wait.until(ExpectedConditions.elementToBeClickable(searchBox.getSearchButtonBottom()));
    }

    @Test
    public void searchButtonBottomIsAbsentOnHighResolution() {
        app.driver.manage().window().setSize(HIGH_RESOLUTION);
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(searchBox.getSearchButtonBottom())));
    }

    @Test
    public void searchBoxInputIsPresent() {
        Assert.assertNotNull(searchBox.getSearchBoxInput(), "Search box input is not present");
    }

    @Test
    public void searchBoxInputTextTest() {
        String searchText = "some text to input test";
        basePage.type(searchBox.getSearchBoxInput(), searchText);
        searchBox.assertSearchBoxInputText(searchText);
    }


    @Test
    public void searchBoxIsEmptyBeforeInput() {
        String searchText = "";
        Assert.assertEquals(searchBox.getSearchBoxInput().getAttribute("value"), searchText, "Search box is not empty before input");
    }

    @Test
    public void searchBoxAcceptsLongInput() {
        String longSearchText = "This is a very long search text that should still be accepted by the search box";
        basePage.type(searchBox.getSearchBoxInput(), longSearchText);
        searchBox.assertSearchBoxInputText(longSearchText);
    }

    @Test
    public void searchBoxAcceptsSpecialCharacters() {
        String specialCharacterText = "!@#$%^&*()";
        basePage.type(searchBox.getSearchBoxInput(), specialCharacterText);
        searchBox.assertSearchBoxInputText(specialCharacterText);
    }

    @Test
    public void searchBoxIsClickableOnDifferentResolutions() {
        searchBox.validateClickable(searchBox.getSearchButton(), HIGH_RESOLUTION, WAIT_SECONDS);
        searchBox.validateClickable(searchBox.getSearchButtonBottom(), LOW_RESOLUTION, WAIT_SECONDS);
    }

    @Test
    public void searchButtonIsClickable() {
        searchBox.validateClickable(searchBox.getSearchButton(), HIGH_RESOLUTION, WAIT_SECONDS);
    }

    @Test
    public void searchButtonBottomIsClickable() {
        searchBox.validateClickable(searchBox.getSearchButtonBottom(), LOW_RESOLUTION, WAIT_SECONDS);
    }

    @Test
    public void searchRedirectsToCorrectPageTest() {
        String searchText = "some text to input test";
        basePage.type(searchBox.getSearchBoxInput(), searchText);
        searchBox.clickOnSearchButton();
        basePage.isCurrentPage(SearchBox.searchResultURL(), false);
    }


    @Test(dataProvider = "invalidSearchData", dataProviderClass = DataProviderClass.class)
    public void searchBoxInputTest(String searchText) {
        basePage.type(searchBox.getSearchBoxInput(), searchText);
        searchBox.assertSearchBoxInputText(searchText);
    }

    @Test(dataProvider = "invalidSearchData", dataProviderClass = DataProviderClass.class)
    public void validateSearchWithIncorrectData(String invalidSearch) {
        basePage.type(searchBox.getSearchBoxInput(), invalidSearch);
        //basePage.isValidationErrorPresent(false); // TODO change to true when validation is implemented
    }
}

