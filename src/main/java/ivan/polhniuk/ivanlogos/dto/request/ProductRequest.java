package ivan.polhniuk.ivanlogos.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductRequest {

    private String name;
    private String description;
    private Integer price;
    private Long subCategoryId;

}
