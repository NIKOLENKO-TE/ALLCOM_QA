package allcom_restassured.Login;

import allcom_restassured.TestBaseRA;
import allcom_restassured_dto.CategoriesDTO;
import io.restassured.common.mapper.TypeRef;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetAllCategoriesTestsRestAssured extends TestBaseRA {

    @Test
    public void getAllCategoriesTest() {
        System.out.println("User successfully logged in with Admin token: " + TestBaseRA.getAdminToken());
        List<CategoriesDTO> categoriesList = given()
                .header("Authorization", TestBaseRA.getAdminToken())
                .get("/categories/all")
                .then()
                .assertThat().statusCode(200)
                .extract().body().as(new TypeRef<List<CategoriesDTO>>() {
                });
        for (CategoriesDTO categories : categoriesList) {
            System.out.println("ID: [" + categories.getId() + "], NAME: [" + categories.getNameRu() + "]");
            System.out.println("---------------------------------------------------");
        }
    }

    @Test
    public void getAllCategoriesWithoutAnyTokenTest() {
        List<CategoriesDTO> categoriesList = given()
                .get("/categories/all")
                .then()
                .assertThat().statusCode(200)
                .extract().body().as(new TypeRef<List<CategoriesDTO>>() {
                });
        Assert.assertFalse(categoriesList.isEmpty(), "Categories list is empty");
    }
}