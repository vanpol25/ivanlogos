package ivan.polhniuk.ivanlogos.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserRequest {

    private String name;
    private String email;
    private String password;
    private String phone_number;

}