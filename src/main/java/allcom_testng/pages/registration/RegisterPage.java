package allcom_testng.pages.registration;

import allcom_testng.pages.BasePage;
import allcom_testng.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class RegisterPage extends BasePage {
    BasePage basePage;

    public RegisterPage(WebDriver driver) {
        super(driver);
        this.basePage = new BasePage(driver);
    }

    public static String registerPageURL() {
        return HomePage.homePageURL() + "/register";
    }

    public BasePage getBasePage() {
        return basePage;
    }

    @FindBy(name = "firstName")
    WebElement firstNameField;
    @FindBy(name = "lastName")
    WebElement lastNameField;
    @FindBy(name = "email")
    WebElement emailField;
    @FindBy(name = "phoneNumber")
    WebElement phoneNumberField;
    @FindBy(name = "password")
    WebElement passwordField;
    @FindBy(name = "passwordConfirm")
    WebElement passwordConfirmField;
    @FindBy(name = "companyName")
    WebElement companyNameField;
    @FindBy(name = "position")
    WebElement positionField;
    @FindBy(name = "taxNumber")
    WebElement taxNumberField;
    @FindBy(name = "postIndex")
    WebElement postIndexField;
    @FindBy(name = "city")
    WebElement cityField;
    @FindBy(name = "street")
    WebElement streetField;
    @FindBy(name = "houseNumber")
    WebElement houseNumberField;

    public void registerClient(String firstName, String lastName, String phoneNumber, String email, String password, String passwordConfirm) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(emailField, email);
        type(phoneNumberField, phoneNumber);
        type(passwordField, password);
        type(passwordConfirmField, passwordConfirm);
    }

    public void registerFirma(String firstName, String lastName, String phoneNumber, String email, String password, String passwordConfirm,
                              String companyName, String position, String taxNumber, String postIndex, String city,
                              String street, String houseNumber) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(emailField, email);
        type(phoneNumberField, phoneNumber);
        type(passwordField, password);
        type(passwordConfirmField, passwordConfirm);
        type(companyNameField, companyName);
        type(positionField, position);
        type(taxNumberField, taxNumber);
        type(postIndexField, postIndex);
        type(cityField, city);
        type(streetField, street);
        type(houseNumberField, houseNumber);
    }

    public WebElement getFirstNameField() {
        return firstNameField;
    }

    public WebElement getLastNameField() {
        return lastNameField;
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getPhoneNumberField() {
        return phoneNumberField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getPasswordConfirmField() {
        return passwordConfirmField;
    }

    public WebElement getCompanyNameField() {
        return companyNameField;
    }

    public WebElement getPositionField() {
        return positionField;
    }

    public WebElement getTaxNumberField() {
        return taxNumberField;
    }

    public WebElement getPostIndexField() {
        return postIndexField;
    }

    public WebElement getCityField() {
        return cityField;
    }

    public WebElement getStreetField() {
        return streetField;
    }

    public WebElement getHouseNumberField() {
        return houseNumberField;
    }

    public void clickRegisterButton() {
        clickOnElement(BasePage.ElementType.DATA_TESTID, "button_register");
    }

    public void clickLoginButton() {
        clickOnElement(BasePage.ElementType.DATA_TESTID, "button_login");
    }

    public void clickReadTermsCheckbox() {
        clickOnElement(BasePage.ElementType.CHECKBOX, "checkbox_read_terms");
    }

    public void comparePasswordsEquality(String password, String passwordConfirm, boolean expectPasswordsEquality) {
        if (password != null && passwordConfirm != null) {
            Assert.assertEquals(password.equals(passwordConfirm), expectPasswordsEquality, "Passwords equality validation failed");
            if (password.equals(passwordConfirm)) {
                basePage.type(getPasswordField(), password);
                basePage.type(getPasswordConfirmField(), passwordConfirm);
            }
        } else {
            throw new IllegalArgumentException("Password values are null");
        }
    }
}
