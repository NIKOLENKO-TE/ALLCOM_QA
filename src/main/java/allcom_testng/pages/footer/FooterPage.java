package allcom_testng.pages.footer;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.homePage.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FooterPage extends BasePage {
    public FooterPage(WebDriver driver) {
        super(driver);
    }

    public static String FACEBOOK_URL() {
        return "https://www.facebook.com";
    }

    public static String TWITTER_URL() {
        return "https://twitter.com";
    }

    public static String INSTAGRAM_URL() {
        return "https://www.instagram.com";
    }

    public static String YOUTUBE_URL() {
        return "https://www.youtube.com";
    }

    public static String MY_ACCOUNT_URL() {
        return HomePage.homePageURL() + "/login";
    }

    public static String MY_AUCTIONS_URL() {
        return HomePage.homePageURL() + "/user/my_account/my_auctions";
    }

    public static String FAVORITES_AUCTIONS_URL() {
        return HomePage.homePageURL() + "/user/my_account/products";
    }

    public static String ABOUT_US_URL() {
        return HomePage.homePageURL() + "/about_us";
    }

    public static String CONTACT_US_URL() {
        return HomePage.homePageURL() + "/contact";
    }

    public static String PORTFOLIO_URL() {
        return HomePage.homePageURL() + "/portfolio";
    }

    public static String PRIVACY_POLICY_URL() {
        return HomePage.homePageURL() + "/privacy_policy";
    }

    public WebElement FACEBOOK() {
        return driver.findElement(By.xpath("//a[@data-testid='link_facebook']"));
    }

    public WebElement TWITTER() {
        return driver.findElement(By.xpath("//a[@data-testid='link_twitter']"));
    }

    public WebElement INSTAGRAM() {
        return driver.findElement(By.xpath("//a[@data-testid='link_instagram']"));
    }

    public WebElement YOUTUBE() {
        return driver.findElement(By.xpath("//a[@data-testid='link_youtube']"));
    }

    public WebElement MY_ACCOUNT() {
        return driver.findElement(By.xpath("//a[@data-testid='link_my_account']"));
    }

    public WebElement MY_AUCTIONS() {
        return driver.findElement(By.xpath("//a[@data-testid='link_my_auctions']"));
    }

    public WebElement LOGIN() {
        return driver.findElement(By.xpath("//a[@data-testid='link_login']"));
    }

    public WebElement REGISTER() {
        return driver.findElement(By.xpath("//a[@data-testid='link_register']"));
    }

    public WebElement FAVORITES_AUCTIONS() {
        return driver.findElement(By.xpath("//a[@data-testid='link_my_account_products']"));
    }

    public WebElement ABOUT_US() {
        return driver.findElement(By.xpath("//a[@data-testid='link_about_us']"));
    }

    public WebElement CONTACT_US() {
        return driver.findElement(By.xpath("//a[@data-testid='link_contact']"));
    }

    public WebElement PORTFOLIO() {
        return driver.findElement(By.xpath("//a[@data-testid='link_portfolio']"));
    }

    public WebElement PRIVACY_POLICY() {
        return driver.findElement(By.xpath("//a[@data-testid='link_privacy-policy']"));
    }

    public WebElement ALLCOM_GO_HOME_BOTTOM() {
        return driver.findElement(By.xpath("//a[@data-testid='link_Allcom_home']"));
    }
}