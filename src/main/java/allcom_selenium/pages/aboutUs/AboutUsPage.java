package allcom_selenium.pages.aboutUs;

import allcom_selenium.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static allcom_selenium.pages.homePage.HomePage.HOME_PAGE_URL;

@Getter
public class AboutUsPage extends BasePage {

  public AboutUsPage(WebDriver driver) {
    super(driver);
  }

  public static final String ABOUT_US_PAGE_URL = HOME_PAGE_URL + "/about_us";

  @FindBy(className = "about-us-header")
  WebElement aboutUsHeader;

  @FindBy(className = "about-us-content")
  WebElement aboutUsContent;

  @FindBy(className = "leaflet-container")
  WebElement aboutUsMapContainer;

}
