package allcom_restassured;

import allcom_restassured_dto.AuthRequestDTO;
import allcom_restassured_dto.AuthResponseDTO;
import allcom_selenium.TestBaseSE;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestBaseRA {
    public static String TOKEN_ADMIN;
    public static Logger logger = LoggerFactory.getLogger(TestBaseSE.class);
    @BeforeMethod
    public void init() {
        RestAssured.baseURI = "http://allcom.itvm.com.ua:8080";
        RestAssured.basePath = "/api";
        setTokenAdmin(getAdminToken());
    }

    public static void setTokenAdmin(String token) {
        TOKEN_ADMIN = token;
    }

    public static String getTokenAdmin() {
        return TOKEN_ADMIN;
    }
    public static String getAdminToken() {
        return given()
                .contentType("application/json")
                .body(AuthRequestDTO.valid())
                .when().post("/auth/login")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .response()
                .as(AuthResponseDTO.class)
                .getToken();
    }
}