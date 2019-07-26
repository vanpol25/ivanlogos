package ivan.polahniuk.ivanLogos.dto.response;

import ivan.polahniuk.ivanLogos.entity.User;
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
    private String phoneNumber;
    private CityResponse city;


    public UserResponse(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        password = user.getPassword();
        phoneNumber = user.getPhoneNumber();
        city = new CityResponse(user.getCity());
    }

}

