package allcom.tests;
import java.time.Duration;

import allcom.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import allcom.pages.HomePage;

public class TestBase {
    WebDriver driver;
    private BasePage basePage;
    @BeforeMethod
    public void init() {
        driver = new ChromeDriver();
        basePage = new BasePage(driver);
        driver.get(HomePage.homePageURL());
        basePage.changeLanguage("English");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }
//@BeforeMethod
//public void init() {
//    ChromeOptions chromeOptions = new ChromeOptions();
//    chromeOptions.addArguments("--headless");
//    chromeOptions.addArguments("--disable-gpu");
//    chromeOptions.addArguments("--window-size=1920,1080");
//    chromeOptions.addArguments("--ignore-certificate-errors");
//    chromeOptions.addArguments("--silent");
//    chromeOptions.addArguments("--disable-logging");
//    chromeOptions.addArguments("--disable-notifications");
//    chromeOptions.addArguments("--disable-popup-blocking");
//    chromeOptions.addArguments("--disable-extensions");
//    chromeOptions.addArguments("--no-sandbox");
//    driver = new ChromeDriver(chromeOptions);
//    basePage = new BasePage(driver);
//    driver.get(HomePage.homePageURL());
//    basePage.changeLanguage("English");
//    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//}
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
