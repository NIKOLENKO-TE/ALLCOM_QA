package allcom_testng.pages;

import allcom_testng.pages.homePage.HomePage;
import com.google.common.io.Files;
import lombok.Getter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ApplicationManager extends BasePage {

    public BasePage basePage;
    public String browser;

    @Getter
    public WebDriver driver;
    private static final Duration WAIT_MILLIS_TIMEOUT = Duration.ofMillis(1000);
    private static final Duration WAIT_MILLIS_WAIT = Duration.ofMillis(500);

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
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(WAIT_MILLIS_TIMEOUT);
        driver.manage().timeouts().implicitlyWait(WAIT_MILLIS_WAIT);
        basePage.changeLanguage("English");
    }

    public void initHeadless() {
        String browserType = System.getProperty("browserType", "chrome");
        if (browserType.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-headless");
            driver = new FirefoxDriver(options);
        } else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1920x1080");
            driver = new ChromeDriver(options);
        }
        BasePage basePage = new BasePage(driver);
        driver.get(HomePage.homePageURL());
        driver.manage().timeouts().pageLoadTimeout(WAIT_MILLIS_TIMEOUT);
        driver.manage().timeouts().implicitlyWait(WAIT_MILLIS_WAIT);
        basePage.changeLanguage("English");
    }

    public void stop() {
        if (driver != null) {
            driver.quit();
        }
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
        } catch (IOException ignored) {
        }
    }
    public String takeScreenshot() {
        String time = String.valueOf(System.nanoTime());
        String shortTime = time.substring(0, 6);
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("src/screenshots/screenshot_error_" + shortTime + ".png");
        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return screenshot.getAbsolutePath();
    }
}