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

    public Long create(UserRequest request) {
        return save(new User(), request);
    }

    public Long update(Long id, UserRequest request) {
        return save(findById(id), request);
    }

    public void delete(Long id) {
        userRepository.delete(findById(id));
    }

    private Long save(User user, UserRequest request) {
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCity(cityService.findById(request.getCityId()));
        userRepository.save(user);
        return user.getId();
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
