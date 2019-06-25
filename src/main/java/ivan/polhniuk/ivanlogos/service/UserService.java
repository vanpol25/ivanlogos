package ivan.polhniuk.ivanlogos.service;

import ivan.polhniuk.ivanlogos.dto.request.UserRequest;
import ivan.polhniuk.ivanlogos.dto.response.UserResponse;
import ivan.polhniuk.ivanlogos.entity.City;
import ivan.polhniuk.ivanlogos.entity.User;
import ivan.polhniuk.ivanlogos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id=" + id + " not exists"));
    }

}
