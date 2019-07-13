package ivan.polhniuk.ivanlogos.controller;

import ivan.polhniuk.ivanlogos.dto.request.UserRequest;
import ivan.polhniuk.ivanlogos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

}
