package ivan.polahniuk.ivanLogos.dto.response;

import ivan.polahniuk.ivanLogos.entity.SubCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SubCategoryResponse {

    private Long id;
    private String name;
    private CategoryResponse category;

    public SubCategoryResponse(SubCategory subCategory) {
        id = subCategory.getId();
        name = subCategory.getName();
        category = new CategoryResponse(subCategory.getCategory());
    }
}
