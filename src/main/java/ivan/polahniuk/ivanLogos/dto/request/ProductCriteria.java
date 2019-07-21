package ivan.polahniuk.ivanLogos.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter

public class ProductCriteria {
    private String name;
    private Integer minPrice;
    private Integer maxPrice;
    private Long subCategoryId;
    private Long categoryId;
    private Long cityId;

    @NotNull
    private PaginationRequest paginationRequest;

    public ProductCriteria(ProductCriteria productCriteria){
        this.name = name;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.subCategoryId = subCategoryId;
        this.categoryId = categoryId;
        this.cityId = cityId;
        this.paginationRequest = paginationRequest;
    }
}
