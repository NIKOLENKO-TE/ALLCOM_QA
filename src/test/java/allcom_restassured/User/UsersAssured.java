package allcom_restassured.User;

import allcom_restassured.TestBaseRA;
import allcom_restassured_dto.UserDTO;
import allcom_restassured_dto.UserListResponseDTO;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class UsersAssured extends TestBaseRA {
    @Test
    public void getAllUsersTest() {
        int limit = 10000;
        UserListResponseDTO response =
                given()
                        .header("Authorization", "Bearer " + TestBaseRA.getTokenAdmin())
                        .accept(ContentType.JSON)
                        .when().get("/users/getAll?limit=" + limit)
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response()
                        .as(UserListResponseDTO.class);
        int totalUsersCount = response.getContent().size();
      logger.info("TOTAL QUANTITY: [{}]", totalUsersCount);

        Map<String, Long> rolesCount = response.getContent().stream()
                .collect(Collectors.groupingBy(user -> user.getRole() == null ? "NO ROLE" : user.getRole(), Collectors.counting()));

        Map<String, Long> orderedRolesCount = new LinkedHashMap<>();
        orderedRolesCount.put("ADMIN", rolesCount.getOrDefault("ADMIN", 0L));
        orderedRolesCount.put("STOREKEEPER", rolesCount.getOrDefault("STOREKEEPER", 0L));
        orderedRolesCount.put("CLIENT", rolesCount.getOrDefault("CLIENT", 0L));

        for (Map.Entry<String, Long> entry : orderedRolesCount.entrySet()) {
          logger.info("ROLE: [{}]: {{}}", entry.getKey(), entry.getValue());
        }
        logger.info("==================================================================================");
        for (UserDTO user : response.getContent()) {
          logger.info("USER ID: [{}], EMAIL: [{}], CHECKED: [{}], BLOCKED: [{}], ROLE: [{}]", user.getId(), user.getEmail(), user.isChecked(), user.isBlocked(), user.getRole());
        }
    }
    @Test
    public void testGetTokenAdmin() {
        String token = TestBaseRA.getTokenAdmin();
      logger.info("Token: {}", token);
    }
}