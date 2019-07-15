package ivan.polahniuk.ivanLogos.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter

public class ProductRequest {
    @NotBlank
    @Size(min = 4)
    private String name;
    private String description;
    @NotNull
    @Positive
    private Integer price;
    @NotNull
    private Long subCategoryId;
    private Long cityId;
    @NotNull
    private Long userId;

}
