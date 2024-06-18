package allcom_selenium.pages.header;

import allcom_selenium.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static allcom_selenium.pages.homePage.HomePage.HOME_PAGE_URL;
import static org.testng.Assert.assertEquals;

@Getter
public class SearchBox extends BasePage {
  public static final String SEARCH_BOX_URL = HOME_PAGE_URL + "/search";

  public SearchBox(WebDriver driver) {
    super(driver);
  }

  @FindBy(className = "search__container")
  WebElement searchBox;

  @FindBy(className = "search__input")
  WebElement searchBoxInput;

  public By getSearchButtonBottom() {
    return By.cssSelector("[data-testid='searchBottom']");
  }

  public void assertSearchBoxInputText(String expectedText) {
    assertEquals(getSearchBoxInput().getAttribute("value"), expectedText, "Search box input text is not correct");
  }
}
