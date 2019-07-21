package ivan.polahniuk.ivanLogos.service;

import ivan.polahniuk.ivanLogos.entity.User;
import ivan.polahniuk.ivanLogos.repository.UserRepository;
import ivan.polahniuk.ivanLogos.dto.request.UserRequest;
import ivan.polahniuk.ivanLogos.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityService cityService;

    public void create(UserRequest request) {
        save(new User(), request);
    }

    public void update(Long id, UserRequest request) {
        save(findById(id), request);
    }

    public void delete(Long id) {
        userRepository.delete(findById(id));
    }

    private void save(User user, UserRequest request) {
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone_number(request.getPhone_number());
        user.setCity(cityService.findById(request.getCityId()));
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
