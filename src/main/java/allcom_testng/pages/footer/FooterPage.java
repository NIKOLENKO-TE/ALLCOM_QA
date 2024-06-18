package allcom_testng.pages.footer;

import allcom_testng.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static allcom_testng.pages.homePage.HomePage.HOME_PAGE_URL;

public class FooterPage extends BasePage {
  public FooterPage(WebDriver driver) {
    super(driver);
  }

  public static final String FACEBOOK_URL = "https://www.facebook.com";
  public static final String TWITTER_URL = "https://x.com/";
  public static final String INSTAGRAM_URL = "https://www.instagram.com";
  public static final String YOUTUBE_URL = "https://www.youtube.com";

  public static final String MY_AUCTIONS_URL = HOME_PAGE_URL + "/user/my_account/my_auctions";
  public static final String FAVORITES_AUCTIONS_URL = HOME_PAGE_URL + "/user/my_account/products";
  public static final String ABOUT_US_URL = HOME_PAGE_URL + "/about_us";
  public static final String CONTACT_US_URL = HOME_PAGE_URL + "/contact";
  public static final String PORTFOLIO_URL = HOME_PAGE_URL + "/portfolio";
  public static final String PRIVACY_POLICY_URL = HOME_PAGE_URL + "/privacy_policy";

  public static final By FACEBOOK_XPATH = By.xpath("//a[@data-testid='link_facebook']");
  public static final By TWITTER_XPATH = By.xpath("//a[@data-testid='link_twitter']");
  public static final By INSTAGRAM_XPATH = By.xpath("//a[@data-testid='link_instagram']");
  public static final By YOUTUBE_XPATH = By.xpath("//a[@data-testid='link_youtube']");
  public static final By MY_AUCTIONS_XPATH = By.xpath("//a[@data-testid='link_my_auctions']");
  public static final By LOGIN_XPATH = By.xpath("//a[@data-testid='link_login']");
  public static final By REGISTER_XPATH = By.xpath("//a[@data-testid='link_register']");
  public static final By FAVORITES_AUCTIONS_XPATH = By.xpath("//a[@data-testid='link_my_account_products']");
  public static final By ABOUT_US_XPATH = By.xpath("//a[@data-testid='link_about_us']");
  public static final By CONTACT_US_XPATH = By.xpath("//a[@data-testid='link_contact']");
  public static final By PORTFOLIO_XPATH = By.xpath("//a[@data-testid='link_portfolio']");
  public static final By PRIVACY_POLICY_XPATH = By.xpath("//a[@data-testid='link_privacy-policy']");
  public static final By ALLCOM_GO_HOME_BOTTOM_XPATH = By.xpath("//a[@data-testid='link_Allcom_home']");

  public WebElement FACEBOOK() {
    return driver.findElement(FACEBOOK_XPATH);
  }

  public WebElement TWITTER() {
    return driver.findElement(TWITTER_XPATH);
  }

  public WebElement INSTAGRAM() {
    return driver.findElement(INSTAGRAM_XPATH);
  }

  public WebElement YOUTUBE() {
    return driver.findElement(YOUTUBE_XPATH);
  }


  public WebElement MY_AUCTIONS() {
    return driver.findElement(MY_AUCTIONS_XPATH);
  }

  public WebElement LOGIN() {
    return driver.findElement(LOGIN_XPATH);
  }

  public WebElement REGISTER() {
    return driver.findElement(REGISTER_XPATH);
  }

  public WebElement FAVORITES_AUCTIONS() {
    return driver.findElement(FAVORITES_AUCTIONS_XPATH);
  }

  public WebElement ABOUT_US() {
    return driver.findElement(ABOUT_US_XPATH);
  }

  public WebElement CONTACT_US() {
    return driver.findElement(CONTACT_US_XPATH);
  }

  public WebElement PORTFOLIO() {
    return driver.findElement(PORTFOLIO_XPATH);
  }

  public WebElement PRIVACY_POLICY() {
    return driver.findElement(PRIVACY_POLICY_XPATH);
  }

  public WebElement ALLCOM_GO_HOME_BOTTOM() {
    return driver.findElement(ALLCOM_GO_HOME_BOTTOM_XPATH);
  }
}