package allcom.tests;

import allcom.pages.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
    protected static ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void setUp() {
        //app.init();
        app.initHeadless();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }
}
