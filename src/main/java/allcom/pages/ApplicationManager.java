package allcom.pages;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.Duration;

public class ApplicationManager extends BasePage {
    private static final Logger logger = LogManager.getLogger(ApplicationManager.class);

    public void init() {
        driver = new ChromeDriver();
        BasePage basePage = new BasePage(driver);
        driver.get(HomePage.homePageURL());
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
        } catch (IOException e) {
            logger.error("An error occurred while stopping the driver", e);
        }
    }
}