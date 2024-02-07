package allcom_restassured.Login;

import allcom_restassured_dto.AuthRequestDTO;
import allcom_restassured_dto.AuthResponseDTO;
import allcom_restassured_dto.ErrorDTO;
import allcom_restassured.TestBaseRA;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

public class LoginAdminTestsRestAssured extends TestBaseRA {
    @BeforeMethod
    public void setup() {
        AuthResponseDTO dto =
                given()
                        .contentType("application/json")
                        .body(AuthRequestDTO.valid())
                        .when().post("/auth/login")
                        .then()
                        .assertThat().statusCode(200)
                        .extract()
                        .response()
                        .as(AuthResponseDTO.class);
        TestBaseRA.setTokenAdmin(dto.getToken());
    }
    @Test
    public void loginAdminPositiveTest() {
        AuthResponseDTO dto =
                given()
                        .contentType("application/json")
                        .body(AuthRequestDTO.valid())
                        .when().post("/auth/login")
                        .then()
                        .assertThat().statusCode(200)
                        .extract()
                        .response()
                        .as(AuthResponseDTO.class);
        TestBaseRA.setTokenAdmin(dto.getToken());
    }

    @Test
    public void loginAdminSuccessTest() {
        String responseToken = given()
                .contentType("application/json")
                .body(AuthRequestDTO.valid())
                .when().post("/auth/login")
                .then()
                .assertThat().statusCode(200)
                .body(containsString("token"))
                .extract().path("token");
    }

    @Test
    public void loginAdminInvalidEmailNegativeTest() {
        given()
                .contentType("application/json")
                .body(AuthRequestDTO.invalidEmail())
                .when().post("/auth/login")
                .then()
                .assertThat().statusCode(404) //TODO: change to 400 after fixing the bug
                .extract()
                .response()
                .as(AuthResponseDTO.class);
    }

    @Test
    public void loginAdminInvalidPasswordNegativeTest() {
        given()
                .contentType("application/json")
                .body(AuthRequestDTO.invalidPassword())
                .when().post("/auth/login")
                .then()
                .assertThat().statusCode(400)
                .extract()
                .response()
                .as(AuthResponseDTO.class);
    }

    @Test
    public void loginAdminInvalidLowerCasePasswordNegativeTest() {
        given()
                .contentType("application/json")
                .body(AuthRequestDTO.invalidLowerCasePassword())
                .when().post("/auth/login")
                .then()
                .assertThat().statusCode(400)
                .extract()
                .response()
                .as(AuthResponseDTO.class);
    }

    @Test
    public void loginNonExistentUserNegativeTestMessage() {
        ErrorDTO errorDTO =
                given()
                        .body(AuthRequestDTO.nonExistentUser())
                        .contentType(ContentType.JSON)
                        .when().post("/auth/login")
                        .then()
                        .assertThat().statusCode(404)
                        .extract()
                        .response()
                        .as(ErrorDTO.class);
        System.out.println(errorDTO.getMessage());
        Assert.assertEquals(errorDTO.getMessage(), "User with email " + AuthRequestDTO.nonExistentUser().getEmail() + " not found!");
    }

    @Test
    public void loginAdminInvalidEmailNegativeTestMessage() {
        ErrorDTO errorDTO =
                given()
                        .contentType("application/json")
                        .body(AuthRequestDTO.invalidEmail())
                        .when().post("/auth/login")
                        .then()
                        .assertThat().statusCode(404)
                        .extract()
                        .response()
                        .as(ErrorDTO.class);
        Assert.assertEquals(errorDTO.getMessage(), "User with email " + AuthRequestDTO.invalidEmail().getEmail() + " not found!");
    }

    @Test
    public void invalidEmailFormatTest() {
        ErrorDTO errorDTO =
                given()
                        .contentType("application/json")
                        .body(AuthRequestDTO.incorrectEmailFormat())
                        .when().post("/auth/login")
                        .then()
                        .assertThat().statusCode(400)
                        .extract()
                        .response()
                        .as(ErrorDTO.class);
        Assert.assertEquals(errorDTO.getErrors().get(0).getMessage(), "Email address must be in a valid format (e.g., user@example.com)");
    }
    @Test
    public void loginNonExistentUserNegativeTestMessage2() {
        given()
                .body(AuthRequestDTO.nonExistentUser())
                .contentType(ContentType.JSON)
                .when().post("/auth/login")
                .then()
                .assertThat().statusCode(404)
                .assertThat().body("message", equalTo("User with email " + AuthRequestDTO.nonExistentUser().getEmail() + " not found!"));
    }
}
