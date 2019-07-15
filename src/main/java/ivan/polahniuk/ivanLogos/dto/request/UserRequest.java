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
    @Size(min = 4)
    private String name;
//    @Email(regexp = "^[A-Z][a-z]*(\\\\s(([a-z]{1,3})|(([a-z]+\\\\')?[A-Z][a-z]*)))*$")
    private String email;
    @NotBlank
    private String password;
    private String phone_number;
    @NotNull
    private Long cityId;

}