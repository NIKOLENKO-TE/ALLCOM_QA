package allcom_testng.footerTests;

import allcom_testng.TestBaseSE;
import allcom_testng.pages.BasePage;
import allcom_testng.pages.homePage.HomePage;
import allcom_testng.pages.footer.FooterPage;
import allcom_testng.pages.login.LoginPage;
import allcom_testng.pages.registration.RegisterPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FooterPageTests extends TestBaseSE {
    BasePage basePage;
    FooterPage footerPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        footerPage = new FooterPage(app.driver);
        basePage.goToPage(HomePage.homePageURL());
        basePage.isCurrentPage(HomePage.homePageURL(), true);
        basePage.scrollToBottom();
    }

    @Test
    public void clickFacebookLink() {
        basePage.testClickLink(footerPage.FACEBOOK(), FooterPage.FACEBOOK_URL());
    }

    @Test
    public void clickTwitterLink() {
        basePage.testClickLink(footerPage.TWITTER(), FooterPage.TWITTER_URL());
    }

    @Test
    public void clickInstagramLink() {
        basePage.testClickLink(footerPage.INSTAGRAM(), FooterPage.INSTAGRAM_URL());
    }

    @Test
    public void clickYoutubeLink() {
        basePage.testClickLink(footerPage.YOUTUBE(), FooterPage.YOUTUBE_URL());
    }

    @Test
    public void clickMyAccountLink() {
        basePage.testClickLink(footerPage.MY_ACCOUNT(), FooterPage.MY_ACCOUNT_URL());
    }

    @Test
    public void clickLoginLink() {
        basePage.testClickLink(footerPage.LOGIN(), LoginPage.loginPageURL());
    }

    @Test
    public void clickRegisterLink() {
        basePage.testClickLink(footerPage.REGISTER(), RegisterPage.registerPageURL());
    }

    @Test
    public void clickMyAuctionsLink() {
        basePage.testClickLink(footerPage.MY_AUCTIONS(), FooterPage.MY_AUCTIONS_URL());
    }

    @Test
    public void clickFavoritesAuctionsLink() {
        basePage.testClickLink(footerPage.FAVORITES_AUCTIONS(), FooterPage.FAVORITES_AUCTIONS_URL());
    }

    @Test
    public void clickAboutUsLink() {
        basePage.testClickLink(footerPage.ABOUT_US(), FooterPage.ABOUT_US_URL());
 }

    @Test
    public void clickContactLink() {
        basePage.testClickLink(footerPage.CONTACT_US(), FooterPage.CONTACT_US_URL());
    }

    @Test
    public void clickPortfolioLink() {
        basePage.testClickLink(footerPage.PORTFOLIO(), FooterPage.PORTFOLIO_URL());
    }

    @Test
    public void clickPrivacyPolicyLink() {
        basePage.testClickLink(footerPage.PRIVACY_POLICY(), FooterPage.PRIVACY_POLICY_URL());
    }

    @Test
    public void clickAllcomBottomLink() {
        basePage.testClickLink(footerPage.ALLCOM_GO_HOME_BOTTOM(), HomePage.homePageURL());
    }
}