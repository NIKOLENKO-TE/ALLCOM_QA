package allcom_cucumber.stepDefinitions;

import allcom_selenium.TestBaseSE;
import allcom_selenium.pages.homePage.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static allcom_selenium.TestBaseSE.app;

public class HomePageSteps {
    WebDriver driver = app.getDriver();

    @Given("User open HOME_PAGE and choose language to EN")
    public void user_launches_the_browser_and_lang_en() {
        if (TestBaseSE.HEADLESS_MODE) {
            app.initHeadless();
        } else {
            app.init();
        }
    }

    @When("User verifies Home Page title Auctions")
    public void verify_HomePage_title_auctions() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageTitleAuctionsDisplayed());
    }

    @And("User close the browser")
    public void userCloseBrowser() {
        app.stop();
    }
}