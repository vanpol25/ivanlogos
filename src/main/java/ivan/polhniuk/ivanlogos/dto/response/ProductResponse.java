package ivan.polhniuk.ivanlogos.dto.response;

import ivan.polhniuk.ivanlogos.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Long subCategoryId;

    public ProductResponse(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        subCategoryId = product.getSubCategory().getId();
    }

}
