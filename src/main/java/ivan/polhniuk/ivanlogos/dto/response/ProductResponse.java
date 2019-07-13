package ivan.polhniuk.ivanlogos.dto.response;

import ivan.polhniuk.ivanlogos.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter

public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Long subCategoryId;
    private Date date_published;
    private Long reviews;

    public ProductResponse(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        subCategoryId = product.getSubCategory().getId();
        date_published = product.getDate_published();
        reviews = product.getReviews();
    }

}
