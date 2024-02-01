package allcom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

public class BasePage {
    public WebDriver driver;
    private static final Duration WAIT_SECONDS = Duration.ofSeconds(5);

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
        WebDriverWait wait = new WebDriverWait(driver, WAIT_SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void clickLinks(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
    public void switchToNewTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }
    public void closeTab() {
        driver.close();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }
    public void testClickLink(WebElement urlToOpen, String urlToCompare) {
        String currentWindowHandle = driver.getWindowHandle();
        clickLinks(urlToOpen);
        Set<String> windowHandles = driver.getWindowHandles();
        if (windowHandles.size() > 1) {
            windowHandles.remove(currentWindowHandle);
            String newWindowHandle = windowHandles.iterator().next();
            driver.switchTo().window(newWindowHandle);
            waitForPageLoad();
            isCurrentPage(urlToCompare, true);
            closeTab();
            driver.switchTo().window(currentWindowHandle);
        } else {
            isCurrentPage(urlToCompare, true);
        }
    }
    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void isValidationErrorPresent(boolean validationStatus) {
        boolean isPresent = isElementPresent(BasePage.ElementType.ERROR_VALIDATION, "", validationStatus);
        assert isPresent == validationStatus : "Validation error present status does not match the expected status";
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

    public WebElement waitForElement(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToAppear(ElementType type, String value, boolean expectedStatus, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        try {
            wait.until((WebDriver d) -> isElementPresent(type, value, expectedStatus));
        } catch (TimeoutException e) {
            System.out.println("Element can't be found on the page in " + timeoutInSeconds + " seconds");
            throw new RuntimeException("The item didn't appear within the specified time", e);
        }
    }

    public void changeLanguage(String language) {
        WebElement languageDropdown = waitForElement(By.cssSelector("[data-testid='language-text-set']"), 10);
        languageDropdown.click();
        WebElement languageOption = waitForElement(By.xpath("//li[contains(text(), '" + language + "')]"), 10);
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

    public boolean isElementPresent(ElementType type, String value, boolean expectedStatus) {
        switch (type) {
            case XPATH:
                By.xpath(value);
                break;
            case CSS:
                By.cssSelector(value);
                break;
            case HREF:
                By.xpath("//a[@href='" + value + "']");
                break;
            case DATA_TESTID:
                By.xpath("//*[@data-testid='" + value + "']");
                break;
            case SPAN:
                By.xpath("//span[@id='" + value + "']");
                break;
            case BUTTON:
                By.xpath("//button[@id='" + value + "']");
                break;
            case P:
                By.xpath("//p[@id='" + value + "']");
                break;
            case ERROR_VALIDATION:
                try {
                    driver.findElement(By.cssSelector("[data-testid^='error_']"));
                    Assert.assertTrue(expectedStatus);
                    return true;
                } catch (NoSuchElementException e) {
                    Assert.assertFalse(expectedStatus, "Element can not be found");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid selector type: " + type);
        }
        return expectedStatus;
    }

    public void validateField(WebElement getElement, String invalidData, boolean validationExpectation) {
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

    public void clickLinkSameTab(String linkName) {
        if (linkName != null) {
            if (!linkName.startsWith("https://") && !linkName.startsWith("http://")) {
                linkName = HomePage.homePageURL() + "/" + linkName;
            }
            driver.get(linkName);
        } else {
            throw new IllegalArgumentException("Invalid link name: " + linkName);
        }
    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
