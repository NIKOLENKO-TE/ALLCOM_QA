//BasePage.java
package allcom_selenium.pages;

import allcom_selenium.pages.homePage.HomePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;

import static allcom_selenium.pages.BasePage.ElementType.DATA_TESTID;
import static allcom_selenium.pages.BasePage.ElementType.ERROR_VALIDATION;
import static allcom_selenium.pages.homePage.HomePage.*;

public class BasePage {
    protected WebDriver driver;
    public FluentWait<WebDriver> wait;
    public static final Duration WAIT_SEC = Duration.ofSeconds(5);
    static Logger logger = LoggerFactory.getLogger(BasePage.class);
    public enum ElementType {
        XPATH, CSS, ID, DATA_TESTID, CLASS, HREF, ROLE, LABEL, SPAN, BUTTON, P, ERROR_VALIDATION, PASSWORD_CONFIRM, LANGUAGE_SELECTOR, LANGUAGE_ITEM, CHECKBOX
    }

    public BasePage() {
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(100))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        PageFactory.initElements(driver, this);
    }

    public void waitUntilUrlToBe(String url) {
        final int MAX_ATTEMPTS = 5;
        int attempts = 0;
        final String expectedUrl = url.replaceAll("/$", "");
        Instant start = Instant.now();
        while (attempts < MAX_ATTEMPTS) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, WAIT_SEC);
                wait.until((ExpectedCondition<Boolean>) webDriver -> {
                    assert webDriver != null;
                    String currentUrl = webDriver.getCurrentUrl().replaceAll("/$", "");
                    return currentUrl.equals(expectedUrl);
                });
                long timeTakenMillis = Duration.between(start, Instant.now()).toMillis();
                System.out.println("Waiting for URL to be [" + expectedUrl + "], Time taken: [" + timeTakenMillis + "] milliseconds, Attempts: [" + MAX_ATTEMPTS + "]");
                break;
            } catch (TimeoutException e) {
                attempts++;
            } catch (WebDriverException e) {
                long timeTakenMillis = Duration.between(start, Instant.now()).toMillis();
                String actualUrl = driver.getCurrentUrl().replaceAll("/$", "");
                throw new RuntimeException("Failed to wait URL \nExpected URL: [" + expectedUrl + "] \nActual URL: [" + actualUrl + "]\nTime taken: [" + timeTakenMillis + "] milliseconds\nAttempts: [" + MAX_ATTEMPTS + "]", e);
            }
        }
        if (attempts == MAX_ATTEMPTS) {
            long timeTakenMillis = Duration.between(start, Instant.now()).toMillis();
            String actualUrl = driver.getCurrentUrl().replaceAll("/$", "");
            throw new RuntimeException("Failed to navigate to URL. \nExpected URL: [" + expectedUrl + "] \nActual URL: [" + actualUrl + "]\nTime taken: [" + timeTakenMillis + "] milliseconds\nAttempts: [" + MAX_ATTEMPTS + "]");
        }
    }

    public void isCurrentPage(String expectedUrl, boolean expectedStatus) {
        final String ERROR_MESSAGE = "\nCurrent URL: %s\nExpected URL: %s\nTime taken: [%d] milliseconds";
        final String[] currentUrl = new String[1];
        final String[] expectedUrlArray = new String[1];
        expectedUrlArray[0] = expectedUrl.replaceAll("/$", "");
        Instant start = null;
        try {
            waitForSpinnerStop();
            start = Instant.now();
            waitUntilUrlToBe(expectedUrlArray[0]);
            currentUrl[0] = driver.getCurrentUrl().replaceAll("/$", "");
            long timeTakenMillis = Duration.between(start, Instant.now()).toMillis();
            String errorMessage = String.format(ERROR_MESSAGE, currentUrl[0], expectedUrlArray[0], timeTakenMillis);
            if (expectedStatus) {
                Assert.assertEquals(currentUrl[0], expectedUrlArray[0], errorMessage);
            } else {
                Assert.assertNotEquals(currentUrl[0], expectedUrlArray[0], errorMessage);
            }
        } catch (TimeoutException e) {
            assert start != null;
            long timeTakenMillis = Duration.between(start, Instant.now()).toMillis();
            String errorMessage = String.format(ERROR_MESSAGE, currentUrl[0], expectedUrlArray[0], timeTakenMillis);
            if (expectedStatus) {
                Assert.fail("\nCurrent page URL does not match the expected URL: " + errorMessage);
            } else {
                Assert.fail("\nCurrent page URL matches the expected URL: " + errorMessage);
            }
        }
    }

    public void waitForSpinnerStop() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_SEC);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loading-spinner']")));
    }

    public WebElement getElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element;
        } catch (TimeoutException e) {
            throw new RuntimeException("Element not found: " + element, e);
        }
    }

    public WebElement waitUntilElementToBeClickable(WebElement element) {
        final int MAX_ATTEMPTS = 10;
        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element));
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
            } catch (ElementNotInteractableException e) {
                throw new RuntimeException("Element is not intractable: [" + element + "]", e);
            } catch (NoSuchElementException e) {
                throw new RuntimeException("Element not found: [" + element + "]", e);
            } catch (TimeoutException e) {
                throw new RuntimeException("Operation timed out: [" + element + "]", e);
            } catch (WebDriverException e) {
                throw new RuntimeException("Element operation failed: [" + element + "]", e);
            }
        }
        if (attempts == MAX_ATTEMPTS) {
            throw new RuntimeException("Element was not clickable after: [" + MAX_ATTEMPTS + "] attempts");
        }
        return element;
    }

    public void click(WebElement element) {
        isElementPresent(element, true);
        String[] split = element.toString().split("->");
        String elementDescription = split.length > 1 ? split[1].trim().replace("]", "") : "Element description not available";
        Instant start = null;
        try {
            start = Instant.now();
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            long timeTakenMillis = Duration.between(start, Instant.now()).toMillis();
            System.out.println("Click on Element [" + elementDescription + "] Time taken: [" + timeTakenMillis + "] milliseconds");
        } catch (ElementNotInteractableException e) {
            throw new RuntimeException("Element is not intractable: [" + elementDescription + "]", e);
        } catch (StaleElementReferenceException e) {
            throw new RuntimeException("Stale element reference: [" + elementDescription + "]", e);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Element not found: [" + elementDescription + "]", e);
        } catch (TimeoutException e) {
            assert start != null;
            long timeTakenMillis = Duration.between(start, Instant.now()).toMillis();
            throw new RuntimeException("Operation timed out: [" + elementDescription + "] Time taken: [" + timeTakenMillis + "] milliseconds", e);
        } catch (WebDriverException e) {
            throw new RuntimeException("Element is not clickable: [" + elementDescription + "]", e);
        }
    }

    public void clickJSExecutor(WebElement element) {
        waitUntilElementToBeClickable(element);
        String[] split = element.toString().split("->");
        String elementDescription = split.length > 1 ? split[1].trim().replace("]", "") : "Element description not available";
        Instant start = null;
        try {
            start = Instant.now();
            wait.until(ExpectedConditions.elementToBeClickable(element));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            long timeTakenMillis = Duration.between(start, Instant.now()).toMillis();
            System.out.println("Click on Element [" + elementDescription + "], Time taken: [" + timeTakenMillis + "] milliseconds");
        } catch (ElementNotInteractableException e) {
            throw new RuntimeException("Element is not intractable: [" + elementDescription + "]", e);
        } catch (StaleElementReferenceException e) {
            throw new RuntimeException("Stale element reference: [" + elementDescription + "]", e);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Element not found: [" + elementDescription + "]", e);
        } catch (TimeoutException e) {
            assert start != null;
            long timeTakenMillis = Duration.between(start, Instant.now()).toMillis();
            throw new RuntimeException("Operation timed out: [" + elementDescription + "], Time taken: [" + timeTakenMillis + "] milliseconds", e);
        } catch (WebDriverException e) {
            throw new RuntimeException("Element is not clickable: [" + elementDescription + "]", e);
        }
    }

    public void closeTab() {
        driver.close();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    public void testClickLink(WebElement urlToOpen, String urlToCompare) {
        String currentWindowHandle = driver.getWindowHandle();
        clickJSExecutor(urlToOpen);
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
            try {
                click(element);
                String elementDescription = element.toString().split("->")[1].trim().replace("]", "");
                element.clear();
                System.out.println("Clear Element [" + elementDescription + "]");
                element.sendKeys(text);
                System.out.println("Type [" + text + "] to [" + elementDescription + "]");
            } catch (ElementNotInteractableException e) {
                throw new RuntimeException("Element is not intractable: [" + element + "]", e);
            } catch (StaleElementReferenceException e) {
                throw new RuntimeException("Stale element reference: [" + element + "]", e);
            } catch (NoSuchElementException e) {
                throw new RuntimeException("Element not found: [" + element + "]", e);
            } catch (TimeoutException e) {
                throw new RuntimeException("Operation timed out: [" + element + "]", e);
            } catch (WebDriverException e) {
                throw new RuntimeException("Element operation failed: [" + element + "]", e);
            }
        }
    }

    public void isValidationErrorPresent(boolean validationStatus) {
        boolean isPresent = isElementPresent(ERROR_VALIDATION, "", validationStatus);
        assert isPresent == validationStatus : "Validation error present status does not match the expected status";
    }

    public void goToPage(String pageURL) {
        waitForSpinnerStop();
        driver.get(pageURL);
    }

    public WebElement waitForElement(By locator, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToAppear(ElementType type, String value, boolean expectedStatus, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        waitForSpinnerStop();
        try {
            wait.until((WebDriver d) -> isElementPresent(type, value, expectedStatus));
        } catch (TimeoutException e) {
            System.out.println("Element can't be found on the page in " + timeoutInSeconds + " seconds");
            throw new RuntimeException("The item didn't appear within the specified time", e);
        }
    }

    public void changeLanguage(String language) {
        try {
            click(waitForElement(By.cssSelector(HomePage.LANGUAGE_SELECTOR), 5));
            click(waitForElement(By.xpath(String.format(HomePage.LANGUAGE_ITEM_XPATH, language)), 5));
        } catch (ElementNotInteractableException e) {
            throw new RuntimeException("Element is not interactable: [" + language + "]", e);
        } catch (StaleElementReferenceException e) {
            throw new RuntimeException("Stale element reference: [" + language + "]", e);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Element not found: [" + language + "]", e);
        } catch (TimeoutException e) {
            throw new RuntimeException("Operation timed out: [" + language + "]", e);
        } catch (WebDriverException e) {
            throw new RuntimeException("Element operation failed: [" + language + "]", e);
        }
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
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        isElementPresent(element, true);
        if (type == ElementType.CHECKBOX) {
            if (!element.isSelected()) {
                click(element);
            }
        } else {
            waitUntilElementToBeClickable(element);
            click(element);
        }
    }

    public void clickOnElement(WebElement element) {
        isElementPresent(element, true);
        if (element.getTagName().equals("input") && element.getAttribute("type").equals("checkbox")) {
            if (!element.isSelected()) {
                clickJSExecutor(element);
            }
        } else {
            waitUntilElementToBeClickable(element);
            clickJSExecutor(element);
        }
    }

    public void isElementPresent(WebElement element, boolean expectedStatus) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            Assert.assertEquals(true, expectedStatus, "Element [" + element.toString().split("->")[1].trim().replace("]", "") + "] visibility status is: " + expectedStatus);
        } catch (TimeoutException | NoSuchElementException e) {
            Assert.assertEquals(false, expectedStatus, "Element [" + element.toString().split("->")[1].trim().replace("]", "") + "] visibility status is: " + expectedStatus);
        }
    }

    public boolean isElementPresent(ElementType type, String value, boolean expectedStatus) {
        By by = getByFromType(type, value);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            Assert.assertEquals(true, expectedStatus, "Element [" + type + ": " + value + "] visibility status does not match the expected status");
            return true;
        } catch (TimeoutException e) {
            Assert.assertEquals(false, expectedStatus, "Element [" + type + ": " + value + "] not be found for short time");
            return expectedStatus;
        } catch (NoSuchElementException e) {
            Assert.assertEquals(false, expectedStatus, "Element [" + type + ": " + value + "] not be found");
            return expectedStatus;
        }
    }

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

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void clickLinkSameTab(String linkName) {
        if (linkName != null) {
            if (!linkName.startsWith("https://") && !linkName.startsWith("http://")) {
                linkName = HOME_PAGE_URL + "/" + linkName;
            }
            driver.get(linkName);
        } else {
            throw new IllegalArgumentException("Invalid link name: " + linkName);
        }
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

    public void switchToNewTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }
    public static void checkLink(String url) {
        if (url == null || url.isEmpty()) {
            logger.error("URL is either not configured for anchor tag or it is empty");
            return;
        }

//        if (!url.startsWith(homePageURL())) {
//        logger.info("URL belongs to another domain, skipping it.");
//            return;
//        }

        try {
            HttpURLConnection huc = (HttpURLConnection) (new URL(url).openConnection());
            huc.setRequestMethod("HEAD");
            huc.connect();
            int respCode = huc.getResponseCode();

            if (respCode >= 400) {
                logger.error("Broken link: " + url);
            } else {
                logger.info("Valid link: " + url);
            }
            huc.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void checkLocatorLink(WebDriver driver, By locator) {
        if (locator == null) {
            logger.error("Locator is null, cannot check link.");
            return;
        }

        try {
            WebElement linkElement = new WebDriverWait(driver, WAIT_SEC).until(ExpectedConditions.visibilityOfElementLocated(locator));
            String url = linkElement.getAttribute("href");

            if (url == null || url.isEmpty()) {
                logger.error("URL is either not configured for anchor tag or it is empty.");
                return;
            }

            // You can add the domain check here if needed
            // if (!url.startsWith(homePageURL())) {
            //     logger.info("URL belongs to another domain, skipping it.");
            //     return;
            // }

            HttpURLConnection huc = (HttpURLConnection) (new URL(url).openConnection());
            huc.setRequestMethod("HEAD");
            huc.connect();
            int respCode = huc.getResponseCode();

            if (respCode >= 400) {
                logger.error("Broken link: " + url);
            } else {
                logger.info("Valid link: " + url);
            }
            huc.disconnect();
        } catch (NoSuchElementException e) {
            logger.error("Link element not found: " + locator, e);
        } catch (TimeoutException e) {
            logger.error("Timeout while waiting for link element: " + locator, e);
        } catch (IOException e) {
            logger.error("Error checking link: " + locator, e);
        }
    }
}