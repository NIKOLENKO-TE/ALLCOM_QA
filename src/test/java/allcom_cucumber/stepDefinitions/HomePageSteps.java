package allcom_cucumber.stepDefinitions;

import allcom_cucumber.pages.HomePageCucumberCucumber;
import allcom_testng.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static allcom_cucumber.pages.BasePageCucumber.driver;

public class HomePageSteps {

    @Given("User launches the browser")
    public void user_launches_the_browser() {
        new HomePageCucumberCucumber(driver).launchBrowser();
    }

    @When("User opens ALLCOM Home Page")
    public void open_allcom_HomePage() {
        new HomePageCucumberCucumber(driver).openURL();
    }

    @Then("User verifies Home Page title Auctions")
    public void verify_HomePage_title_auctions() {
        new HomePageCucumberCucumber(driver).isHomePageTitleAuctionsDisplayed();
    }

    @And("User changes the language to English")
    public void userChangesTheLanguageToEnglish() {
        HomePage homePage = new HomePage(driver);
        homePage.changeLanguage("English");
    }
}