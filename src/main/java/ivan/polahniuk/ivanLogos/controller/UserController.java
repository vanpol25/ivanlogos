package ivan.polahniuk.ivanLogos.controller;

import ivan.polahniuk.ivanLogos.dto.request.UserRequest;
import ivan.polahniuk.ivanLogos.dto.response.UserResponse;
import ivan.polahniuk.ivanLogos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void create(@Valid @RequestBody UserRequest request) {
        userService.create(request);
    }

    @PutMapping
    public void update(Long id, @Valid @RequestBody UserRequest request) {
        userService.update(id, request);
    }

    @DeleteMapping
    public void delete(Long id) {
        userService.delete(id);
    }

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

}
