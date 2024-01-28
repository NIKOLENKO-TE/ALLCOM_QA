package allcom.pages.header;

import allcom.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AllcomLogo extends BasePage {
    public AllcomLogo(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//img[@alt='logo']")
    WebElement allcomLogo;

    public WebElement getAllcomLogo() {
        return allcomLogo;
    }
}
