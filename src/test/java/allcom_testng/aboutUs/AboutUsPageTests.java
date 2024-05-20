package allcom_testng.aboutUs;

import allcom_testng.TestBaseSE;
import allcom_testng.pages.BasePage;
import allcom_testng.pages.aboutUs.AboutUsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AboutUsPageTests extends TestBaseSE {
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
