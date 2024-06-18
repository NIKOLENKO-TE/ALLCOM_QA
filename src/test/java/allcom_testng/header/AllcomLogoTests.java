package allcom_testng.header;

import allcom_testng.TestBaseSE;
import allcom_testng.pages.BasePage;
import allcom_testng.pages.homePage.HomePage;
import allcom_testng.pages.header.AllcomLogo;
import allcom_testng.pages.login.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static allcom_testng.pages.homePage.HomePage.HOME_PAGE_URL;
import static allcom_testng.pages.login.LoginPage.LOGIN_PAGE_URL;

public class AllcomLogoTests extends TestBaseSE {
    private AllcomLogo allcomLogo;

    BasePage basePage = new BasePage(app.driver);

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        allcomLogo = new AllcomLogo(app.driver);
        basePage.goToPage(HOME_PAGE_URL);
        basePage.isCurrentPage(HOME_PAGE_URL, true);
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
        basePage.goToPage(LOGIN_PAGE_URL);
        basePage.isElementPresent(allcomLogo.getAllcomLogo(), true);
        basePage.isElementClickable(allcomLogo.getAllcomLogo(), true);
        basePage.click(allcomLogo.getAllcomLogo());
        basePage.waitUntilUrlToBe(HOME_PAGE_URL);
        basePage.isCurrentPage(HOME_PAGE_URL, true);
    }
}

