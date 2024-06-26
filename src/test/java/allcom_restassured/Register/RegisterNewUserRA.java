package allcom_restassured.Register;

import allcom_restassured.User.UsersAssured;

import allcom_restassured.TestBaseRA;
import allcom_restassured_dto.AuthRequestDTO;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static io.restassured.RestAssured.given;

public class RegisterNewUserRA extends TestBaseRA {

  @Test(invocationCount = 10, threadPoolSize = 1)
  public void registerNewRandomUserTest() {
    logger.info("ADMIN TOKEN: {}", TestBaseRA.getTokenAdmin());
    long uniqueSuffix = System.currentTimeMillis();
    AuthRequestDTO newUser = AuthRequestDTO.exampleValidFieldNewUser();
    newUser.setEmail("email_random_" + uniqueSuffix + "@mail.com");
    given().contentType(ContentType.JSON).body(newUser).log().all().when().post("/auth/register").then().assertThat().statusCode(201);
  }

  @Test
  public void registerNewRandomMultipleUserTest() {
    UsersAssured usersAssured = new UsersAssured();

    logger.info("Before registration:");
    usersAssured.getAllUsersTest();

    logger.info("ADMIN TOKEN: {}", TestBaseRA.getTokenAdmin());
    ExecutorService executorService = Executors.newFixedThreadPool(10); //reducing the number of threads to 10
    AtomicLong uniqueSuffix = new AtomicLong(System.currentTimeMillis());
    for (int i = 0; i < 100; i++) { // Number of users we want to register
      executorService.submit(() -> {
        AuthRequestDTO newUser = AuthRequestDTO.exampleValidFieldNewUser();
        newUser.setEmail("nikolenkote_" + uniqueSuffix.incrementAndGet() + "_random@mail.com");
        given().contentType(ContentType.JSON).body(newUser).when().post("/auth/register").then().assertThat().statusCode(201);
        try {
          Thread.sleep(100); // добавляем задержку в 100 миллисекунд между запросами
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }
    executorService.shutdown();
    try {
      // Ожидание завершения всех задач в течение 1 часа
      if (!executorService.awaitTermination(1, TimeUnit.HOURS)) {
        logger.info("Not all tasks finished");
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    logger.info("After registration:");
    usersAssured.getAllUsersTest();
  }
}
