package allcom_cucumber.stepDefinitions;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.HomePage;
import allcom_testng.pages.login.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static allcom_testng.pages.ApplicationManager.app;
import static allcom_testng.pages.login.LoginPage.*;

public class LoginPageSteps {
    WebDriver driver = app.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    BasePage basePage = new BasePage();
    private static final Duration WAIT_SECONDS = Duration.ofSeconds(5);
    @And("User clicks on LogIn link")
    public void click_on_login_link() {
        new HomePage(driver).clickOnLoginLink();
    }
    @And("User enters valid CLIENT_CHECKED username and password")
    public void enter_valid_client_checked_username_password() {
        new LoginPage(driver).login(
                CLIENT_CHECKED.getUsername(),
                CLIENT_CHECKED.getPassword());
    }
    @And("User enters valid CLIENT_UNCHECKED username and password")
    public void enter_valid_client_unchecked_username_password() {
        new LoginPage(driver).login(
                CLIENT_UNCHECKED.getUsername(),
                CLIENT_UNCHECKED.getPassword());
    }

    @And("User enters valid ADMIN username and password")
    public void enter_valid_admin_username_password() {
        new LoginPage(driver).login(
                ADMIN.getUsername(),
                ADMIN.getPassword());
    }

    @And("User clicks on Log In button")
    public void click_on_login_button() {
        LoginPage loginPage = new LoginPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, WAIT_SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.signInButtonText));
        loginPage.clickOnLoginButton();
    }

    @Then("User is still on Login Page")
    public void isLoginHeaderPresent() {
        new LoginPage(driver).isLoginHeaderPresent();
    }
    @And("User is redirected on Home Page?")
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
    @Then("User is Logged in?")
    public void userIsLoggedIn() {
        loginPage.isUserLoggedIn(true);
    }

    @Then("User is Logged out?")
    public void userIsLoggedOut() {
        loginPage.isUserLoggedIn(false);
    }
    @Then("User logged out")
    public void loggedOutUser() {
       loginPage.userLoggedOut();
    }
    @And("User enters valid CLIENT_BLOCKED username and password")
    public void userEntersValidCLIENT_BLOCKEDUsernameAndPassword() {
        new LoginPage(driver).login(
                CLIENT_BLOCKED.getUsername(),
                CLIENT_BLOCKED.getPassword());
    }

    @And("User enters valid CLIENT_NON_EXISTING username and password")
    public void userEntersValidCLIENT_NON_EXISTINGUsernameAndPassword() {
        new LoginPage(driver).login(
                CLIENT_NON_EXISTING.getUsername(),
                CLIENT_NON_EXISTING.getPassword());
    }
}