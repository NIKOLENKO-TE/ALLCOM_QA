package allcom.tests.header;

import allcom.pages.BasePage;
import allcom.pages.HomePage;
import allcom.pages.header.HeaderAccount;
import allcom.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class HeaderAccountTests extends TestBase {
    private BasePage basePage;
    private HeaderAccount headerAccount;
    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(driver);
        headerAccount = new HeaderAccount(driver);
        basePage.goToPage(HomePage.homePageURL());
        basePage.isCurrentPage(HomePage.homePageURL());
    }
    @Test
    public void myAccountTopIsPresent() {
        Assert.assertNotNull(headerAccount.getMyAccountTop());
    }

    @Test
    public void wishlistTopIsPresent() {
        Assert.assertNotNull(headerAccount.getWishlistTop());
    }

    @Test
    public void cartTopIsPresent() {
        Assert.assertNotNull(headerAccount.getCartTop());
    }

    @Test
    public void clickMyAccountTopNavigatesToCorrectPage() {
        Assert.assertNotNull(headerAccount.getMyAccountTop());
        headerAccount.getMyAccountTop().click();
        basePage.isCurrentPage(HomePage.homePageURL()+ "/user/my_account/about_me");
    }

    @Test
    public void clickWishlistTopNavigatesToCorrectPage() {
        Assert.assertNotNull(headerAccount.getWishlistTop());
        headerAccount.getWishlistTop().click();
        basePage.isCurrentPage(HomePage.homePageURL() + "/user/my_account/products");
    }
    @Test
    public void clickCartTopNavigatesToCorrectPage() {
        Assert.assertNotNull(headerAccount.getCartTop());
        headerAccount.getCartTop().click();
        basePage.isCurrentPage(HomePage.homePageURL() + "/user/my_account/my_auctions");
    }
}

