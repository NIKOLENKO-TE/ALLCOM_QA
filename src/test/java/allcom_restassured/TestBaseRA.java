package allcom_restassured;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBaseRA {
    public static String TOKEN_ADMIN;
    @BeforeMethod
    public void init() {
        RestAssured.baseURI = "http://allcom.itvm.com.ua:8080";
        RestAssured.basePath = "/api";
    }
    public static void setTokenAdmin(String token) {
        TOKEN_ADMIN = token;
    }
}
