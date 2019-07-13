package ivan.polhniuk.ivanlogos.dto.response;

import ivan.polhniuk.ivanlogos.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor

public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone_number;
    private Long cityId;


    public UserResponse(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        password = user.getPassword();
        phone_number = user.getPhone_number();
        cityId = user.getCity().getId();
    }
}

