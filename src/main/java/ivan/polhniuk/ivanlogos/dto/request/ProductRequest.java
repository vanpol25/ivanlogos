package ivan.polhniuk.ivanlogos.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter

public class ProductRequest {

    private String name;
    private String description;
    private Integer price;
    private Date date_published;
    private Long subCategoryId;
    private Long cityId;
    private Long userId;

}
