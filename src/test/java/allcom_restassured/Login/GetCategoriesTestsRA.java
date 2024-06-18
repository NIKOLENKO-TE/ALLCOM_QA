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
          logger.info("ID: [{}], NAME: [{}]", categories.getId(), categories.getNameRu());
            logger.info("---------------------------------------------------");
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
      logger.info("Request ID: [{}], Found ID: [{}], CATEGORY NAME: [{}]", categoryId, category.getId(), category.getNameRu());
    }
}