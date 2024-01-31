package allcom.tests.footerPageTests;

import allcom.pages.BasePage;
import allcom.pages.HomePage;
import allcom.pages.footer.FooterPage;
import allcom.pages.login.LoginPage;
import allcom.pages.registration.RegisterPage;
import allcom.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FooterPageTests extends TestBase {
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
        basePage.clickLinks(footerPage.MY_ACCOUNT());
        basePage.isCurrentPage(HomePage.homePageURL() + FooterPage.MY_ACCOUNT_URL(), true);
    }

    @Test
    public void clickLoginLink() {
        basePage.clickLinks(footerPage.LOGIN());
        basePage.isCurrentPage(LoginPage.loginPageURL(), true);
    }

    @Test
    public void clickRegisterLink() {
        basePage.clickLinks(footerPage.REGISTER());
        basePage.isCurrentPage(RegisterPage.registerPageURL(), true);
    }

    @Test
    public void clickMyAuctionsLink() {
        basePage.clickLinks(footerPage.MY_AUCTIONS());
        basePage.isCurrentPage(HomePage.homePageURL() + FooterPage.MY_AUCTIONS_URL(), true);
    }

    @Test
    public void clickFavoritesAuctionsLink() {
        basePage.clickLinks(footerPage.FAVORITES_AUCTIONS());
        basePage.isCurrentPage(HomePage.homePageURL() + FooterPage.FAVORITES_AUCTIONS_URL(), true);
    }

    @Test
    public void clickAboutUsLink() {
        basePage.clickLinks(footerPage.ABOUT_US());
        basePage.isCurrentPage(HomePage.homePageURL() + FooterPage.ABOUT_US_URL(), true);
    }

    @Test
    public void clickContactLink() {
        basePage.clickLinks(footerPage.CONTACT_US());
        basePage.isCurrentPage(HomePage.homePageURL() + FooterPage.CONTACT_US_URL(), true);
    }

    @Test
    public void clickPortfolioLink() {
        basePage.clickLinks(footerPage.PORTFOLIO());
        basePage.isCurrentPage(HomePage.homePageURL() + FooterPage.PORTFOLIO_URL(), true);
    }

    @Test
    public void clickPrivacyPolicyLink() {
        basePage.clickLinks(footerPage.PRIVACY_POLICY());
        basePage.isCurrentPage(HomePage.homePageURL() + FooterPage.PRIVACY_POLICY_URL(), true);
    }

    @Test
    public void clickAllcomBottomLink() {
        basePage.clickLinks(footerPage.ALLCOM_GO_HOME_BOTTOM());
        basePage.isCurrentPage(HomePage.homePageURL(), true);
    }
}