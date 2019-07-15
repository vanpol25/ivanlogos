package ivan.polahniuk.ivanLogos.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter

public class FileRequest {

    @NotNull
    @NotBlank
    private String data;
    private String fileName;

}
