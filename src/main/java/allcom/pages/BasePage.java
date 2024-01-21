package allcom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        element.click();
    }
    public void type(WebElement element, String text) {
       if(text != null) {
           click(element);
           element.clear();
           element.sendKeys(text);
       }
    }
    public boolean isCurrentPage(String expectedURL) {
        return driver.getCurrentUrl().equals(expectedURL);
    }

    public void goToPage(String pageURL) {
        driver.get(pageURL);
    }

    @FindBy(xpath = "//div[@class='language__currency d-none d-lg-block']")
    WebElement chooseLangList;
    public void changeLanguage(String language) {
        click(chooseLangList);
        driver.findElement(By.xpath("//li[contains(text(), '" + language + "')]")).click();
    }
}
