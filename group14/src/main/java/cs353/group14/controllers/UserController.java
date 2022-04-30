package cs353.group14.controllers;

import cs353.group14.*;
import cs353.group14.requests.LoginRequest;
import cs353.group14.requests.ReferRequest;
import cs353.group14.responses.LoginResponse;
import cs353.group14.services.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return userService.loginWithBasicResponse(loginRequest.getUsername(),loginRequest.getPassword());
    }

     @PutMapping("/giveReferCoder/{userId}/{referredId}")
     public void giveReferCoder(@PathVariable int userId, @PathVariable int referredId, @RequestBody ReferRequest referRequest){
         userService.giveReferCoder(userId,referredId,referRequest.getReason());
     }

    @PutMapping("/giveReferEditor/{userId}/{coderId}")
    public void giveReferEditor(@PathVariable int userId, @PathVariable int coderId,@RequestBody ReferRequest referRequest ){
        userService.giveReferEditor(userId,coderId,referRequest.getReason());
    }



}
