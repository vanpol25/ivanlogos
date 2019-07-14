package ivan.polhniuk.ivanlogos.dto.response;

import ivan.polhniuk.ivanlogos.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter

public class ProductFullResponse {

    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Date date_published;
    private Long reviews;
    private Long subCategoryId;
    private Long cityId;
    private Long userId;

    public ProductFullResponse(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        date_published = product.getDate_published();
        reviews = product.getReviews();
        subCategoryId = product.getSubCategory().getId();
        cityId = product.getCity().getId();
        userId = product.getUser().getId();
    }

}
