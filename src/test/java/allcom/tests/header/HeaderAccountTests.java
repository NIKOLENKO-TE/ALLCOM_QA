package allcom.tests.header;

import allcom.pages.BasePage;
import allcom.pages.HomePage;
import allcom.pages.header.HeaderAccount;
import allcom.pages.login.LoginPage;
import allcom.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HeaderAccountTests extends TestBase {
    private BasePage basePage;
    private HeaderAccount headerAccount;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        headerAccount = new HeaderAccount(app.driver);
        basePage.goToPage(HomePage.homePageURL());
        basePage.isCurrentPage(HomePage.homePageURL(), true);
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
        Assert.assertNotNull(headerAccount.getMyAccountTop(), "My account top is not present");
        headerAccount.getMyAccountTop().click();
        basePage.isCurrentPage(LoginPage.loginPageURL(), true);
    }

    @Test
    public void clickWishlistTopNavigatesToCorrectPage() {
        Assert.assertNotNull(headerAccount.getWishlistTop(), "WishListTop is not present");
        headerAccount.getWishlistTop().click();
        basePage.isCurrentPage(HomePage.homePageURL() + "/user/my_account/products", true);
    }

    @Test
    public void clickCartTopNavigatesToCorrectPage() {
        Assert.assertNotNull(headerAccount.getCartTop(), "CartTop is not present");
        headerAccount.getCartTop().click();
        basePage.isCurrentPage(HomePage.homePageURL() + "/user/my_account/my_auctions", true);
    }
}

