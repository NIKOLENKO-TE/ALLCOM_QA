package allcom.tests;

import allcom.pages.ApplicationManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {
    protected static ApplicationManager app = new ApplicationManager("chrome");

    protected static WebDriver driver;
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void logTestStart(Method method) {
        logger.info("Start test: [" + method.getName() + "]");
    }

    @BeforeSuite
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        String mode = System.getProperty("mode", "headless");
        app = new ApplicationManager(browser);
        driver = app.getDriver();
        if (mode.equalsIgnoreCase("headless")) {
            app.initHeadless();
        } else {
            app.init();
        }
    }

    @AfterMethod
    public void stopTest(ITestResult result) {
        Object[] parameters = result.getParameters();
        String params = Arrays.toString(parameters);
        if (result.isSuccess()) {
            logger.info("PASSED: [" + result.getMethod().getMethodName() + "], with Parameters: " + params);
        } else {
            Throwable throwable = result.getThrowable();
            String errorMessage = throwable != null ? throwable.getMessage() : "Unknown error";
            logger.error("ERROR FAILED TEST: [" + result.getMethod().getMethodName() + "], with Parameters: " + params);
            logger.error("ERROR TEST MESSAGE: " + errorMessage);
            logger.error("ERROR TEST SCREENSHOT: " + app.takeScreenshot());
            logger.info("[==================================================================================================]");
        }
    }
    @AfterSuite
    public void tearDown() {
        app.stop();
    }
}
