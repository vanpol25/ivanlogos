package ivan.polhniuk.ivanlogos.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter

public class SubCategoryRequest {

    @NotBlank
    @Size(min = 4)
    private String name;
    @NotNull
    private Long categoryId;

}
