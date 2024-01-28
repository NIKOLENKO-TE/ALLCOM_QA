package allcom.tests.aboutUs;

import allcom.pages.aboutUs.AboutUsPage;
import allcom.pages.BasePage;
import allcom.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AboutUsPageTests extends TestBase {
    private AboutUsPage aboutUsPage;

    @BeforeMethod
    public void precondition() {
        BasePage basePage = new BasePage(app.driver);
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
