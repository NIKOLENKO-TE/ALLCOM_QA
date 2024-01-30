package allcom.tests.notFound;

import allcom.pages.BasePage;
import allcom.pages.HomePage;
import allcom.pages.NotFoundPage.NotFoundPage;
import allcom.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NotFoundPositiveTests extends TestBase {
    private BasePage basePage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        new NotFoundPage(app.driver);
        basePage.goToPage(NotFoundPage.notFoundPageURL());
        basePage.isCurrentPage(NotFoundPage.notFoundPageURL(),true);
    }
    @Test
    public void clickOnBackToHomeButton() {
        basePage.clickOnElement(BasePage.ElementType.DATA_TESTID,"back_to_home_button");
        basePage.isCurrentPage(HomePage.homePageURL(),true);
    }
    @Test
    public void findContentTitleElement() {
        basePage.isElementPresent(BasePage.ElementType.DATA_TESTID, "content_title", true);
    }
}