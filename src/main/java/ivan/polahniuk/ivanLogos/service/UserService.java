package ivan.polahniuk.ivanLogos.service;

import ivan.polahniuk.ivanLogos.dto.response.AuthenticationResponse;
import ivan.polahniuk.ivanLogos.entity.User;
import ivan.polahniuk.ivanLogos.entity.UserRole;
import ivan.polahniuk.ivanLogos.repository.UserRepository;
import ivan.polahniuk.ivanLogos.dto.request.UserRequest;
import ivan.polahniuk.ivanLogos.dto.response.UserResponse;
import ivan.polahniuk.ivanLogos.security.JwtTokenTool;
import ivan.polahniuk.ivanLogos.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenTool jwtTokenTool;

    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        return new JwtUser(user.getUsername(), user.getPassword(), user.getUserRole());
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public AuthenticationResponse register(UserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadCredentialsException("User with username " + request.getUsername() + " already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setUserRole(UserRole.ROLE_USER);
        user.setPassword(encoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCity(cityService.findById(request.getCityId()));
        userRepository.save(user);
        return login(request);
    }

    public AuthenticationResponse login(UserRequest request) {
        String username = request.getUsername();
        User user = findByUsername(username);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));
        String token = jwtTokenTool.createToken(username, user.getUserRole());
        return new AuthenticationResponse(username, token);
    }

    public User findByUsername(String username)  {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not exists"));
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
