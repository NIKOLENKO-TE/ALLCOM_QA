package allcom.tests;

import allcom.pages.BasePage;
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

public class RestorePasswordTests extends TestBase{
    private BasePage basePage;
    private RestorePasswordPage restorePasswordPage;
    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(driver);
        restorePasswordPage = new RestorePasswordPage(driver);
        basePage.goToPage(RestorePasswordPage.restorePasswordPageURL());
        basePage.isSpanElementPreset("login_register--divide__text");
        basePage.isCurrentPage(RestorePasswordPage.restorePasswordPageURL());
    }
    @Test
    public void restoreWithValidEmail() {
        restorePasswordPage.emailToRestore("admin@gmail.com");
        basePage.clickOnButton("button_restore");
        Assert.assertTrue(basePage.errorValidationIsPresent(false));
        basePage.isCurrentPage(RestorePasswordWaitPage.restorePasswordWaitPageURL());
    }

    @DataProvider(name = "invalidEmailData")
    public Object[][] getInvalidEmailData() throws IOException {
        List<Object[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("./src/test/resources/email_invalid.csv"))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                System.out.println("Email: " + line[0]);
                data.add(new Object[]{line[0]});
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return data.toArray(new Object[0][]);
    }
    @Test(dataProvider = "invalidEmailData")
    public void restoreWithInvalidEmail(String email) {
        restorePasswordPage.emailToRestore(email);
        basePage.clickOnButton("button_restore");
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
}
