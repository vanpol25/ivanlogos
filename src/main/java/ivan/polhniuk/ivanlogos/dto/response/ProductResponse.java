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
    private SubCategoryResponse subCategory;
    private Date date_published;
    private CityResponse city;
    private UserResponse user;

    public ProductResponse(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        subCategory = new SubCategoryResponse(product.getSubCategory());
    }

}
