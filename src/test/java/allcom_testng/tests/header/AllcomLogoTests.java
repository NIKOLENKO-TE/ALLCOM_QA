package allcom_testng.tests.header;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.HomePage;
import allcom_testng.pages.header.AllcomLogo;
import allcom_testng.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AllcomLogoTests extends TestBase {
    private BasePage basePage;
    private AllcomLogo allcomLogo;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        allcomLogo = new AllcomLogo(app.driver);
        basePage.goToPage(HomePage.homePageURL());
        basePage.isCurrentPage(HomePage.homePageURL(), true);
    }

    @Test
    public void allcomLogoIsPresent() {
        Assert.assertNotNull(allcomLogo.getAllcomLogo(), "Allcom logo is not present");
    }

    @Test
    public void clickAllcomLogoNavigatesToHomePage() {
        basePage.goToPage(HomePage.homePageURL() + "/login");
        Assert.assertNotNull(allcomLogo.getAllcomLogo(), "Allcom logo is not present");
        allcomLogo.getAllcomLogo().click();
        basePage.isCurrentPage(HomePage.homePageURL(), true);
    }
}

