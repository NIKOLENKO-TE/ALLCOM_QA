package allcom_selenium.header;

import allcom_selenium.TestBaseSE;
import allcom_selenium.pages.BasePage;
import allcom_selenium.pages.header.AllcomLogo;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static allcom_selenium.pages.homePage.HomePage.HOME_PAGE_URL;
import static allcom_selenium.pages.login.LoginPage.LOGIN_PAGE_URL;

public class AllcomLogoTests extends TestBaseSE {
    private AllcomLogo allcomLogo;

    BasePage basePage = new BasePage(driver);

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(driver);
        allcomLogo = new AllcomLogo(driver);
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

