package allcom_selenium.links;

import allcom_selenium.TestBaseSE;
import allcom_selenium.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static allcom_selenium.pages.BasePage.checkLink;


public class BrokenLinksTests extends TestBaseSE {

    @Test
    public void checkLinks() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        new BasePage(driver);
        List<String> brokenLinks = new ArrayList<>();
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (!checkLink(url)) {
                brokenLinks.add(url);
            }
        }
        if (!brokenLinks.isEmpty()) {
            throw new SkipException("\033[31m" +"There were some broken links: " + brokenLinks + "\033[0m");
        }
    }
}
