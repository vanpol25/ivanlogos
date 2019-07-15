package ivan.polahniuk.ivanLogos.dto.response;

import ivan.polahniuk.ivanLogos.entity.SubCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SubCategoryResponse {

    private Long id;
    private String name;
    private Long categoryId;

    public SubCategoryResponse(SubCategory subCategory) {
        id = subCategory.getId();
        name = subCategory.getName();
        categoryId = subCategory.getCategory().getId();
    }
}
