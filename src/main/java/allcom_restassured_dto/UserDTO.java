package allcom_restassured_dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean checked;
    private boolean blocked;
    private String companyName;
    private String position;
    private String taxNumber;
    private Address address;
    private String role;

    @Getter
    @Setter
    @Builder
    public static class Address {
        private String postIndex;
        private String city;
        private String street;
        private String houseNumber;
    }
    public String getRole() {
        return role;
    }
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isChecked() {
        return checked;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPosition() {
        return position;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public Address getAddress() {
        return address;
    }
}