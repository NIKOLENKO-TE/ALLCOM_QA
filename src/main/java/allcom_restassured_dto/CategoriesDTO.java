package allcom_restassured_dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class CategoriesDTO {
    private int id;
    private String nameRu; //"Утюг"
    private String nameDe; //"Bügel"
    private String nameEn; //"Iron"
    private int parentId;
}
