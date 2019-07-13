package ivan.polhniuk.ivanlogos.dto.response;

import ivan.polhniuk.ivanlogos.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductMinResponse {

    private Long id;
    private String name;
    private Integer price;

    public ProductMinResponse(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
    }
}
