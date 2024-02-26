package allcom_testng.pages.header;
import allcom_testng.pages.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class HeaderAccount extends BasePage{
    public HeaderAccount(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//li[@data-testid='myAccountTop']")
    WebElement myAccountTop;
    public WebElement getMyAccountTop() {
        return myAccountTop;
    }
    public void clickMyAccountTop() {
        By locator = By.xpath("//*[@data-testid='myAccountTop_link']");
        WebElement cartTop = driver.findElement(locator);
        if (cartTop != null) {
            cartTop.click();
        } else {
            throw new NoSuchElementException("Element not found: cartTop_link");
        }
    }
    @FindBy(xpath = "//li[@data-testid='wishlistTop']")
    WebElement wishlistTop;
    public WebElement getWishlistTop() {
        return wishlistTop;
    }
    public void clickWishlistTop() {
        By locator = By.xpath("//*[@data-testid='wishlistTop_link']");
        WebElement cartTop = super.waitForElement(locator, 5);
        if (cartTop != null) {
            click(cartTop);
        } else {
            throw new NoSuchElementException("Element not found: cartTop_link");
        }
    }
    @FindBy(xpath = "//li[@data-testid='cartTop_link']")
    WebElement cartTop;
    public WebElement getCartTop() {
        return wishlistTop;
    }
    public void clickCartTop() {
        By locator = By.xpath("//*[@data-testid='cartTop_link']");
        WebElement cartTop = super.waitForElement(locator, 5);
        if (cartTop != null) {
            cartTop.click();
        } else {
            throw new NoSuchElementException("Element not found: cartTop_link");
        }
    }
}
