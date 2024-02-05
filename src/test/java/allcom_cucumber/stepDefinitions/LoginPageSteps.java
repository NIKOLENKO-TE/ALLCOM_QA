package allcom_cucumber.stepDefinitions;

import allcom_cucumber.pages.HomePage;
import allcom_cucumber.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static allcom_cucumber.pages.BasePage.driver;

public class LoginPageSteps {

    @And("User clicks on LogIn link")
    public void click_on_login_link() {
        new HomePage(driver).clickOnLoginLink();
    }

    @And("User enters valid username and password")
    public void enter_valid_username_password() {
        new LoginPage(driver).enterValidUsernamePassword("admin@gmail.com", "Strongpass@1");
    }

    @And("User clicks on Log In button")
    public void click_on_login_button() {
        new LoginPage(driver).clickOnLoginButton();
    }

    @Then("User is still on Login Page")
    public void check_navigation_to_LoginPage() {
        new LoginPage(driver).isLoginHeaderPresent();
    }
}