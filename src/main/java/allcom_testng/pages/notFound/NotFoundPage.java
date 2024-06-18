package allcom_testng.pages.notFound;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.homePage.HomePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class NotFoundPage extends BasePage {

  public static final String NOT_FOUND_PAGE_URL = HomePage.HOME_PAGE_URL + "/404";

  public NotFoundPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(className = "error__content")
  WebElement errorContent;
}
