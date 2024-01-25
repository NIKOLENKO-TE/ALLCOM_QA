package allcom.tests;

import allcom.pages.BasePage;
import allcom.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {
    protected WebDriver driver;

//    @BeforeMethod
//    public void init() {
//        driver = new ChromeDriver();
//        BasePage basePage = new BasePage(driver);
//        driver.get(HomePage.homePageURL());
//        basePage.changeLanguage("English");
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
//    }

    @BeforeMethod
    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1920x1080");
        driver = new ChromeDriver(options);
        BasePage basePage = new BasePage(driver);
        driver.get(HomePage.homePageURL());
        basePage.changeLanguage("English");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
