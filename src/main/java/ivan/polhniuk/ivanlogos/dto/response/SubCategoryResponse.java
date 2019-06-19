package ivan.polhniuk.ivanlogos.dto.response;

import ivan.polhniuk.ivanlogos.entity.SubCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SubCategoryResponse {

    private String name;
    private Long id;
    private Long categoryId;

    public SubCategoryResponse(SubCategory subCategory) {
        name = subCategory.getName();
        id = subCategory.getId();
        categoryId = subCategory.getCategory().getId();
    }
}
