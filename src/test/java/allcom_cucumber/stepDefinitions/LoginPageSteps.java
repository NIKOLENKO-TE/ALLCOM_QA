package allcom_cucumber.stepDefinitions;

import allcom_selenium.pages.BasePage;
import allcom_selenium.pages.homePage.HomePage;
import allcom_selenium.pages.login.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static allcom_selenium.pages.ApplicationManager.app;
import static allcom_selenium.pages.homePage.HomePage.HOME_PAGE_URL;
import static allcom_selenium.pages.login.LoginPage.*;

public class LoginPageSteps {
    WebDriver driver = app.getDriver();
    BasePage basePage = new BasePage(driver);
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);
    private static final Duration WAIT_SECONDS = Duration.ofSeconds(5);
    @And("User clicks on LogIn link")
    public void click_on_login_link() {
        homePage.clickOnLoginLink();
    }
    @And("User enters valid CLIENT_CHECKED username and password")
    public void enter_valid_client_checked_username_password() {
        loginPage.login(
                CLIENT_CHECKED.getUsername(),
                CLIENT_CHECKED.getPassword());
    }
    @And("User enters valid CLIENT_UNCHECKED username and password")
    public void enter_valid_client_unchecked_username_password() {
        loginPage.login(
                CLIENT_UNCHECKED.getUsername(),
                CLIENT_UNCHECKED.getPassword());
    }

    @And("User enters valid ADMIN username and password")
    public void enter_valid_admin_username_password() {
        loginPage.login(
                ADMIN.getUsername(),
                ADMIN.getPassword());
    }

    @And("User clicks on Log In button")
    public void click_on_login_button() {
        new WebDriverWait(driver, WAIT_SECONDS).until(ExpectedConditions.elementToBeClickable(loginPage.signInButtonText));
        loginPage.clickOnLoginButton();
    }

    @Then("User is still on Login Page")
    public void isLoginHeaderPresent() {
        basePage.isElementPresent(loginPage.isLoginHeaderPresent(), true);
    }
    @And("User is redirected on Home Page?")
    public void userIsRedirectedOnHomePage() {
        String expectedUrl = HOME_PAGE_URL;
        if (!expectedUrl.endsWith("/")) {
            expectedUrl = expectedUrl + "/";
        }
        new WebDriverWait(driver, WAIT_SECONDS).until(ExpectedConditions.urlToBe(expectedUrl));
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.equals(expectedUrl)) {
            Assert.fail("User is not redirected on Home Page");
        }
    }
    @Then("User is redirected on Login Page?")
    public void userIsRedirectedOnLoginPage() {
        String expectedUrl = LOGIN_PAGE_URL;
        String currentUrl = driver.getCurrentUrl();
        new WebDriverWait(driver, WAIT_SECONDS).until(ExpectedConditions.urlToBe(expectedUrl));
        Assert.assertEquals(currentUrl, expectedUrl, "User is not redirected on Home Page");
    }
    @Then("User is Logged in?")
    public void userIsLoggedIn() {
        loginPage.isUserLoggedIn(true);
    }
    @Then("User logged out")
    public void loggedOutUser() {
        loginPage.userLoggedOut();
    }
    @Then("User is Logged out?")
    public void userIsLoggedOut() {
        loginPage.isUserLoggedIn(false);
    }

    @And("User enters valid CLIENT_BLOCKED username and password")
    public void userEntersValidCLIENT_BLOCKEDUsernameAndPassword() {
        loginPage.login(
                CLIENT_BLOCKED.getUsername(),
                CLIENT_BLOCKED.getPassword());
    }

    @And("User enters valid CLIENT_NON_EXISTING username and password")
    public void userEntersValidCLIENT_NON_EXISTINGUsernameAndPassword() {
        loginPage.login(
                CLIENT_NON_EXISTING.getUsername(),
                CLIENT_NON_EXISTING.getPassword());
    }
}