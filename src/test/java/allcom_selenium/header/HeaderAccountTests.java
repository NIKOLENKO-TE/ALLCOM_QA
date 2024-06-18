package allcom_selenium.header;

import allcom_selenium.TestBaseSE;
import allcom_selenium.pages.BasePage;
import allcom_selenium.pages.header.HeaderAccount;
import allcom_selenium.pages.login.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static allcom_selenium.pages.footer.FooterPage.FAVORITES_AUCTIONS_URL;
import static allcom_selenium.pages.footer.FooterPage.MY_AUCTIONS_URL;
import static allcom_selenium.pages.login.LoginPage.LOGIN_PAGE_URL;
import static allcom_selenium.pages.login.LoginPage.MY_ACCOUNT_PAGE_URL;

public class HeaderAccountTests extends TestBaseSE {
    BasePage basePage = new BasePage(driver);
    LoginPage loginPage = new LoginPage(driver);
    private HeaderAccount headerAccount;
    private static final Duration WAIT_SECONDS = Duration.ofSeconds(5);

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        headerAccount = new HeaderAccount(driver);
        basePage.goToPage(LOGIN_PAGE_URL);
        basePage.isCurrentPage(LOGIN_PAGE_URL, true);
        loginPage.loginAdmin();
        basePage.waitForSpinnerStop();
    }

    @Test(priority = 1)
    public void myAccountTopIsPresent() {
        basePage.isElementPresent(headerAccount.myAccountTopAfterLoggedIn(), true);
    }

    @Test(priority = 2, invocationCount = 3)
    public void wishlistTopIsPresent() {
        loginPage.isUserLoggedIn(true);
        basePage.waitUntilElementToBeClickable(headerAccount.wishlistTop());
        basePage.isElementPresent(headerAccount.wishlistTop(), true);
    }

    @Test(priority = 3)
    public void cartTopIsPresent() {
        basePage.waitUntilElementToBeClickable(headerAccount.cartTop());
        basePage.isElementClickable(headerAccount.cartTop(), true);
    }

    @Test(priority = 4)
    public void clickMyAccountTopNavigatesToCorrectPage() {
        basePage.click(headerAccount.myAccountTopAfterLoggedIn());
        basePage.isCurrentPage(MY_ACCOUNT_PAGE_URL, true);
    }

    @Test(priority = 5)
    public void clickWishlistTopNavigatesToCorrectPage() {
        basePage.click(headerAccount.wishlistTop());
        basePage.isCurrentPage(FAVORITES_AUCTIONS_URL, true);
    }

    @Test(priority = 6)
    public void clickCartTopNavigatesToCorrectPage() {
        basePage.click(headerAccount.cartTop());
        basePage.isCurrentPage(MY_AUCTIONS_URL, true);

    }

    @Test(priority = 7, invocationCount = 3)
    public void clickMyAccountTopBeforeLoggedInNavigatesToCorrectPage() {
        loginPage.isUserLoggedIn(true);
        loginPage.userLoggedOut();
        loginPage.isUserLoggedIn(false);
        basePage.clickJSExecutor(headerAccount.myAccountTopBeforeLoggedIn());
        basePage.isCurrentPage(LOGIN_PAGE_URL, true);
        loginPage.isUserLoggedIn(false);
    }
    @AfterMethod
    public void postcondition() {
        loginPage.userLoggedOut();
    }

}

