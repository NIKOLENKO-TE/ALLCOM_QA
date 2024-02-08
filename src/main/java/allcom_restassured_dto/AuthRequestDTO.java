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
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phoneNumber;
    private boolean checked;
    private boolean blocked;
    private String companyName;
    private String position;
    private String taxNumber;
    private Address address;

    @Getter
    @Setter
    @Builder
    public static class Address {
        private String postIndex;
        private String city;
        private String street;
        private String houseNumber;
    }
    public static AuthRequestDTO exampleValidFieldNewUser() {
        return AuthRequestDTO.builder()
                .firstName("Alex")
                .lastName("Schmidt")
                .password("Qwerty007!")
                .email("alex-schmidt1@mail.com")
                .phoneNumber("+491753456755")
                .companyName("Allcom GmbH")
                .position("Purchasing manager")
                .taxNumber("3458795653")
                .address(AuthRequestDTO.Address.builder()
                        .postIndex("10176")
                        .city("Berlin")
                        .street("Alexanderplatz")
                        .houseNumber("1")
                        .build())
                .blocked(true)
                .checked(true)
                .build();
    }
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