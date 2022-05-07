package cs353.group14.controllers;

import cs353.group14.*;
import cs353.group14.requests.LoginRequest;
import cs353.group14.requests.ReferRequest;
import cs353.group14.responses.IdUserNameandNameResponse;
import cs353.group14.responses.LoginResponse;
import cs353.group14.responses.UserNameandNameResponse;
import cs353.group14.services.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @PutMapping("/askReferCoder/{userId}/{referredId}")
    public void askReferCoder(@PathVariable int userId, @PathVariable int referredId){
        userService.askReferCoder(userId,referredId);
    }

    @PutMapping("/askReferEditor/{userId}/{coderId}")
    public void askReferEditor(@PathVariable int userId, @PathVariable int coderId ){
        userService.askReferEditor(userId,coderId);
    }

    @PutMapping("/answerReferCoder/{userId}/{referredId}/{answer}")
    public void answerReferCoder(@PathVariable int userId, @PathVariable int referredId,@PathVariable int answer,@RequestBody ReferRequest referRequest ){
        userService.answerReferCoder(userId,referredId,answer,referRequest.getReason());
    }

    @PutMapping("/answerReferEditor/{userId}/{coderId}/{answer}")
    public void answerReferEditor(@PathVariable int userId, @PathVariable int coderId,@PathVariable int answer,@RequestBody ReferRequest referRequest ){
        userService.answerReferEditor(userId,coderId, answer,referRequest.getReason());
    }

    @GetMapping("/listReferCoder/{userId}")
    public List<Integer> listReferCoder(@PathVariable int userId){
        return userService.listReferCoder(userId);
    }

    @GetMapping("/listReferEditor/{userId}")
    public List<Integer> listReferEditor(@PathVariable int userId){
        return userService.listReferEditor(userId);
    }

    @GetMapping("/listOldAttempts/{userId}/{challengeId}")
    public List<Submission> listOldAttempts(@PathVariable int userId, @PathVariable int challengeId){
        return userService.listOldAttempts(userId, challengeId);
    }


    @GetMapping("/getUserNameandName/{userName}")
    public UserNameandNameResponse getUserNameandName(@PathVariable String userName){
        return userService.getUserNameandName(userName);
    }


    @GetMapping("/getIdUserNameandName/{userName}")
    public IdUserNameandNameResponse getIdUserNameandName(@PathVariable String userName){
        return userService.getIdUserNameandName(userName);
    }



}
