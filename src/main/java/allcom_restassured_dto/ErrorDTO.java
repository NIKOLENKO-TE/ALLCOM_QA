package allcom_restassured_dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@Builder
public class ErrorDTO {
    private List<ErrorDetail> errors;
    private Object message;

    public Object getMessage() {
        return message;
    }
    public static class ErrorDetail {
        private String message;

        public String getMessage() {
            return message;
        }
    }
}
