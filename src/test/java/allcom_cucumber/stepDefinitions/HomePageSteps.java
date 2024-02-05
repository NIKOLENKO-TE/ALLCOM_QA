package allcom_cucumber.stepDefinitions;

import allcom_cucumber.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static allcom_cucumber.pages.BasePage.driver;

public class HomePageSteps {

    @Given("User launches the browser")
    public void user_launches_the_browser() {
        new HomePage(driver).launchBrowser();
    }

    @When("User opens ALLCOM Home Page")
    public void open_allcom_HomePage() {
        new HomePage(driver).openURL();
    }

    @Then("User verifies Home Page title Auctions")
    public void verify_HomePage_title_auctions() {
        new HomePage(driver).isHomePageTitleAuctionsDisplayed();
    }
}