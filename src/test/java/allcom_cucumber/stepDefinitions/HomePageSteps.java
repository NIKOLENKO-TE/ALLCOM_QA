package allcom_cucumber.stepDefinitions;

import allcom_testng.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static allcom_testng.pages.ApplicationManager.app;
import static allcom_testng.tests.TestBase.driver;

public class HomePageSteps {

    @Given("User open HOME_PAGE and choose language to EN")
    public void user_launches_the_browser_and_lang_en() {
        app.init();
    }


    @Then("User verifies Home Page title Auctions")
    public void verify_HomePage_title_auctions() {
        new HomePage(driver).isHomePageTitleAuctionsDisplayed();
    }

    @And("User close the browser")
    public void userCloseBrowser() {
        app.stop();
    }
}