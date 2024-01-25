package allcom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }
    public void isCurrentPage(String expectedURL) {
        driver.getCurrentUrl();
    }

    public void goToPage(String pageURL) {
        driver.get(pageURL);
    }

    @FindBy(css = "[data-testid^='error_']")
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

    public void changeLanguage(String language) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement languageDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='language-text-set']")));
        languageDropdown.click();
        WebElement languageOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(), '" + language + "')]")));
        languageOption.click();
    }

    public void clickOnButton(String buttonId) {
        By buttonLocator = By.xpath("//button[@id='" + buttonId + "']");
        driver.findElement(buttonLocator).click();
    }

    public void clickOnLabel(String labelName) {
        driver.findElement(By.xpath("//label[@id='" + labelName + "']/a")).click();
    }

    public void clickOnHrefLink(String linkName) {
        driver.findElement(By.xpath("//href[@id='" + linkName + "']/a")).click();
    }

    public boolean isPPresent(String pElement) {
        try {
            driver.findElement(By.xpath("//p[@id='" + pElement + "']"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isButtonPresent(String buttonName) {
        try {
            driver.findElement(By.xpath("//button[@id='" + buttonName + "']"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isSpanElementPresent(String spanName) {
        try {
            driver.findElement(By.xpath("//span[@id='" + spanName + "']"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
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
