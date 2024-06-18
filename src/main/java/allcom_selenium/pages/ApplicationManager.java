package allcom_selenium.pages;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static allcom_selenium.pages.homePage.HomePage.HOME_PAGE_URL;

public class ApplicationManager extends BasePage {

  private WebDriver driver;
  private static final Duration WAIT_MILLIS_WAIT = Duration.ofMillis(100);
  private final String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public WebDriver getDriver() {
    System.out.println("\033[32m" + "Driver: " + driver + "\033[0m");
    if (driver == null) {
      throw new IllegalStateException("Driver not initialized. Call init() or initHeadless() first.");
    }
    return driver;
  }

  public void init() {
    if (browser.equalsIgnoreCase("chrome")) {
      driver = new ChromeDriver();
    } else if (browser.equalsIgnoreCase("firefox")) {
      driver = new FirefoxDriver();
    } else if (browser.equalsIgnoreCase("edge")) {
      System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
      driver = new EdgeDriver();
    } else {
      throw new IllegalArgumentException("Unsupported browser: " + browser);
    }
    driver.get(HOME_PAGE_URL);
    BasePage basePage = new BasePage(driver);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(WAIT_MILLIS_WAIT);
    basePage.changeLanguage("English");
  }

  public void initHeadless() {
    if (browser.equalsIgnoreCase("firefox")) {
      FirefoxOptions options = new FirefoxOptions();
      options.addArguments("-headless");
      driver = new FirefoxDriver(options);
    } else if (browser.equalsIgnoreCase("chrome")) {
      ChromeOptions options = new ChromeOptions();
      options.addArguments("headless");
      options.addArguments("window-size=1920x1080");
      driver = new ChromeDriver(options);
    } else {
      throw new IllegalArgumentException("Unsupported browser: " + browser);
    }
    BasePage basePage = new BasePage(driver);
    driver.get(HOME_PAGE_URL);
    driver.manage().timeouts().implicitlyWait(WAIT_MILLIS_WAIT);
    basePage.changeLanguage("English");
  }

  public void stop() {
    if (driver != null) {
      try {
        System.out.println("\033[32m" + "Closing WebDriver: " + driver + "\033[0m");
        driver.quit();
        System.out.println("\033[31m" + "Driver has been successfully closed."+ "\033[0m");
      } catch (Exception e) {
        System.err.println("\033[31m" + "Exception while quitting the WebDriver: " + e.getMessage() + "\033[0m");
      } finally {
        driver = null;
      }
    }
    // Закрытие chromedriver.exe
    try {
      Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
    } catch (IOException e) {
      System.err.println("IOException while trying to kill chromedriver.exe: " + e.getMessage());
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
    logger.info("Screenshot saved to: [{}]", screenshot.getAbsolutePath());
    return screenshot.getAbsolutePath();
  }
}
