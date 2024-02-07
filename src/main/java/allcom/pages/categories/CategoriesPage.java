package allcom.pages.categories;

import allcom.pages.BasePage;
import allcom.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class CategoriesPage extends BasePage {
    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    public static String categoriesPageURL() {
        return HomePage.homePageURL() + "/categories";
    }

    public Object getCategories() {
        return this;
    }
}