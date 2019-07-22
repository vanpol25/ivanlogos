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
    private Long regionId;

    @NotNull
    private PaginationRequest paginationRequest;

}
