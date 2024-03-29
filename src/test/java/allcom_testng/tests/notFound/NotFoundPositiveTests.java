package allcom_testng.tests.notFound;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.HomePage;
import allcom_testng.pages.notFound.NotFoundPage;
import allcom_testng.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NotFoundPositiveTests extends TestBase {
    private BasePage basePage;
    private NotFoundPage notFoundPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        notFoundPage = new NotFoundPage(app.driver);
        basePage.goToPage(NotFoundPage.notFoundPageURL());
        basePage.isCurrentPage(NotFoundPage.notFoundPageURL(), true);
    }

    @Test
    public void clickOnBackToHomeButton() {
        basePage.clickOnElement(BasePage.ElementType.DATA_TESTID, "back_to_home_button");
        basePage.isCurrentPage(HomePage.homePageURL(), true);
    }

    @Test
    public void findContentTitleElement() {
        basePage.isElementPresent(BasePage.ElementType.DATA_TESTID, "content_title", true);
    }

    @Test
    public void findErrorContentElement() {
        Assert.assertNotNull(notFoundPage.getErrorContent(), "Error content is not present");
    }
}