package allcom.pages.header;
import allcom.pages.BasePage;

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

    @FindBy(xpath = "//li[@data-testid='wishlistTop']")
    WebElement wishlistTop;
    public WebElement getWishlistTop() {
        return wishlistTop;
    }

    @FindBy(xpath = "//li[@data-testid='cartTop']")
    WebElement cartTop;
    public WebElement getCartTop() {
        return cartTop;
    }
}
