package cs353.group14.controllers;

import cs353.group14.*;
import cs353.group14.common.MessageResponse;
import cs353.group14.requests.LoginRequest;
import cs353.group14.requests.ReferRequest;
import cs353.group14.responses.IdUserNameandNameResponse;
import cs353.group14.responses.LoginResponse;
import cs353.group14.responses.UserCoderResponse;
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
    public MessageResponse registerCoder(@RequestBody Coder coder){
            coder.setUserType( UserType.Coder);
            return userService.registerCoder(coder);
    }

    @PutMapping("/register-editor")
    public MessageResponse registerCompany(@RequestBody Editor editor){
        editor.setUserType( UserType.Editor);
        return userService.registerEditor(editor);
    }

    @PutMapping("/register-company")
    public MessageResponse registerCompany(@RequestBody Company company){
        company.setUserType(UserType.Company);
        return userService.registerCompany(company);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return userService.loginWithBasicResponse(loginRequest.getUsername(),loginRequest.getPassword());
    }

     @PutMapping("/giveReferCoder/{userId}/{referredId}")
     public MessageResponse giveReferCoder(@PathVariable int userId, @PathVariable int referredId, @RequestBody ReferRequest referRequest){
         return userService.giveReferCoder(userId,referredId,referRequest.getReason());
     }

    @PutMapping("/giveReferEditor/{userId}/{coderId}")
    public MessageResponse giveReferEditor(@PathVariable int userId, @PathVariable int coderId,@RequestBody ReferRequest referRequest ){
        return userService.giveReferEditor(userId,coderId,referRequest.getReason());
    }

    @PutMapping("/askReferCoder/{userId}/{referredId}")
    public MessageResponse askReferCoder(@PathVariable int userId, @PathVariable int referredId){
        return userService.askReferCoder(userId,referredId);
    }

    @PutMapping("/askReferEditor/{userId}/{coderId}")
    public MessageResponse askReferEditor(@PathVariable int userId, @PathVariable int coderId ){
        return userService.askReferEditor(userId,coderId);
    }

    @PutMapping("/answerReferCoder/{userId}/{referredId}/{answer}")
    public MessageResponse answerReferCoder(@PathVariable int userId, @PathVariable int referredId,@PathVariable int answer,@RequestBody ReferRequest referRequest ){
        return userService.answerReferCoder(userId,referredId,answer,referRequest.getReason());
    }

    @PutMapping("/answerReferEditor/{userId}/{coderId}/{answer}")
    public MessageResponse answerReferEditor(@PathVariable int userId, @PathVariable int coderId,@PathVariable int answer,@RequestBody ReferRequest referRequest ){
        return userService.answerReferEditor(userId,coderId, answer,referRequest.getReason());
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


    @GetMapping("/getCoders")
    public List<UserCoderResponse>  getCoders(){
        return userService.getCoders();
    }


    @GetMapping("/getCoders/{filter}")
    public List<UserCoderResponse>  getCodersWithFilter(@PathVariable String filter){
        return userService.getCodersWithFilter(filter);
    }

}
