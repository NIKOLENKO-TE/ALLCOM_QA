package allcom.tests;
import allcom.pages.BasePage;
import allcom.pages.LoginPage;
import allcom.pages.RegisterPage;
import allcom.pages.RestorePasswordPage;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginTests extends TestBase {
    private BasePage basePage;
    private LoginPage loginPage;

    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        basePage.goToPage(LoginPage.loginPageURL());
        basePage.isElementPreset("Log In");
        basePage.isCurrentPage(LoginPage.loginPageURL());
    }
    @Test
    public void loginWithValidData() {
        loginPage.login("admin@gmail.com", "Qwertyuiop@1");
        basePage.clickOnButton("button_login");
        Assert.assertTrue(basePage.errorValidationIsPresent(false));
    }
    @Test
    public void loginWithInvalidEmail() {
        loginPage.login("admin@gmailcom", "Qwertyuiop@1");
        basePage.clickOnButton("button_login");
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
    @Test
    public void loginWithInvalidPassword() {
        loginPage.login("admin@gmail.com", "Qwertyuiop1");
        basePage.clickOnButton("button_login");
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() throws IOException {
        List<Object[]> data = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader("./src/test/resources/email_password_invalid.csv"))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                System.out.println("Email: " + line[0] + ", Password: " + (line.length > 1 ? line[1] : ""));
                String password = line.length > 1 ? line[1] : "";
                data.add(new Object[]{line[0], password});
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return data.toArray(new Object[0][]);
    }
    @Test(dataProvider = "invalidLoginData")
    public void loginWithInvalidData(String invalidEmail, String invalidPassword) {
        loginPage.login(invalidEmail, invalidPassword);
        basePage.clickOnButton("button_login");
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
    @Test
    public void clickOnForgotYourPasswordLabel() {
        basePage.clickOnLabel("forgot_password");
        basePage.isCurrentPage(RestorePasswordPage.restorePasswordPageURL());
    }
    @Test
    public void clickOnLoginButtonWOData() {
        basePage.clickOnButton("button_login");
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
    @Test
    public void clickOnRegisterButton() {
        basePage.clickOnButton("button_register");
        basePage.isCurrentPage(RegisterPage.registerPageURL());
    }

}