package ivan.polhniuk.ivanlogos.service;

import ivan.polhniuk.ivanlogos.dto.request.UserRequest;
import ivan.polhniuk.ivanlogos.entity.User;
import ivan.polhniuk.ivanlogos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void create(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone_number(request.getPhone_number());
        userRepository.save(user);
    }

    private String name;

    private String email;

    private String password;

    private String phone_number;

}
