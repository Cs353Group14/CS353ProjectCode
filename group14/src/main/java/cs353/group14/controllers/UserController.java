package cs353.group14.controllers;

import cs353.group14.*;
import cs353.group14.requests.LoginRequest;
import cs353.group14.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/register-coder")
    public void registerCoder(@RequestBody Coder coder){
            coder.setUserType( UserType.Coder);
            userService.registerCoder(coder);
    }

    @PutMapping("/register-editor")
    public void registerCompany(@RequestBody Editor editor){
        editor.setUserType( UserType.Editor);
        userService.registerEditor(editor);
    }

    @PutMapping("/register-company")
    public void registerCompany(@RequestBody Company company){
        company.setUserType(UserType.Company);
        userService.registerCompany(company);
    }

    @GetMapping("/login")
    public User login(@RequestBody LoginRequest loginRequest){
        return UserService.login(loginRequest.getUsername(),loginRequest.getPassword());
    }



}
