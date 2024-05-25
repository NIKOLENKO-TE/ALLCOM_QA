package allcom_testng;

import allcom_testng.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

import static allcom_testng.pages.BasePage.checkLink;


public class BrokenLinksTests extends TestBaseSE{

    @Test
    public void checkLinks() {
        List<WebElement> links = TestBaseSE.app.driver.findElements(By.tagName("a"));
        new BasePage(TestBaseSE.app.driver);
        for (WebElement link : links) {
            checkLink(link.getAttribute("href"));
        }
    }

    @AfterMethod
    public void tearDown() {
        TestBaseSE.app.driver.quit();
    }
}
