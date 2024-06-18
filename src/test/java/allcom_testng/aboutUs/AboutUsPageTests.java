package allcom_testng.aboutUs;

import allcom_testng.TestBaseSE;
import allcom_testng.pages.BasePage;
import allcom_testng.pages.aboutUs.AboutUsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static allcom_testng.pages.aboutUs.AboutUsPage.ABOUT_US_PAGE_URL;
import static org.testng.Assert.assertNotNull;

public class AboutUsPageTests extends TestBaseSE {
  private AboutUsPage aboutUsPage;

  @BeforeMethod
  public void precondition() {
    BasePage basePage = new BasePage(app.driver);
    aboutUsPage = new AboutUsPage(app.driver);
    basePage.goToPage(ABOUT_US_PAGE_URL);
    basePage.isCurrentPage(ABOUT_US_PAGE_URL, true);
  }

  @Test
  public void aboutUsHeaderIsPresent() {
   assertNotNull(aboutUsPage.getAboutUsHeader(), "AboutUsHeader is not present");
  }

  @Test
  public void aboutUsContentIsPresent() {
    assertNotNull(aboutUsPage.getAboutUsContent(), "AboutUsContent is not present");
  }

  @Test
  public void aboutUsMapContainerIsPresent() {
    assertNotNull(aboutUsPage.getAboutUsMapContainer(), "AboutUsMapContainer is not present");
  }
}
