package allcom.pages.header;

import allcom.pages.BasePage;
import allcom.pages.HomePage;
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
