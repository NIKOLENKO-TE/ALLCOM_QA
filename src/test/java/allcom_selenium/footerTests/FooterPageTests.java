package allcom_selenium.footerTests;

import allcom_selenium.links.BrokenLinksTests;
import allcom_selenium.TestBaseSE;
import allcom_selenium.pages.BasePage;
import allcom_selenium.pages.footer.FooterPage;
import allcom_selenium.pages.login.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static allcom_selenium.pages.footer.FooterPage.*;
import static allcom_selenium.pages.homePage.HomePage.HOME_PAGE_URL;
import static allcom_selenium.pages.login.LoginPage.LOGIN_PAGE_URL;
import static allcom_selenium.pages.registration.RegisterPage.REGISTER_PAGE_URL;

public class FooterPageTests extends TestBaseSE {
  BasePage basePage;
  LoginPage loginPage;
  FooterPage footerPage;
  BrokenLinksTests brokenLinksTests;

  @BeforeMethod
  public void precondition() {
    loginPage = new LoginPage(driver);
    basePage = new BasePage(driver);
    footerPage = new FooterPage(driver);
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
    checkLocatorLink(driver, FACEBOOK_XPATH);
  }

  @Test
  public void clickTwitterLink() {
    basePage.testClickLink(footerPage.TWITTER(), TWITTER_URL);
  }

  @Test
  public void clickTwitterLocator() {
    checkLocatorLink(driver, TWITTER_XPATH);
  }

  @Test
  public void clickInstagramLink() {
    basePage.testClickLink(footerPage.INSTAGRAM(), INSTAGRAM_URL);
  }

  @Test
  public void clickInstagramLocator() {
    checkLocatorLink(driver, INSTAGRAM_XPATH);
  }

  @Test
  public void clickYoutubeLink() {
    basePage.testClickLink(footerPage.YOUTUBE(), YOUTUBE_URL);
  }

  @Test
  public void clickYoutubeLocator() {
    checkLocatorLink(driver, YOUTUBE_XPATH);
  }

  @Test
  public void clickLoginLink() {
    basePage.testClickLink(footerPage.LOGIN(), LOGIN_PAGE_URL);
  }

  @Test
  public void clickLoginLocator() {
    checkLocatorLink(driver, LOGIN_XPATH);
  }

  @Test
  public void clickRegisterLink() {
    basePage.testClickLink(footerPage.REGISTER(), REGISTER_PAGE_URL);
  }

  @Test
  public void clickRegisterLocator() {
    checkLocatorLink(driver, REGISTER_XPATH);
  }

  @Test
  public void clickMyAuctionsLink() {
    basePage.testClickLink(footerPage.MY_AUCTIONS(), MY_AUCTIONS_URL);
  }

  @Test
  public void clickMyAuctionsLocator() {
    checkLocatorLink(driver, MY_AUCTIONS_XPATH);
  }

  @Test
  public void clickFavoritesAuctionsLink() {
    basePage.testClickLink(footerPage.FAVORITES_AUCTIONS(), FAVORITES_AUCTIONS_URL);
  }

  @Test
  public void clickFavoritesAuctionsLocator() {
    checkLocatorLink(driver, FAVORITES_AUCTIONS_XPATH);
  }

  @Test
  public void clickAboutUsLink() {
    basePage.testClickLink(footerPage.ABOUT_US(), ABOUT_US_URL);
  }

  @Test
  public void clickAboutUsLocator() {
    checkLocatorLink(driver, ABOUT_US_XPATH);
  }

  @Test
  public void clickContactLink() {
    basePage.testClickLink(footerPage.CONTACT_US(), CONTACT_US_URL);
  }

  @Test
  public void clickContactLocator() {
    checkLocatorLink(driver, CONTACT_US_XPATH);
  }

  @Test
  public void clickPortfolioLink() {
    basePage.testClickLink(footerPage.PORTFOLIO(), PORTFOLIO_URL);
  }

  @Test
  public void clickPortfolioLocator() {
    checkLocatorLink(driver, PORTFOLIO_XPATH);
  }

  @Test
  public void clickPrivacyPolicyLink() {
    basePage.testClickLink(footerPage.PRIVACY_POLICY(), PRIVACY_POLICY_URL);
  }
  @Test
  public void clickPrivacyPolicyLocator() {
    checkLocatorLink(driver, PRIVACY_POLICY_XPATH);
  }

  @Test
  public void clickAllComBottomLink() {
    basePage.testClickLink(footerPage.ALLCOM_GO_HOME_BOTTOM(), HOME_PAGE_URL);
  }

  @Test
  public void clickAllComBottomLocator() {
    checkLocatorLink(driver, ALLCOM_GO_HOME_BOTTOM_XPATH);
  }
}