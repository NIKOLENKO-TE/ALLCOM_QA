//BasePage.java
package allcom_testng.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;

import static allcom_testng.pages.BasePage.ElementType.DATA_TESTID;
import static allcom_testng.pages.HomePage.*;

public class BasePage {
    protected WebDriver driver;
    FluentWait<WebDriver> wait;
    public static final Duration WAIT_SEC = Duration.ofSeconds(5);

    public enum ElementType {
        XPATH, CSS, ID, DATA_TESTID, CLASS, HREF, ROLE, LABEL, SPAN, BUTTON, P, ERROR_VALIDATION, PASSWORD_CONFIRM, LANGUAGE_SELECTOR, LANGUAGE_ITEM, CHECKBOX
    }

    public BasePage() {
    }
    public WebElement getElement(ElementType type, String value) {
        By locator = getByFromType(type, value);
        try {
            waitForElementToAppear(type, value, true, 5);
            return driver.findElement(locator);
        } catch (TimeoutException e) {
            throw new RuntimeException("Element not found with locator: " + locator, e);
        }
    }
    public WebElement getElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element;
        } catch (TimeoutException e) {
            throw new RuntimeException("Element not found: " + element, e);
        }
    }
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(200))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        PageFactory.initElements(driver, this);
    }

    public void waitUntilElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(WebElement element) {
        waitUntilElementToBeClickable(element);
        element.click();
    }

    public void clickLinks(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
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
            isCurrentPage(urlToCompare, true);
            closeTab();
            driver.switchTo().window(currentWindowHandle);
        } else {
            isCurrentPage(urlToCompare, true);
        }
    }

    public void type(WebElement element, String text) {
        waitUntilElementToBeClickable(element);
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

    public void isCurrentPage(String expectedURL, boolean expectedStatus) {
        wait.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.endsWith("/")) {
            currentUrl = currentUrl.substring(0, currentUrl.length() - 1);
        }
        boolean isCurrent = currentUrl.equals(expectedURL);
        assert isCurrent == expectedStatus : "Current page status does not match the expected status";
    }

    public void goToPage(String pageURL) {
        driver.get(pageURL);
    }

    public WebElement waitForElement(By locator, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
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
        click(waitForElement(By.cssSelector(HomePage.LANGUAGE_SELECTOR), 10));
        click(waitForElement(By.xpath(String.format(HomePage.LANGUAGE_ITEM_XPATH, language)), 10));
    }

    protected By getByFromType(ElementType type, String value) {
        switch (type) {
            case ID:
                return By.id(value);
            case XPATH:
                return By.xpath(value);
            case CSS:
                return By.cssSelector(value);
            case DATA_TESTID:
                return By.xpath("//*[@data-testid='" + value + "']");
            case HREF:
                return By.xpath("//a[@href='" + value + "']");
            case ROLE:
                return By.xpath("//*[@role='" + value + "']");
            case LABEL:
                return By.xpath("//label[@id='" + value + "']/a");
            case CLASS:
                return By.className(value);
            case CHECKBOX:
                return By.xpath("//input[@id='" + value + "']");
            case SPAN:
                return By.xpath("//span[@id='" + value + "']");
            case BUTTON:
                return By.xpath("//button[@id='" + value + "']");
            case P:
                return By.xpath("//p[@id='" + value + "']");
            case ERROR_VALIDATION:
                return By.cssSelector("[data-testid^='error_']");
            case LANGUAGE_SELECTOR:
                return By.cssSelector(LANGUAGE_SELECTOR);
            case LANGUAGE_ITEM:
                return By.xpath(String.format(LANGUAGE_ITEM_XPATH, value));
            default:
                throw new IllegalArgumentException("Invalid selector type: " + type);
        }
    }

    public void clickOnElement(ElementType type, String value) {
        By by = getByFromType(type, value);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        if (type == ElementType.CHECKBOX) {
            if (!element.isSelected()) {
                element.click();
            }
        } else {
            click(element);
        }
    }
    public void isElementPresent(WebElement element, boolean expectedStatus) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            Assert.assertTrue(expectedStatus, "Element ["+ element + "] is not visible");
        } catch (TimeoutException e) {
            Assert.assertFalse(expectedStatus, "Element ["+ element + "] not be found for short time");
        } catch (NoSuchElementException e) {
            Assert.assertFalse(expectedStatus, "Element ["+ element + "] not be found");
        }
    }
    public boolean isElementPresent(ElementType type, String value, boolean expectedStatus) {
        By by = getByFromType(type, value);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            Assert.assertTrue(expectedStatus);
            return true;
        } catch (TimeoutException e) {
            Assert.assertFalse(expectedStatus, "Element ["+  type + ": " +  value + "] not be found for short time");
            return false;
        } catch (NoSuchElementException e) {
            Assert.assertFalse(expectedStatus, "Element ["+  type + ": " +  value + "] not be found");
            return false;
        }
    }
//public boolean isElementPresent(ElementType type, String value, boolean expectedStatus) {
//    By locator = getByFromType(type, value);
//    try {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//        return expectedStatus;
//    } catch (TimeoutException e) {
//        Assert.assertFalse(expectedStatus, "Element with " + type + ": " + value + " not be found for short time");
//        return true;
//    } catch (NoSuchElementException e) {
//        Assert.assertFalse(expectedStatus, "Element with " + type + ": " + value + " not be found");
//        return true;
//    }
//}
public void isElementClickable(WebElement element, boolean expectedStatus) {
    try {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Assert.assertTrue(expectedStatus, "Element " + element + " is not clickable");
    } catch (TimeoutException e) {
        Assert.assertFalse(expectedStatus, "Element " + element + " is not clickable for short time");
    } catch (NoSuchElementException e) {
        Assert.assertFalse(expectedStatus, "Element " + element + " not be found");
    }
}
    public void isElementClickable(By locator, Dimension resolution, boolean expectedStatus) {
        driver.manage().window().setSize(resolution);
        boolean isClickable;
        try {
            wait.withTimeout(WAIT_SEC)
                    .withTimeout(Duration.ofMillis(5000))
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.elementToBeClickable(locator));
            isClickable = true;
        } catch (TimeoutException e) {
            isClickable = false;
            System.err.println("TimeoutException: Element was not clickable within the specified wait time [" + WAIT_SEC.getSeconds() + "] seconds");
        } catch (NoSuchElementException e) {
            isClickable = false;
            System.err.println("NoSuchElementException: Element was not found");
        }
        Assert.assertEquals(isClickable, expectedStatus, "Element is clickable?");
    }

    public void isElementClickable(ElementType type, String value, boolean expectedStatus) {
        By locator = getByFromType(type, value);
        boolean isClickable;
        try {
            wait.withTimeout(WAIT_SEC)
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.elementToBeClickable(locator));
            isClickable = true;
        } catch (TimeoutException e) {
            isClickable = false;
            System.err.println("TimeoutException: Element was not clickable within the specified wait time [" + WAIT_SEC.getSeconds() + "] seconds");
        } catch (NoSuchElementException e) {
            isClickable = false;
            System.err.println("NoSuchElementException: Element was not found");
        }
        Assert.assertEquals(isClickable, expectedStatus, "Element is clickable?");
    }
    public void isElementClickable(ElementType type, String value, Dimension resolution, boolean expectedStatus) {
        By locator = getByFromType(type, value);
        isElementClickable(locator, resolution, expectedStatus);
    }
    public void validateField(WebElement getElement, String invalidData, boolean validationExpectation) {
        if (getElement == null) {
            throw new IllegalArgumentException("Element is invalid");
        }
        if (invalidData == null) {
            throw new IllegalArgumentException("Data is null");
        }
        type(getElement, invalidData);
        isValidationErrorPresent(validationExpectation);
    }

    public void setCheckboxFirma() {
        clickOnElement(DATA_TESTID, "checkbox_client_firma_switch");
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

    public void waitForPageLoadComplete() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void waitForPageLoadInteractive() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("interactive"));
    }
}