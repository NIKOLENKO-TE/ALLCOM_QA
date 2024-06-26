package allcom_selenium.pages;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProviderClass {

    private Object[][] readDataFromCSV(String fileName) {
        String filePath = "./src/test/resources/csv_files_for_dataProviders/" + fileName;
        List<Object[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                data.add(line);
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return data.toArray(new Object[data.size()][]);
    }

    @DataProvider(name = "validLoginData")
    public Object[][] getValidLoginData() {
        return readDataFromCSV("email_password_valid.csv");
    }

    @DataProvider(name = "validNewUserRegisterDataFromFile")
    public Object[][] getValidNewUserRegisterData() {
        return readDataFromCSV("register_new_user_valid.csv");
    }

    @DataProvider(name = "validNewFirmaRegisterDataFromFile")
    public Object[][] getValidNewFirmaRegisterData() {
        return readDataFromCSV("register_new_firma_valid.csv");
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() {
        return readDataFromCSV("email_password_invalid.csv");
    }

    @DataProvider(name = "invalidTextData")
    public Object[][] getInvalidTextNameData() {
        return readDataFromCSV("name_invalid.csv");
    }

    @DataProvider(name = "invalidPhoneData")
    public Object[][] getInvalidPhoneData() {
        return readDataFromCSV("phone_invalid.csv");
    }

    @DataProvider(name = "invalidEMailData")
    public Object[][] getInvalidEmailData() {
        return readDataFromCSV("email_invalid.csv");
    }

    @DataProvider(name = "invalidPasswordData")
    public Object[][] getInvalidPassword() {
        return readDataFromCSV("password_invalid.csv");
    }

    @DataProvider(name = "invalidCompanyNameTextData")
    public Object[][] getInvalidCompanyNameData() {
        return readDataFromCSV("company_name_invalid.csv");
    }

    @DataProvider(name = "invalidTaxData")
    public Object[][] getInvalidTaxData() {
        return readDataFromCSV("tax_invalid.csv");
    }

    @DataProvider(name = "invalidPostalData")
    public Object[][] getInvalidPostalData() {
        return readDataFromCSV("postal_invalid.csv");
    }

    @DataProvider(name = "invalidStreetData")
    public Object[][] getInvalidStreetData() {
        return readDataFromCSV("street_invalid.csv");
    }

    @DataProvider(name = "invalidHouseNumberData")
    public Object[][] getInvalidBuildingData() {
        return readDataFromCSV("houseNumber_invalid.csv");
    }

    @DataProvider(name = "invalidPositionData")
    public Object[][] getInvalidPositionData() {
        return readDataFromCSV("position_invalid.csv");
    }

    @DataProvider(name = "invalidSearchData")
    public Object[][] getInvalidSearchData() {
        return readDataFromCSV("search_invalid.csv");
    }
}