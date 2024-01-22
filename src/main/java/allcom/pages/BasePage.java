package allcom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
    @FindBy(xpath = "//div[@class='warning_message--validation']")
    WebElement errorValidation;

    public boolean errorValidationIsPresent(boolean expectedStatus) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOf(errorValidation));
            return element.isDisplayed() == expectedStatus;
        } catch (Exception e) {
            return !expectedStatus;
        }
    }
    @FindBy(xpath = "//div[@class='language__currency d-none d-lg-block']")
    WebElement chooseLangList;
    public void changeLanguage(String language) {
        click(chooseLangList);
        driver.findElement(By.xpath("//li[contains(text(), '" + language + "')]")).click();
    }
    public void clickOnButton(String buttonName) {
        driver.findElement(By.xpath("//button[@id='" + buttonName + "']")).click();
    }
    public void clickOnLabel(String labelName) {
        driver.findElement(By.xpath("//label[@id='" + labelName + "']/a")).click();
    }
    public void isElementPreset(String nameElement) {
        driver.findElement(By.xpath("//p[contains(text(),'" + nameElement + "')]"));
    }
    public void isSpanElementPreset(String nameElement) {
        driver.findElement(By.xpath("//span[@class='" + nameElement + "']"));
    }
    public void setCheckbox(String nameCheckbox) {
        WebElement checkbox = driver.findElement(By.xpath("//input[@id='" + nameCheckbox + "']"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }
    public void setCheckboxFirma() {
        WebElement checkboxFirma = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/form[1]/div[2]/div[1]/div[1]/input[1]"));
        if (!checkboxFirma.isSelected()) {
            checkboxFirma.click();
        }
    }
}
