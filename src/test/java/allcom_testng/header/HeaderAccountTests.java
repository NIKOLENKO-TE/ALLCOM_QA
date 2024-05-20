package allcom_testng.header;

import allcom_testng.TestBaseSE;
import allcom_testng.pages.BasePage;
import allcom_testng.pages.footer.FooterPage;
import allcom_testng.pages.header.HeaderAccount;
import allcom_testng.pages.login.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HeaderAccountTests extends TestBaseSE {
    BasePage basePage = new BasePage(app.driver);
    LoginPage loginPage = new LoginPage(app.driver);
    private HeaderAccount headerAccount;
    private static final Duration WAIT_SECONDS = Duration.ofSeconds(5);

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        loginPage = new LoginPage(app.driver);
        headerAccount = new HeaderAccount(app.driver);
        driver.get(LoginPage.loginPageURL());
        //basePage.goToPage(LoginPage.loginPageURL());
        basePage.isCurrentPage(LoginPage.loginPageURL(), true);
        loginPage.loginAdmin();
        basePage.waitForSpinnerStop();
        System.out.println("***************************************************");
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
        basePage.isCurrentPage(LoginPage.myAccountPageURL(), true);
    }

    @Test(priority = 5)
    public void clickWishlistTopNavigatesToCorrectPage() {
        basePage.click(headerAccount.wishlistTop());
        basePage.isCurrentPage(FooterPage.FAVORITES_AUCTIONS_URL(), true);
    }

    @Test(priority = 6)
    public void clickCartTopNavigatesToCorrectPage() {
        basePage.click(headerAccount.cartTop());
        basePage.isCurrentPage(FooterPage.MY_AUCTIONS_URL(), true);

    }

    @Test(priority = 7, invocationCount = 3)
    public void clickMyAccountTopBeforeLoggedInNavigatesToCorrectPage() {
        loginPage.isUserLoggedIn(true);
        loginPage.userLoggedOut();
        loginPage.isUserLoggedIn(false);
        basePage.clickJSExecutor(headerAccount.myAccountTopBeforeLoggedIn());
        basePage.isCurrentPage(LoginPage.loginPageURL(), true);
        loginPage.isUserLoggedIn(false);
    }
    @AfterMethod
    public void postcondition() {
        loginPage.userLoggedOut();
    }
}

