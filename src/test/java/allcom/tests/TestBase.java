package allcom.tests;

import allcom.pages.ApplicationManager;
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
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void logTestStart(Method method) {
        logger.info("Start test: [" + method.getName() + "]");
    }

    @AfterMethod
    public void stopTest(ITestResult result) {
        Object[] parameters = result.getParameters();
        String params = Arrays.toString(parameters);
        if (result.isSuccess()) {
            logger.info("PASSED: [" + result.getMethod().getMethodName() + "], with Parameters: " + params);
        } else {
            logger.error("FAILED: [" + result.getMethod().getMethodName() + "], with Parameters: " + params);
            logger.info("///////////////////////////////////////////////////////");
        }
    }

    @BeforeSuite
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        String mode = System.getProperty("mode", "headless");
        app = new ApplicationManager(browser);
        if (mode.equalsIgnoreCase("headless")) {
            app.initHeadless();
        } else {
            app.init();
        }
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }
}
