package allcom_selenium.header;

import allcom_selenium.TestBaseSE;
import allcom_selenium.pages.BasePage;
import allcom_selenium.pages.DataProviderClass;
import allcom_selenium.pages.header.SearchBox;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

import static allcom_selenium.pages.header.SearchBox.SEARCH_BOX_RESULT_URL;
import static allcom_selenium.pages.homePage.HomePage.HOME_PAGE_URL;

@Test(groups = { "SearchBoxTestsClass" })
public class SearchBoxTests extends TestBaseSE {
  private static final Dimension FULL_HD_RESOLUTION = new Dimension(1920, 1080);
  private static final Dimension LOW_RESOLUTION = new Dimension(990, 800);
  private BasePage basePage;
  private SearchBox searchBox;
  private WebDriverWait wait;

  @BeforeMethod
  public void precondition() {
    basePage = new BasePage(driver);
    searchBox = new SearchBox(driver);
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    driver.manage().window().maximize();
    basePage.goToPage(HOME_PAGE_URL);
    basePage.isCurrentPage(HOME_PAGE_URL, true);
  }

  @Test(priority = 1, description = "Check if search box is present", groups = {"smoke"})
  public void searchBoxIsPresent() {
    Assert.assertNotNull(searchBox.getSearchBox(), "Search box is not present");
  }

  @Test(priority = 2, description = "Check if search box is present on high resolution", groups = {"smoke"})
  public void searchBoxIsPresentOnFullHDResolution() {
    driver.manage().window().setSize(FULL_HD_RESOLUTION);
    wait.until(ExpectedConditions.elementToBeClickable(searchBox.getSearchBoxInput()));
  }

  @Test(priority = 3, description = "Check if search box is absent on low resolution")
  public void searchBoxIsAbsentOnLowResolution() {
    driver.manage().window().setSize(LOW_RESOLUTION);
    wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(searchBox.getSearchBoxInput())));
  }

  @Test(priority = 4, description = "Check if search button bottom is present on low resolution", groups = {"smoke"})
  public void searchButtonBottomIsPresentOnLowResolution() {
    driver.manage().window().setSize(LOW_RESOLUTION);
    wait.until(ExpectedConditions.elementToBeClickable(searchBox.getSearchButtonBottom()));
  }

  @Test(priority = 5, description = "Check if search button bottom is absent on high resolution")
  public void searchButtonBottomIsAbsentOnHighResolution() {
    driver.manage().window().setSize(FULL_HD_RESOLUTION);
    basePage.isElementClickable(BasePage.ElementType.ID, "searchBottom", false);
  }

  @Test(priority = 6, description = "Check if search box input is present")
  public void searchBoxInputIsPresent() {
    Assert.assertNotNull(searchBox.getSearchBoxInput(), "Search box input is not present");
  }

  @Test(priority = 7, description = "Test search box input text")
  public void searchBoxInputTextTest() {
    String searchText = "some text to input test";
    basePage.type(searchBox.getSearchBoxInput(), searchText);
    searchBox.assertSearchBoxInputText(searchText);
  }

  @Test(priority = 8, description = "Check if search box is empty before input")
  public void searchBoxIsEmptyBeforeInput() {
    String searchText = "";
    Assert.assertEquals(searchBox.getSearchBoxInput().getAttribute("value"), searchText, "Search box is not empty before input");
  }

  @Test(priority = 9, description = "Check if search box accepts long input")
  public void searchBoxAcceptsLongInput() {
    String longSearchText = "This is a very long search text that should still be accepted by the search box";
    basePage.type(searchBox.getSearchBoxInput(), longSearchText);
    searchBox.assertSearchBoxInputText(longSearchText);
  }

  @Test(priority = 10, description = "Check if search box accepts special characters")
  public void searchBoxAcceptsSpecialCharacters() {
    String specialCharacterText = "!@#$%^&*()";
    basePage.type(searchBox.getSearchBoxInput(), specialCharacterText);
    searchBox.assertSearchBoxInputText(specialCharacterText);
  }

  @Test(priority = 11, description = "Check if search box is clickable on low resolution")
  public void searchBoxIsClickableOnLowResolution() {
    driver.manage().window().setSize(LOW_RESOLUTION);
    basePage.isElementClickable(searchBox.getSearchButtonBottom(), LOW_RESOLUTION, true);
  }

  @Test(priority = 12, description = "Check if search box is not clickable on full HD resolution")
  public void searchBoxIsNotClickableOnFullHDResolution() {
    driver.manage().window().setSize(FULL_HD_RESOLUTION);
    basePage.isElementClickable(searchBox.getSearchButtonBottom(), FULL_HD_RESOLUTION, false);
  }

  @Test(priority = 13, description = "Check if search redirects to correct page")
  public void searchRedirectsToCorrectPageTest() {
    driver.manage().window().setSize(FULL_HD_RESOLUTION);
    basePage.type(searchBox.getSearchBoxInput(), "text");
    searchBox.getSearchBoxInput().submit();
    basePage.isCurrentPage(SEARCH_BOX_RESULT_URL, true); //TODO change to true when
  }
  @Test(priority = 14, dataProvider = "invalidSearchData", dataProviderClass = DataProviderClass.class, description = "Test search box input")
  public void searchBoxInputTest(String searchText) {
    basePage.type(searchBox.getSearchBoxInput(), searchText);
    searchBox.assertSearchBoxInputText(searchText);
  }

  @Test(priority = 15, dataProvider = "invalidSearchData", dataProviderClass = DataProviderClass.class, description = "Validate search with incorrect data")
  public void validateSearchWithIncorrectData(String invalidSearch) {
    basePage.type(searchBox.getSearchBoxInput(), invalidSearch);
    basePage.isValidationErrorPresent(false); // TODO change to true when validation is implemented
  }
}