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
        BasePage basePage = new BasePage(driver);
        aboutUsPage = new AboutUsPage(driver);
        basePage.goToPage(AboutUsPage.aboutUsPageURL());
        basePage.isCurrentPage(AboutUsPage.aboutUsPageURL());
    }

    @Test
    public void aboutUsHeaderIsPresent() {
        Assert.assertNotNull(aboutUsPage.getAboutUsHeader());
    }

    @Test
    public void aboutUsContentIsPresent() {
        Assert.assertNotNull(aboutUsPage.getAboutUsContent());
    }

    @Test
    public void aboutUsMapContainerIsPresent() {
        Assert.assertNotNull(aboutUsPage.getAboutUsMapContainer());
    }
}
