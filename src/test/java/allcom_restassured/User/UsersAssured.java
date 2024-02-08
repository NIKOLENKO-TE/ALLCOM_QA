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
        System.out.println("TOTAL QUANTITY: [" + totalUsersCount + "]");

        Map<String, Long> rolesCount = response.getContent().stream()
                .collect(Collectors.groupingBy(user -> user.getRole() == null ? "NO ROLE" : user.getRole(), Collectors.counting()));

        Map<String, Long> orderedRolesCount = new LinkedHashMap<>();
        orderedRolesCount.put("ADMIN", rolesCount.getOrDefault("ADMIN", 0L));
        orderedRolesCount.put("STOREKEEPER", rolesCount.getOrDefault("STOREKEEPER", 0L));
        orderedRolesCount.put("CLIENT", rolesCount.getOrDefault("CLIENT", 0L));

        for (Map.Entry<String, Long> entry : orderedRolesCount.entrySet()) {
            System.out.println("ROLE: [" + entry.getKey() + "]: {" + entry.getValue() + "}");
        }
        System.out.println("==================================================================================");
        for (UserDTO user : response.getContent()) {
            System.out.println("USER ID: [" + user.getId() + "], EMAIL: [" + user.getEmail() + "], CHECKED: [" + user.isChecked() + "], BLOCKED: [" + user.isBlocked() + "], ROLE: [" + user.getRole() + "]");
        }
    }
    @Test
    public void testGetTokenAdmin() {
        String token = TestBaseRA.getTokenAdmin();
        System.out.println("Token: " + token);
    }
}