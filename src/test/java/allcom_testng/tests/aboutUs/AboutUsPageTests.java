package allcom_testng.tests.aboutUs;

import allcom_testng.pages.aboutUs.AboutUsPage;
import allcom_testng.pages.BasePage;
import allcom_testng.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AboutUsPageTests extends TestBase {
    private AboutUsPage aboutUsPage;
    private BasePage basePage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        aboutUsPage = new AboutUsPage(app.driver);
        basePage.goToPage(AboutUsPage.aboutUsPageURL());
        basePage.isCurrentPage(AboutUsPage.aboutUsPageURL(), true);
    }

    @Test
    public void aboutUsHeaderIsPresent() {
        Assert.assertNotNull(aboutUsPage.getAboutUsHeader(), "AboutUsHeader is not present");
    }

    @Test
    public void aboutUsContentIsPresent() {
        Assert.assertNotNull(aboutUsPage.getAboutUsContent(), "AboutUsContent is not present");
    }

    @Test
    public void aboutUsMapContainerIsPresent() {
        Assert.assertNotNull(aboutUsPage.getAboutUsMapContainer(), "AboutUsMapContainer is not present");
    }
}
