package allcom_selenium;

import allcom_selenium.pages.ApplicationManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBaseSE {
  public static ApplicationManager app;
  protected static WebDriver driver;
  Logger logger = LoggerFactory.getLogger(TestBaseSE.class);
  public static boolean HEADLESS_MODE = false;

  @BeforeSuite
  public void setUp() {
    String browser = System.getProperty("browser", "chrome");
    app = new ApplicationManager(browser);
    if (HEADLESS_MODE) {
      app.initHeadless();
    } else {
      app.init();
    }
    driver = app.getDriver();
  }

  @BeforeMethod
  public void logTestStart(Method method) {
    logger.info("STARTED: [{}]", method.getName());
  }

  @AfterMethod
  public void stopTest(ITestResult result) {
    Object[] parameters = result.getParameters();
    String params = Arrays.toString(parameters);
    if (result.isSuccess()) {
      logger.info("PASSED: [" + result.getMethod().getMethodName() + "], with VALUES: " + params);
    } else {
      Throwable throwable = result.getThrowable();
      String errorMessage = throwable != null ? throwable.getMessage() : "Unknown error";
      logger.error("ERROR FAILED TEST: [{}], with VALUES: {}", result.getMethod().getMethodName(), params);
      logger.error("ERROR TEST MESSAGE: {}", errorMessage);
      logger.error("ERROR TEST STACK TRACE: ", throwable);
      logger.error("ERROR TEST SCREENSHOT: {}", app.takeScreenshot());
      logger.info("[==================================================================================================]");
    }
  }

  @AfterSuite(enabled = true)
  public void tearDown() {
    app.stop();
  }
}
