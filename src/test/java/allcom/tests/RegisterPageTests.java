package allcom.tests;

import allcom.pages.BasePage;
import allcom.pages.LoginPage;
import allcom.pages.RegisterPage;
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

public class RegisterPageTests extends TestBase{
    private BasePage basePage;
    private RegisterPage registerPage;
    @BeforeMethod
    public void precondition() {
        basePage = new BasePage(driver);
        registerPage = new RegisterPage(driver);
        basePage.goToPage(RegisterPage.registerPageURL());
        basePage.isElementPreset("Create Account");
        basePage.isCurrentPage(RegisterPage.registerPageURL());
    }
    @Test
    public void registerClientWithValidData() {
        registerPage.registerClient(
                "Ivanov",
                "Ivan",
                "160-5945-111",
                "admin@gmail.com",
                "Qwertyuiop@1",
                "Qwertyuiop@1");
        basePage.setCheckbox("checkbox_read_terms");
        basePage.clickOnButton("button_register");
        Assert.assertTrue(basePage.errorValidationIsPresent(false));
    }
    @Test
    public void registerFirmaWithValidData() {
        basePage.setCheckboxFirma();
        registerPage.registerFirma(
                "Ivanov",
                "Ivan",
                "160-5945-111",
                "admin@gmail.com",
                "Qwertyuiop@1",
                "Qwertyuiop@1",
                "Firma",
                "Director",
                "1234567890",
                "12459",
                "Berlin",
                "Street",
                "79");
        basePage.setCheckbox("checkbox_read_terms");
        basePage.clickOnButton("button_register");
        Assert.assertTrue(basePage.errorValidationIsPresent(false));
    }
    @DataProvider(name = "invalidTextData")
    public Object[][] getInvalidTextNameData() throws IOException {
        List<Object[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("./src/test/resources/name_invalid.csv"))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                System.out.println("Text Data: " + line[0] + (line.length > 1 ? line[1] : ""));
                data.add(new Object[]{line[0]});
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return data.toArray(new Object[0][]);
    }

    @Test(dataProvider = "invalidTextData")
    public void validateFirstNameWithIncorrectData(String invalidFirstName) {
        registerPage.type(registerPage.getFirstNameField(), invalidFirstName);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
    @Test(dataProvider = "invalidTextData")
    public void validateLastNameWithIncorrectData(String invalidLastName) {
        registerPage.type(registerPage.getLastNameField(), invalidLastName);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
    @DataProvider(name = "invalidPhoneData")
    public Object[][] getInvalidPhoneData() throws IOException {
        List<Object[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("./src/test/resources/phone_invalid.csv"))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                System.out.println("Phone Number: " + line[0] + (line.length > 1 ? line[1] : ""));
                data.add(new Object[]{line[0]});
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return data.toArray(new Object[0][]);
    }
    @Test(dataProvider = "invalidPhoneData")
    public void validatePhoneWithIncorrectData(String invalidPhoneNumber) {
        registerPage.type(registerPage.getPhoneNumberField(), invalidPhoneNumber);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
    @DataProvider(name = "invalidEMailData")
    public Object[][] getInvalidEmailData() throws IOException {
        List<Object[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("./src/test/resources/email_invalid.csv"))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                System.out.println("Email: " + line[0] + (line.length > 1 ? line[1] : ""));
                data.add(new Object[]{line[0]});
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return data.toArray(new Object[0][]);
    }
    @Test(dataProvider = "invalidEMailData")
    public void validateEmailWithIncorrectData(String invalidEmail) {
        registerPage.type(registerPage.getEmailField(), invalidEmail);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
    @DataProvider(name = "invalidPasswordData")
    public Object[][] getInvalidPassword() throws IOException {
        List<Object[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("./src/test/resources/password_invalid.csv"))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                System.out.println("Email: " + line[0] + (line.length > 1 ? line[1] : ""));
                data.add(new Object[]{line[0]});
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return data.toArray(new Object[0][]);
    }
    @Test(dataProvider = "invalidPasswordData")
    public void validatePasswordWithIncorrectData(String invalidPassword) {
        registerPage.type(registerPage.getPasswordField(), invalidPassword);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
    @Test(dataProvider = "invalidPasswordData")
    public void validatePasswordConfirmWithIncorrectData(String invalidPasswordConfirm) {
        registerPage.type(registerPage.getPasswordConfirmField(), invalidPasswordConfirm);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
    @Test
    public void validateComparePasswordsEqualityPositive() {
        String password = "Qwertyuiop@1";
        String passwordConfirm = "Qwertyuiop@1";
        registerPage.type(registerPage.getPasswordField(), password);
        registerPage.type(registerPage.getPasswordConfirmField(), passwordConfirm);
        Assert.assertTrue(basePage.errorValidationIsPresent(false));
    }
    @Test
    public void validateComparePasswordsEqualityNegative() {
        String password = "Qwertyuiop@1";
        String passwordConfirm = "Qwertyuiop@2";
        registerPage.type(registerPage.getPasswordField(), password);
        registerPage.type(registerPage.getPasswordConfirmField(), passwordConfirm);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }

    @Test(dataProvider = "invalidTextData")
    public void validateCompanyNameWithIncorrectData(String invalidCompanyName) {
        basePage.setCheckboxFirma();
        registerPage.type(registerPage.getCompanyNameField(), invalidCompanyName);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
@Test(dataProvider = "invalidTextData")
public void validateCompanyPositionWithIncorrectData(String invalidCompanyPosition) {
    basePage.setCheckboxFirma();
    registerPage.type(registerPage.getPositionField(), invalidCompanyPosition);
    Assert.assertTrue(basePage.errorValidationIsPresent(true));
}
@DataProvider(name = "invalidTaxData")
public Object[][] getInvalidTaxData() throws IOException {
    List<Object[]> data = new ArrayList<>();
    try (CSVReader reader = new CSVReader(new FileReader("./src/test/resources/tax_invalid.csv"))) {
        String[] line;
        while ((line = reader.readNext()) != null) {
            System.out.println("Tax Number: " + line[0] + (line.length > 1 ? line[1] : ""));
            data.add(new Object[]{line[0]});
        }
    } catch (CsvValidationException e) {
        throw new RuntimeException(e);
    }
    return data.toArray(new Object[0][]);
}
    @Test(dataProvider = "invalidTaxData")
    public void validateTaxWithIncorrectData(String invalidTaxNumber) {
        basePage.setCheckboxFirma();
        registerPage.type(registerPage.getTaxNumberField(), invalidTaxNumber);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
    @DataProvider(name = "invalidPostalData")
    public Object[][] getInvalidPostalData() throws IOException {
        List<Object[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("./src/test/resources/postal_invalid.csv"))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                System.out.println("Postal Number: " + line[0] + (line.length > 1 ? line[1] : ""));
                data.add(new Object[]{line[0]});
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return data.toArray(new Object[0][]);
    }
    @Test(dataProvider = "invalidPostalData")
    public void validatePostalWithIncorrectData(String invalidPostalNumber) {
        basePage.setCheckboxFirma();
        registerPage.type(registerPage.getPostIndexField(), invalidPostalNumber);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
    @Test(dataProvider = "invalidTextData")
    public void validateCityWithIncorrectData(String invalidCity) {
        basePage.setCheckboxFirma();
        registerPage.type(registerPage.getCityField(), invalidCity);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
    @Test(dataProvider = "invalidTextData")
    public void validateStreetWithIncorrectData(String invalidStreet) {
        basePage.setCheckboxFirma();
        registerPage.type(registerPage.getStreetField(), invalidStreet);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
    @DataProvider(name = "invalidHouseNumberData")
    public Object[][] getInvalidBuildingData() throws IOException {
        List<Object[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("./src/test/resources/houseNumber_invalid.csv"))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                System.out.println("House Number: " + line[0] + (line.length > 1 ? line[1] : ""));
                data.add(new Object[]{line[0]});
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return data.toArray(new Object[0][]);
    }
    @Test(dataProvider = "invalidHouseNumberData")
    public void validateHouseNumberWithIncorrectData(String invalidHouseNumber) {
        basePage.setCheckboxFirma();
        registerPage.type(registerPage.getHouseNumberField(), invalidHouseNumber);
        Assert.assertTrue(basePage.errorValidationIsPresent(true));
    }
}
