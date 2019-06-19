package ivan.polhniuk.ivanlogos.dto.response;

import ivan.polhniuk.ivanlogos.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CategoryResponse {
    private String name;
    private Long id;

    public CategoryResponse(Category category) {
        name = category.getName();
        id = category.getId();
    }
}
