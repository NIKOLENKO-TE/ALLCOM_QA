package allcom_testng.categoriesTests;

import allcom_testng.TestBaseSE;
import allcom_testng.pages.BasePage;
import allcom_testng.pages.categories.CategoriesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CategoriesTests extends TestBaseSE {
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