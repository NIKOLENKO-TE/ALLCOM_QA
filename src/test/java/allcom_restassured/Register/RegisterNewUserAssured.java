package allcom_restassured.Register;
import allcom_restassured.User.UsersAssured;

import allcom_restassured.TestBaseRA;
import allcom_restassured_dto.AuthRequestDTO;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import static io.restassured.RestAssured.given;

public class RegisterNewUserAssured extends TestBaseRA {
//    @BeforeMethod
//    public void setup() {
//        AuthResponseDTO dto =
//                given()
//                        .contentType("application/json")
//                        .header("Authorization", "Bearer " + TestBaseRA.getTokenAdmin())
//                        .body(AuthRequestDTO.valid())
//                        .when().post("/auth/register")
//                        .then()
//                        .assertThat().statusCode(200)
//                        .extract()
//                        .response()
//                        .as(AuthResponseDTO.class);
//        System.out.println("Token used in test: " + dto.getToken());
//    }

//    @Test
//    public void registerNewUserTest() {
//        AuthRequestDTO newUser = AuthRequestDTO.exampleValidFieldNewUser();
//        ErrorDTO errorResponse =
//                given()
//                        .contentType(ContentType.JSON)
//                        .body(newUser)
//                        .when().post("/auth/register")
//                        .then()
//                        .extract()
//                        .response()
//                        .as(ErrorDTO.class);
//
//        System.out.println("Error message: " + errorResponse.getMessage());
//    }
    @Test(invocationCount = 1000, threadPoolSize = 1)
    public void registerNewRandomUserTest() {
        //System.out.println("ADMIN TOKEN: " + TestBaseRA.getTokenAdmin());
        long uniqueSuffix = System.currentTimeMillis();
        AuthRequestDTO newUser = AuthRequestDTO.exampleValidFieldNewUser();
        newUser.setEmail("nikolenkote_" + uniqueSuffix + "_random@mail.com");
        given()
                .contentType(ContentType.JSON)
                .body(newUser)
                //.log().all()
                .when().post("/auth/register")
                .then()
                .assertThat().statusCode(201);
    }

    @Test
    public void registerNewRandomMultipleUserTest() {
        UsersAssured usersAssured = new UsersAssured();

        System.out.println("Before registration:");
        usersAssured.getAllUsersTest();

        System.out.println("ADMIN TOKEN: " + TestBaseRA.getTokenAdmin());
        ExecutorService executorService = Executors.newFixedThreadPool(10); // уменьшаем количество потоков
        AtomicLong uniqueSuffix = new AtomicLong(System.currentTimeMillis());
        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                AuthRequestDTO newUser = AuthRequestDTO.exampleValidFieldNewUser();
                newUser.setEmail("nikolenkote_" + uniqueSuffix.incrementAndGet() + "_random@mail.com");
                given()
                        .contentType(ContentType.JSON)
                        .body(newUser)
                        .when().post("/auth/register")
                        .then()
                        .assertThat().statusCode(201);
                try {
                    Thread.sleep(100); // добавляем задержку в 100 миллисекунд между запросами
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();

        System.out.println("After registration:");
        usersAssured.getAllUsersTest();
    }
}
