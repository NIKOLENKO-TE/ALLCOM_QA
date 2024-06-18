package allcom_restassured.Login;

import allcom_restassured.TestBaseRA;
import allcom_restassured_dto.CategoriesDTO;
import io.restassured.common.mapper.TypeRef;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllCategoriesTestsRA extends TestBaseRA {

    @Test
    public void getAllCategoriesTest() {
        logger.info("User successfully logged in with Admin token: " + TestBaseRA.getAdminToken());
        List<CategoriesDTO> categoriesList = given()
                .header("Authorization", TestBaseRA.getAdminToken())
                .get("/categories/all")
                .then()
                .assertThat().statusCode(200)
                .extract().body().as(new TypeRef<>() {
            });
        for (CategoriesDTO categories : categoriesList) {
            logger.info("ID: [" + categories.getId() + "], NAME: [" + categories.getNameRu() + "]");
            logger.info("ID: [" + categories.getId() + "], NAME: [" + categories.getNameEn() + "]");
            logger.info("---------------------------------------------------");
        }
    }

    @Test
    public void getAllCategoriesWithoutAnyTokenTest() {
        List<CategoriesDTO> categoriesList = given()
                .get("/categories/all")
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().body().as(new TypeRef<List<CategoriesDTO>>() {
                });
        Assert.assertFalse(categoriesList.isEmpty(), "Categories list is empty");
    }
}