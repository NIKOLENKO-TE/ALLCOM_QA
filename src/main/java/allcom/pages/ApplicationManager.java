package allcom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;

import java.io.IOException;
import java.time.Duration;

public class ApplicationManager extends BasePage {

    public BasePage basePage;
    public String browser;

    public  WebDriver driver;

    public static ApplicationManager app = new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        driver.get(HomePage.homePageURL());
        BasePage basePage = new BasePage(driver);
        basePage.changeLanguage("English");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public void initHeadless() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1920x1080");
        driver = new ChromeDriver(options);
        BasePage basePage = new BasePage(driver);
        driver.get(HomePage.homePageURL());
        basePage.changeLanguage("English");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void stop() {
        driver.quit();
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
        } catch (IOException ignored) {
        }
    }
}
