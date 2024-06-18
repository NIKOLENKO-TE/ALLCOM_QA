package allcom_testng.footerTests;

import allcom_testng.BrokenLinksTests;
import allcom_testng.TestBaseSE;
import allcom_testng.pages.BasePage;
import allcom_testng.pages.footer.FooterPage;
import allcom_testng.pages.login.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static allcom_testng.pages.footer.FooterPage.*;
import static allcom_testng.pages.homePage.HomePage.HOME_PAGE_URL;
import static allcom_testng.pages.login.LoginPage.LOGIN_PAGE_URL;
import static allcom_testng.pages.login.LoginPage.MY_ACCOUNT_PAGE_URL;
import static allcom_testng.pages.registration.RegisterPage.REGISTER_PAGE_URL;

public class FooterPageTests extends TestBaseSE {
  BasePage basePage;
  LoginPage loginPage;
  FooterPage footerPage;
  BrokenLinksTests brokenLinksTests;

  @BeforeMethod
  public void precondition() {
    loginPage = new LoginPage(app.driver);
    basePage = new BasePage(app.driver);
    footerPage = new FooterPage(app.driver);
    brokenLinksTests = new BrokenLinksTests();
    basePage.goToPage(HOME_PAGE_URL);
    basePage.isCurrentPage(HOME_PAGE_URL, true);
    basePage.scrollToBottom();
  }

  @Test
  public void clickFacebookLink() {
    basePage.testClickLink(footerPage.FACEBOOK(), FACEBOOK_URL);
  }

  @Test
  public void clickFacebookLocator() {
    checkLocatorLink(app.driver, FACEBOOK_XPATH);
  }

  @Test
  public void clickTwitterLink() {
    basePage.testClickLink(footerPage.TWITTER(), TWITTER_URL);
  }

  @Test
  public void clickTwitterLocator() {
    checkLocatorLink(app.driver, TWITTER_XPATH);
  }

  @Test
  public void clickInstagramLink() {
    basePage.testClickLink(footerPage.INSTAGRAM(), INSTAGRAM_URL);
  }

  @Test
  public void clickInstagramLocator() {
    checkLocatorLink(app.driver, INSTAGRAM_XPATH);
  }

  @Test
  public void clickYoutubeLink() {
    basePage.testClickLink(footerPage.YOUTUBE(), YOUTUBE_URL);
  }

  @Test
  public void clickYoutubeLocator() {
    checkLocatorLink(app.driver, YOUTUBE_XPATH);
  }

  @Test
  public void clickLoginLink() {
    basePage.testClickLink(footerPage.LOGIN(), LOGIN_PAGE_URL);
  }

  @Test
  public void clickLoginLocator() {
    checkLocatorLink(app.driver, LOGIN_XPATH);
  }

  @Test
  public void clickRegisterLink() {
    basePage.testClickLink(footerPage.REGISTER(), REGISTER_PAGE_URL);
  }

  @Test
  public void clickRegisterLocator() {
    checkLocatorLink(app.driver, REGISTER_XPATH);
  }

  @Test
  public void clickMyAuctionsLink() {
    basePage.testClickLink(footerPage.MY_AUCTIONS(), MY_AUCTIONS_URL);
  }

  @Test
  public void clickMyAuctionsLocator() {
    checkLocatorLink(app.driver, MY_AUCTIONS_XPATH);
  }

  @Test
  public void clickFavoritesAuctionsLink() {
    basePage.testClickLink(footerPage.FAVORITES_AUCTIONS(), FAVORITES_AUCTIONS_URL);
  }

  @Test
  public void clickFavoritesAuctionsLocator() {
    checkLocatorLink(app.driver, FAVORITES_AUCTIONS_XPATH);
  }

  @Test
  public void clickAboutUsLink() {
    basePage.testClickLink(footerPage.ABOUT_US(), ABOUT_US_URL);
  }

  @Test
  public void clickAboutUsLocator() {
    checkLocatorLink(app.driver, ABOUT_US_XPATH);
  }

  @Test
  public void clickContactLink() {
    basePage.testClickLink(footerPage.CONTACT_US(), CONTACT_US_URL);
  }

  @Test
  public void clickContactLocator() {
    checkLocatorLink(app.driver, CONTACT_US_XPATH);
  }

  @Test
  public void clickPortfolioLink() {
    basePage.testClickLink(footerPage.PORTFOLIO(), PORTFOLIO_URL);
  }

  @Test
  public void clickPortfolioLocator() {
    checkLocatorLink(app.driver, PORTFOLIO_XPATH);
  }

  @Test
  public void clickPrivacyPolicyLink() {
    basePage.testClickLink(footerPage.PRIVACY_POLICY(), PRIVACY_POLICY_URL);
  }

  @Test
  public void clickPrivacyPolicyLocator() {
    checkLocatorLink(app.driver, PRIVACY_POLICY_XPATH);
  }

  @Test
  public void clickAllComBottomLink() {
    basePage.testClickLink(footerPage.ALLCOM_GO_HOME_BOTTOM(), HOME_PAGE_URL);
  }

  @Test
  public void clickAllComBottomLocator() {
    checkLocatorLink(app.driver, ALLCOM_GO_HOME_BOTTOM_XPATH);
  }
}