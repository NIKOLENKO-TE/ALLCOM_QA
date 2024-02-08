package allcom_cucumber.stepDefinitions;

import allcom_cucumber.pages.HomePageCucumberCucumber;
import allcom_cucumber.pages.LoginPageCucumber;
import allcom_testng.pages.HomePage;
import allcom_testng.pages.login.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static allcom_cucumber.pages.BasePageCucumber.driver;

public class LoginPageSteps {

    @And("User clicks on LogIn link")
    public void click_on_login_link() {
        new HomePageCucumberCucumber(driver).clickOnLoginLink();
    }
    @And("User enters valid CLIENT_CHECKED username and password")
    public void enter_valid_client_checked_username_password() {
        new LoginPageCucumber(driver).enterValidUsernamePassword("NIKOLENKOTE_200@GMAIL.COM", "Qwertyuiop@1");
    }
    @And("User enters valid CLIENT_UNCHECKED username and password")
    public void enter_valid_client_unchecked_username_password() {
        new LoginPageCucumber(driver).enterValidUsernamePassword("NIKOLENKOTE_100@GMAIL.COM", "Qwertyuiop@1");
    }

    @And("User enters valid ADMIN username and password")
    public void enter_valid_admin_username_password() {
        new LoginPageCucumber(driver).enterValidUsernamePassword("james-smith@mail.com", "Qwerty007!");
    }

    @And("User clicks on Log In button")
    public void click_on_login_button() {
        new LoginPageCucumber(driver).clickOnLoginButton();
    }

    @Then("User is still on Login Page")
    public void isLoginHeaderPresent() {
        new LoginPageCucumber(driver).isLoginHeaderPresent();
    }
    @Then("User is redirected on Home Page?")
    public void userIsRedirectedOnHomePage() {
        String expectedUrl = HomePage.homePageURL();
        if (!expectedUrl.endsWith("/")) {
            expectedUrl = expectedUrl + "/";
        }
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe(expectedUrl));
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.equals(expectedUrl)) {
            Assert.fail("User is not redirected on Home Page");
        }
    }
    @Then("User is redirected on Login Page?")
    public void userIsRedirectedOnLoginPage() {
        String expectedUrl = LoginPage.loginPageURL();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe(expectedUrl));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl, "User is not redirected on Home Page");
    }

    @Then("User is logged in?")
    public void userIsLoggedIn() {
        WebElement loginButton = driver.findElement(By.cssSelector(".header__account--btn__text"));
        String loginButtonText = loginButton.getText();
        if (loginButtonText.equals("Sign In") || loginButtonText.isEmpty()) {
            Assert.fail("User is not logged in");
        }
    }
}