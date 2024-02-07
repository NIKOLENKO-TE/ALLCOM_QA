package allcom.tests.categoriesTests;

import allcom.pages.BasePage;
import allcom.pages.categories.CategoriesPage;
import allcom.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CategoriesTests extends TestBase {
    BasePage basePage;
    CategoriesPage categoriesPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        categoriesPage = new CategoriesPage(app.driver);
        basePage.goToPage(CategoriesPage.categoriesPageURL());
        basePage.isCurrentPage(CategoriesPage.categoriesPageURL(), true);
    }

    @Test
    public void categoriesArePresent() {
        Assert.assertNotNull(categoriesPage.getCategories(), "Categories are not present");
    }
}