package ivan.polahniuk.ivanLogos.dto.response;

import ivan.polahniuk.ivanLogos.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductResponse {

    private Long id;
    private String name;
    private Integer price;
    private String mainImg;

    public ProductResponse(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        mainImg = product.getMainImg();
    }
}
