package ivan.polahniuk.ivanLogos.dto.response;

import ivan.polahniuk.ivanLogos.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

public class ProductFullResponse {

    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Date date;
    private Long reviews;
    private SubCategoryResponse subCategory;
    private CityResponse city;
    private UserResponse user;
    private List<PhotoResponse> photos;

    public ProductFullResponse(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        date = product.getDate();
        reviews = product.getReviews();
        subCategory = new SubCategoryResponse(product.getSubCategory());
        city = new CityResponse(product.getCity());
        user = new UserResponse(product.getUser());
        photos = product.getPhotos().stream().map(PhotoResponse::new).collect(Collectors.toList());
    }

}
