package ivan.polahniuk.ivanLogos.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter

public class RegionRequest {

    @NotBlank
    private String name;

}
