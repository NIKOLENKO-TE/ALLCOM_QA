package allcom_selenium.pages.categories;

import allcom_selenium.pages.BasePage;
import org.openqa.selenium.WebDriver;

import static allcom_selenium.pages.homePage.HomePage.HOME_PAGE_URL;

public class CategoriesPage extends BasePage {
  public CategoriesPage(WebDriver driver) {
    super(driver);
  }

  public static final String CATEGORIES_PAGE_URL = HOME_PAGE_URL + "/categories";

  public Object getCategories() {
    return this;
  }
}