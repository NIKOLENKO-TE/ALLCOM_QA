package allcom_restassured.Login;

import allcom_restassured.TestBaseRA;
import allcom_restassured_dto.AuthRequestDTO;
import allcom_restassured_dto.AuthResponseDTO;
import allcom_restassured_dto.ErrorDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static allcom_restassured_dto.AuthRequestDTO.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

public class LoginAdminTestsRA extends TestBaseRA {
    @BeforeMethod
    public void setup() {
        AuthResponseDTO dto =
                given()
                        .contentType(ContentType.JSON)
                        .body(valid())
                        .when().post("/auth/login")
                        .then()
                        .assertThat().statusCode(200)
                        .extract()
                        .response()
                        .as(AuthResponseDTO.class);
        TestBaseRA.setTokenAdmin(dto.getToken());
    }
    // Извлекаем токен из ответа и устанавливаем его в качестве токена админа
    @Test
    public void loginAdminPositiveTest() {
        AuthResponseDTO dto =
                given()
                        .contentType(ContentType.JSON)
                        .body(valid())
                        .when().post("/auth/login")
                        .then()
                        .log().all()
                        .assertThat().statusCode(200)
                        .extract().response()
                        .as(AuthResponseDTO.class);
        TestBaseRA.setTokenAdmin(dto.getToken());
    }
    // Извлекаем токен из кук и устанавливаем его в качестве токена админа
    @Test
    public void loginAdminPositiveTestCookie() {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(valid())
                        .when().post("/auth/login")
                        .then()
                        .log().all()
                        .assertThat().statusCode(200)
                        .extract().response();
        String token = response.detailedCookie("authorization").getValue();
        TestBaseRA.setTokenAdmin(token);
    }

    @Test
    public void loginAdminSuccessTest() {
        given()
                .contentType("application/json")
                .body(valid())
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
                .body(invalidEmail())
                .when().post("/auth/login")
                .then()
                .assertThat().statusCode(401)
                .extract()
                .response()
                .as(AuthResponseDTO.class);
    }

    @Test
    public void loginAdminInvalidPasswordNegativeTest() {
        given()
                .contentType("application/json")
                .body(invalidPassword())
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
                .contentType(ContentType.JSON)
                .body(invalidLowerCasePassword())
                .when().post("/auth/login")
                .then()
                .assertThat().statusCode(400);
    }

    @Test
    public void loginNonExistentUserNegativeTestMessage() {
        ErrorDTO errorDTO =
                given()
                        .body(nonExistentUser())
                        .contentType(ContentType.JSON)
                        .when().post("/auth/login")
                        .then()
                        .assertThat().statusCode(401)
                        .extract()
                        .response()
                        .as(ErrorDTO.class);
        logger.info(errorDTO.getMessage().toString());
        Assert.assertEquals(errorDTO.getMessage(), "User with email " + nonExistentUser().getEmail() + " not found!");
    }

    @Test
    public void loginAdminInvalidEmailNegativeTestMessage() {
        ErrorDTO errorDTO =
                given()
                        .contentType(ContentType.JSON)
                        .body(invalidEmail())
                        .when().post("/auth/login")
                        .then()
                        .assertThat().statusCode(401)
                        .extract()
                        .response()
                        .as(ErrorDTO.class);
        Assert.assertEquals(errorDTO.getMessage(), "User with email " + AuthRequestDTO.invalidEmail().getEmail() + " not found!");
    }

    @Test
    public void invalidEmailFormatTest() {
        ErrorDTO errorDTO =
                given()
                        .contentType(ContentType.JSON)
                        .body(incorrectEmailFormat())
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
                .body(nonExistentUser())
                .contentType(ContentType.JSON)
                .when().post("/auth/login")
                .then()
                .assertThat().statusCode(401)
                .assertThat().body("message", equalTo("User with email " + nonExistentUser().getEmail() + " not found!"));
    }
}
