package allcom_restassured_dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class AuthRequestDTO {
    private String email;
    private String password;

    public static AuthRequestDTO valid() {
        return AuthRequestDTO.builder().email("james-smith@mail.com").password("Qwerty007!").build();
    }

    public static AuthRequestDTO invalidEmail() {
        return AuthRequestDTO.builder().email("james_@mail.com").password("Qwerty007!").build();
    }

    public static AuthRequestDTO nonExistentUser() {
        return AuthRequestDTO.builder().email("non_existing_user_email@mail.com").password("Non_existing_user_password_007!").build();
    }

    public static AuthRequestDTO invalidPassword() {
        return AuthRequestDTO.builder().email("james-smith@mail.com").password("wwerty007!").build();
    }

    public static AuthRequestDTO invalidLowerCasePassword() {
        return AuthRequestDTO.builder().email("james-smith@mail.com").password("qwerty007!").build();
    }

    public static AuthRequestDTO incorrectEmailFormat() {
        return AuthRequestDTO.builder().email("myemail.com").password("Password007!").build();
    }
}