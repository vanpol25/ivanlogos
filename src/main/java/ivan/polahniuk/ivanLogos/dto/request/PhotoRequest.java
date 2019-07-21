package ivan.polahniuk.ivanLogos.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter

public class PhotoRequest {

    @NotBlank
    @NotNull
    private String data;
    @NotNull
    private Long productId;

}
