package ivan.polhniuk.ivanlogos.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Getter
@Setter

public class ProductRequest {
    @NotBlank
    @Size(min = 4)
    private String name;
    private String description;
    @NotNull
    private Integer price;
//    @NotNull
//    private Date date_published;
    @NotNull
    private Long subCategoryId;
    @NotNull
    private Long cityId;
    @NotNull
    private Long userId;

}
