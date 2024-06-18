package allcom_restassured.Login;

import allcom_restassured.TestBaseRA;
import allcom_restassured_dto.CategoriesDTO;
import io.restassured.common.mapper.TypeRef;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetCategoriesTestsRA extends TestBaseRA {

    @Test
    public void getAllCategoriesTest() {
        List<CategoriesDTO> categoriesList = given()
                .get("/categories/all")
                .then()
                .assertThat().statusCode(200)
                .extract().body().as(new TypeRef<>() {
            });
        for (CategoriesDTO categories : categoriesList) {
            System.out.println("ID: [" + categories.getId() + "], NAME: [" + categories.getNameRu() + "]");
            System.out.println("---------------------------------------------------");
        }
    }

    @Test
    public void getOneCategoryByIdTest() {
        int categoryId = 15;
        CategoriesDTO category = given()
                .contentType("application/json")
                .when().get("/categories/one/" + categoryId)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .response()
                .as(CategoriesDTO.class);
        System.out.println("Request ID: [" + categoryId + "], Found ID: [" + category.getId() + "], CATEGORY NAME: [" + category.getNameRu() + "]");
    }
}