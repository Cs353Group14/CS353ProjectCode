package cs353.group14.controllers;

import cs353.group14.Coder;
import cs353.group14.services.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/register-coder")
    public void registerCoder(@RequestBody Coder coder){
            userService.registerCoder(coder);
    }


}
