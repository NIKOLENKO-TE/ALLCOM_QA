package allcom_selenium.pages.registration;

import allcom_selenium.pages.BasePage;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static allcom_selenium.pages.homePage.HomePage.HOME_PAGE_URL;

@Getter
@Setter
public class RegisterPage extends BasePage {
  BasePage basePage;

  public static final String REGISTER_PAGE_URL = HOME_PAGE_URL + "/register";

  public RegisterPage(WebDriver driver) {
    super(driver);
    this.basePage = new BasePage(driver);
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

  public void registerFirma(String firstName, String lastName, String phoneNumber, String email, String password, String passwordConfirm, String companyName, String position, String taxNumber, String postIndex, String city, String street, String houseNumber) {
    registerClient(firstName, lastName, phoneNumber, email, password, passwordConfirm); // Reuse client registration logic
    type(companyNameField, companyName);
    type(positionField, position);
    type(taxNumberField, taxNumber);
    type(postIndexField, postIndex);
    type(cityField, city);
    type(streetField, street);
    type(houseNumberField, houseNumber);
  }

  public void clickRegisterButton() {
    clickOnElement(ElementType.DATA_TESTID, "button_register");
  }

  public void clickLoginButton() {
    clickOnElement(ElementType.DATA_TESTID, "button_login");
  }

  public void clickReadTermsCheckbox() {
    clickOnElement(ElementType.CHECKBOX, "checkbox_read_terms");
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