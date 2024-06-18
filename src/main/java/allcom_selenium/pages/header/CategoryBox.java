package allcom_selenium.pages.header;

import allcom_selenium.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoryBox extends BasePage {
    public CategoryBox(WebDriver driver) {
        super(driver);
    }
    @FindBy(className = "header__search--box")
    WebElement categoryBox;
    public WebElement getCategoryBox() {
        return categoryBox;
    }

}
