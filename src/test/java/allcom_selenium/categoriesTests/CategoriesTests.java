package allcom_selenium.categoriesTests;

import allcom_selenium.TestBaseSE;
import allcom_selenium.pages.BasePage;
import allcom_selenium.pages.categories.CategoriesPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static allcom_selenium.pages.categories.CategoriesPage.CATEGORIES_PAGE_URL;
import static org.testng.Assert.assertNotNull;

public class CategoriesTests extends TestBaseSE {
  BasePage basePage;
  CategoriesPage categoriesPage;

  @BeforeMethod
  public void precondition() {
    basePage = new BasePage(app.driver);
    categoriesPage = new CategoriesPage(app.driver);
    basePage.goToPage(CATEGORIES_PAGE_URL);
    basePage.isCurrentPage(CATEGORIES_PAGE_URL, true);
  }

  @Test
  public void categoriesArePresent() {
    assertNotNull(categoriesPage.getCategories(), "Categories are not present");
  }
}