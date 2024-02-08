package allcom_cucumber.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class BasePageCucumber {
    public static WebDriver driver;

    public BasePageCucumber(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void launchBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
    }
    public void openURL() {
        driver.get("http://allcom.itvm.com.ua:5173/");
    }
    public void closeBrowser() {
        driver.quit();
    }
    public void closeTab() {
        driver.close();
    }
    public void refreshPage() {
        driver.navigate().refresh();
    }
    public void navigateBack() {
        driver.navigate().back();
    }
    public void navigateForward() {
        driver.navigate().forward();
    }
    public void switchToTab(int tabNumber) {
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[tabNumber]);
    }
    public void click(WebElement element) {
        element.click();
    }
    public void type(WebElement element, String text) {
        if (text != null) {
            element.clear();
            element.sendKeys(text);
        }
    }
    public String getText(WebElement element) {
        return element.getText();
    }
    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

}
