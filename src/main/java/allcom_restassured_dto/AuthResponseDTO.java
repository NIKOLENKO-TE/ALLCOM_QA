package allcom_restassured_dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class AuthResponseDTO {
    private String token;

    public String getToken() {
        return this.token;
    }
}
