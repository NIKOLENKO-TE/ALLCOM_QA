package allcom_testng.pages.categories;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.homePage.HomePage;
import org.openqa.selenium.WebDriver;

import static allcom_testng.pages.homePage.HomePage.HOME_PAGE_URL;

public class CategoriesPage extends BasePage {
  public CategoriesPage(WebDriver driver) {
    super(driver);
  }

  public static final String CATEGORIES_PAGE_URL = HOME_PAGE_URL + "/categories";

  public Object getCategories() {
    return this;
  }
}