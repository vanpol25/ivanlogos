package ivan.polahniuk.ivanLogos.dto.response;

import ivan.polahniuk.ivanLogos.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CategoryResponse {

    private Long id;
    private String name;

    public CategoryResponse(Category category) {
        id = category.getId();
        name = category.getName();
    }
}
