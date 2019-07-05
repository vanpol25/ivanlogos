package ivan.polhniuk.ivanlogos.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter

public class CategoryRequest {

    @NotBlank
    private String name;

}
