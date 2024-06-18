package allcom_selenium;//TestBase.java

import allcom_selenium.pages.ApplicationManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class TestBaseSE {
    protected static ApplicationManager app = new ApplicationManager("chrome");

    public static WebDriver driver;
    Logger logger = LoggerFactory.getLogger(TestBaseSE.class);
    public static boolean HEADLESS_MODE = false; // false - NORMAL, true - HEADLESS
    public static void SET_HEADLESS_MODE(boolean headlessMode) {
        HEADLESS_MODE = headlessMode;
    }
    public WebDriver getDriver() {
        return driver;
    }

    private Instant testStart; // Добавляем поле для хранения времени начала теста

    @BeforeSuite
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        app = new ApplicationManager(browser);
        driver = app.getDriver();
        if (HEADLESS_MODE) {
            app.initHeadless();
        } else {
            app.init();
        }
    }

    @BeforeMethod
    public void logTestStart(Method method) {
        testStart = Instant.now(); // Записываем время начала теста
        logger.info("STARTED: [" + method.getName() + "]");
    }

    @AfterMethod
    public void stopTest(ITestResult result) {
        Instant testFinish = Instant.now();
        long timeElapsed = Duration.between(testStart, testFinish).toMillis();
        Object[] parameters = result.getParameters();
        String params = Arrays.toString(parameters);
        if (result.isSuccess()) {
            logger.info("PASSED: [" + result.getMethod().getMethodName() + "], with VALUES: " + params + ", Time taken: [" + timeElapsed + "] milliseconds"); // Добавляем время выполнения в лог
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
    @AfterSuite(enabled = true)
    public void tearDown() {
        app.stop();
    }
}