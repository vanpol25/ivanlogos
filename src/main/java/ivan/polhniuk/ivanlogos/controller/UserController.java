package ivan.polhniuk.ivanlogos.controller;

import ivan.polhniuk.ivanlogos.dto.request.UserRequest;
import ivan.polhniuk.ivanlogos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void create(@RequestBody UserRequest request) {
        userService.create(request);
    }


}
