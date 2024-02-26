package allcom_testng.tests.header;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.HomePage;
import allcom_testng.pages.header.HeaderAccount;
import allcom_testng.pages.login.LoginPage;
import allcom_testng.tests.TestBase;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HeaderAccountTests extends TestBase {
    private BasePage basePage;
    private LoginPage loginPage;
    private HeaderAccount headerAccount;
    private static final Duration WAIT_SECONDS = Duration.ofSeconds(10);

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        loginPage = new LoginPage(app.driver);
        headerAccount = new HeaderAccount(app.driver);
        basePage.goToPage(LoginPage.loginPageURL());
        basePage.isCurrentPage(LoginPage.loginPageURL(), true);
        try {
            loginPage.loginAdmin();
        } catch (StaleElementReferenceException e) {
            new WebDriverWait(app.driver, WAIT_SECONDS)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions
                            .elementToBeClickable(loginPage.signInButtonText));
            loginPage.loginAdmin();
        }
    }

    @Test
    public void myAccountTopIsPresent() {
        Assert.assertNotNull(headerAccount.getMyAccountTop(), "MyAccountTop is not present");
    }

    @Test
    public void wishlistTopIsPresent() {
        Assert.assertNotNull(headerAccount.getWishlistTop(), "WishlistTop is not present");
    }

    @Test
    public void cartTopIsPresent() {
        Assert.assertNotNull(headerAccount.getCartTop(), "CartTop is not present");
    }

    @Test
    public void clickMyAccountTopNavigatesToCorrectPage() {
        Assert.assertNotNull(headerAccount.getMyAccountTop(), "MyAccountTop is not present");
        headerAccount.clickMyAccountTop();
        basePage.isCurrentPage(LoginPage.myAccountPageURL(), true);
    }

    @Test
    public void clickWishlistTopNavigatesToCorrectPage() {
        Assert.assertNotNull(headerAccount.getWishlistTop(), "WishlistTop is not present");
        headerAccount.clickWishlistTop();
        basePage.isCurrentPage(HomePage.homePageURL() + "/user/my_account/products", true);
    }

    @Test
    public void clickCartTopNavigatesToCorrectPage() {
        Assert.assertNotNull(headerAccount.getCartTop(), "Cart top is not present");
        headerAccount.clickCartTop();
        basePage.isCurrentPage(HomePage.homePageURL() + "/user/my_account/my_auctions", true);
    }
}

