package allcom_testng.header;

import allcom_testng.TestBaseSE;
import allcom_testng.pages.BasePage;
import allcom_testng.pages.homePage.HomePage;
import allcom_testng.pages.header.AllcomLogo;
import allcom_testng.pages.login.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AllcomLogoTests extends TestBaseSE {
    private AllcomLogo allcomLogo;

    BasePage basePage = new BasePage(app.driver);

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        allcomLogo = new AllcomLogo(app.driver);
        basePage.goToPage(HomePage.homePageURL());
        basePage.isCurrentPage(HomePage.homePageURL(), true);
    }

    @Test
    public void allcomLogoIsPresent() {
        basePage.isElementPresent(allcomLogo.getAllcomLogo(), true);
    }
    @Test
    public void allcomLogoIsClickable() {
        basePage.isElementClickable(allcomLogo.getAllcomLogo(), true);
    }
    @Test
    public void clickAllcomLogoNavigatesToHomePage() {
        basePage.goToPage(LoginPage.loginPageURL());
        basePage.isElementPresent(allcomLogo.getAllcomLogo(), true);
        basePage.isElementClickable(allcomLogo.getAllcomLogo(), true);
        basePage.click(allcomLogo.getAllcomLogo());
        basePage.waitUntilUrlToBe(HomePage.homePageURL());
        basePage.isCurrentPage(HomePage.homePageURL(), true);
    }
}

