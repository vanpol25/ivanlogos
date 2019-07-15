package ivan.polahniuk.ivanLogos.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter

public class SubCategoryRequest {
    @NotBlank
    @NotNull
    private String name;
    @NotNull
    private Long categoryId;

}
