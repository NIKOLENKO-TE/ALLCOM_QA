package allcom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BasePage {
    public WebDriver driver;

    public enum ElementType {
        XPATH, CSS, ID, DATA_TESTID, HREF, ROLE, LABEL, SPAN, BUTTON, P, ERROR_VALIDATION, PASSWORD_CONFIRM, CHECKBOX
    }
    public BasePage() {
    }
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
    public void isCurrentPage(String expectedURL, boolean expectedPage) {
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.endsWith("/")) {
            currentUrl = currentUrl.substring(0, currentUrl.length() - 1);
        }
        boolean isCurrent = currentUrl.equals(expectedURL);
        Assert.assertEquals(isCurrent, expectedPage, "Current page status does not match the expected status");
    }

    public void goToPage(String pageURL) {
        driver.get(pageURL);
    }

    public void changeLanguage(String language) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement languageDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='language-text-set']")));
        languageDropdown.click();
        WebElement languageOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(), '" + language + "')]")));
        languageOption.click();
    }

    public void clickOnElement(ElementType type, String value) {
        By by;
        switch (type) {
            case ID:
                by = By.id(value);
                break;
            case DATA_TESTID:
                by = By.xpath("//*[@data-testid='" + value + "']");
                break;
            case HREF:
                by = By.xpath("//a[@href='" + value + "']");
                break;
            case ROLE:
                by = By.xpath("//*[@role='" + value + "']");
                break;
            case LABEL:
                by = By.xpath("//label[@id='" + value + "']/a");
                break;
            case CHECKBOX:
                by = By.xpath("//input[@id='" + value + "']");
                WebElement checkbox = driver.findElement(by);
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
                return;
            default:
                throw new IllegalArgumentException("Invalid selector type: " + type);
        }
        driver.findElement(by).click();
    }

    public void isValidationErrorPresent(boolean validationStatus) {
        boolean isPresent = isElementPresent(BasePage.ElementType.ERROR_VALIDATION, "", validationStatus);
        assert isPresent == validationStatus : "Validation error present status does not match the expected status";
    }

    public boolean isElementPresent(ElementType type, String value, boolean expectedStatus) {
        By by;
        switch (type) {
            case XPATH:
                by = By.xpath(value);
                break;
            case CSS:
                by = By.cssSelector(value);
                break;
            case DATA_TESTID:
                by = By.xpath("//*[@data-testid='" + value + "']");
                break;
            case SPAN:
                by = By.xpath("//span[@id='" + value + "']");
                break;
            case BUTTON:
                by = By.xpath("//button[@id='" + value + "']");
                break;
            case P:
                by = By.xpath("//p[@id='" + value + "']");
                break;
            case ERROR_VALIDATION:
                by = By.cssSelector("[data-testid^='error_']");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                    Assert.assertTrue(expectedStatus);
                    return true;
                } catch (Exception e) {
                    Assert.assertFalse(expectedStatus, "Element is not present");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid selector type: " + type);
        }
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            Assert.assertFalse(expectedStatus, "Element is not present");
        }
        return expectedStatus;
    }

    public void validateFieldWithIncorrectData(WebElement getElement, String invalidData, boolean validationExpectation) {
        if (getElement != null && invalidData != null) {
            type(getElement, invalidData);
            isValidationErrorPresent(validationExpectation);
        } else {
            throw new IllegalArgumentException("Element invalid or data is null");
        }
    }

    public void setCheckboxFirma() {
        clickOnElement(BasePage.ElementType.DATA_TESTID, "checkbox_client_firma_switch");
    }
}
