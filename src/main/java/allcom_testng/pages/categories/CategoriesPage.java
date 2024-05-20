package allcom_testng.pages.categories;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.homePage.HomePage;
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