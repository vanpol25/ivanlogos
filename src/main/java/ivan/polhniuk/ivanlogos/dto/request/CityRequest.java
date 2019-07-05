package ivan.polhniuk.ivanlogos.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter

public class CityRequest {
    @NotBlank
    private String name;
    @NotNull
    private Long regionId;

}
