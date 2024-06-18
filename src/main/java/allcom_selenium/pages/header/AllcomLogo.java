package allcom_selenium.pages.header;

import allcom_selenium.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AllcomLogo extends BasePage {
    BasePage basePage = new BasePage(driver);

    public AllcomLogo(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//img[@alt='logo']")
    protected WebElement allcomLogo;

    public WebElement getAllcomLogo() {
        return basePage.getElement(allcomLogo);
    }
}
