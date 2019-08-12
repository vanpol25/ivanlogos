package ivan.polahniuk.ivanLogos.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter

public class UserRequest {

    @NotBlank
    @Size(min = 4, max = 30)
    private String username;

    private String email;

    @NotBlank
    @Size(min = 8, max = 30)
    private String password;

    private String phoneNumber;

    @NotNull
    private Long cityId;

}