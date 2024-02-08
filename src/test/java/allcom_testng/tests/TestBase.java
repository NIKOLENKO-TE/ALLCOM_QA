package allcom_testng.tests;

import allcom_testng.pages.ApplicationManager;
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
    protected static int BROWSER_MODE_SWITCHER = 1; // 1- NORMAL, 2 - HEADLESS
    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void logTestStart(Method method) {
        logger.info("STARTED: [" + method.getName() + "]");
    }
    @BeforeSuite
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        app = new ApplicationManager(browser);
        driver = app.getDriver();
        switch (BROWSER_MODE_SWITCHER) {
            case 1:
                app.init();
                break;
            case 2:
                app.initHeadless();
                break;
            default:
                throw new IllegalArgumentException("Invalid mode value: " + BROWSER_MODE_SWITCHER);
        }
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
            logger.error("ERROR FAILED TEST: [" + result.getMethod().getMethodName() + "], with VALUES: " + params);
            logger.error("ERROR TEST MESSAGE: " + errorMessage);
            logger.error("ERROR TEST STACK TRACE: ", throwable);
            logger.error("ERROR TEST SCREENSHOT: " + app.takeScreenshot());
            logger.info("[==================================================================================================]");
        }
    }
    @AfterSuite
    public void tearDown() {
        app.stop();
    }
}
