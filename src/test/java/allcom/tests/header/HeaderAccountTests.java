package allcom.tests.header;

import allcom.pages.BasePage;
import allcom.pages.HomePage;
import allcom.pages.header.HeaderAccount;
import allcom.pages.login.LoginPage;
import allcom.tests.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class HeaderAccountTests extends TestBase {
    private BasePage basePage;
    private HeaderAccount headerAccount;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(app.driver);
        headerAccount = new HeaderAccount(app.driver);
        basePage.goToPage(HomePage.homePageURL());
        basePage.isCurrentPage(HomePage.homePageURL(), true);
    }

    @Test
    public void myAccountTopIsPresent() {
        Assert.assertNotNull(headerAccount.getMyAccountTop(), "MyAccountTop is not present");
    }

    @Test
    public void wishlistTopIsPresent() {
        Assert.assertNotNull(headerAccount.getWishlistTop(), "WishlistTop is not present");
    }

    @Test
    public void cartTopIsPresent() {
        Assert.assertNotNull(headerAccount.getCartTop(), "CartTop is not present");
    }

    @Test
    public void clickMyAccountTopNavigatesToCorrectPage() {
        Assert.assertNotNull(headerAccount.getMyAccountTop(), "My account top is not present");
        headerAccount.getMyAccountTop().click();
        basePage.isCurrentPage(LoginPage.loginPageURL(), true);
    }

    @Test
    public void clickWishlistTopNavigatesToCorrectPage() {
        Assert.assertNotNull(headerAccount.getWishlistTop(), "WishListTop is not present");
        headerAccount.getWishlistTop().click();
        basePage.isCurrentPage(HomePage.homePageURL() + "/user/my_account/products", true);
    }

    @Test
    public void clickCartTopNavigatesToCorrectPage() {
        Assert.assertNotNull(headerAccount.getCartTop(), "CartTop is not present");
        headerAccount.getCartTop().click();
        basePage.isCurrentPage(HomePage.homePageURL() + "/user/my_account/my_auctions", true);
    }

    @Test(enabled = false)
    public void allInteractiveElementsAreAccessible() {
        List<WebElement> interactiveElements = driver.findElements(By.xpath("//*[self::a or self::button or self::input or self::select or self::textarea]"));
        for (WebElement element : interactiveElements) {
            Assert.assertTrue(element.getAttribute("aria-label") != null || element.getAttribute("aria-labelledby") != null, "Element " + element.getTagName() + " is not accessible");
        }
    }

    @Test(enabled = false)
    public void allElementsAreKeyboardNavigable() {
        List<WebElement> interactiveElements = driver.findElements(By.xpath("//*[self::a or self::button or self::input or self::select or self::textarea]"));
        for (WebElement element : interactiveElements) {
            try {
                element.sendKeys(Keys.TAB);
                Assert.assertEquals(driver.switchTo().activeElement(), element, "Element " + element.getTagName() + " is not keyboard navigable");
            } catch (Exception e) {
                Assert.fail("Element " + element.getTagName() + " is not keyboard navigable");
            }
        }
    }

    @Test(enabled = false)
    public void textContrastIsReadable() {
        List<WebElement> textElements = driver.findElements(By.xpath("//*[self::p or self::h1 or self::h2 or self::h3 or self::h4 or self::h5 or self::h6 or self::a or self::button]"));
        for (WebElement element : textElements) {
            String backgroundColor = element.getCssValue("background-color");
            String color = element.getCssValue("color");
            Assert.assertNotEquals(backgroundColor, color, "Text contrast for element " + element.getTagName() + " is not readable");
        }
    }
}

