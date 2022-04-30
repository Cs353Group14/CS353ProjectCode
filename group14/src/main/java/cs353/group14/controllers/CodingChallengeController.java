package cs353.group14.controllers;

import cs353.group14.CodingChallenge;
import cs353.group14.requests.LoginRequest;
import cs353.group14.responses.CodingChallengeAuthorCategoryResponse;
import cs353.group14.responses.CodingChallengeQueryResponse;
import cs353.group14.services.CodingChallengeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class CodingChallengeController {

    private final CodingChallengeService codingChallengeService;

    public CodingChallengeController(CodingChallengeService codingChallengeService) {
        this.codingChallengeService = codingChallengeService;
    }

    @GetMapping("/publicCodingChallenges")
    public List<CodingChallengeQueryResponse> getPublicCodingChallenges(){
        return codingChallengeService.getAllPublicChallenges();
    }


    @GetMapping("/publicCodingChallengesFiltered")
    public List<CodingChallengeQueryResponse> getPublicCodingChallengesFiltered( @RequestBody List<String> filters){
        return codingChallengeService.getAllPublicCodingChallengesFiltered(filters);
    }

    @PutMapping("/createCodingChallenge/{editorId}")
    public void createCodingChallenge(@RequestBody CodingChallenge codingChallenge, @PathVariable int editorId){
         codingChallengeService.createAtCodingChallenge(editorId,codingChallenge);
    }

    @GetMapping("/getCodingChallenge/{challengeId}")
    public CodingChallenge getCodingChallenge(@PathVariable int challengeId){
        return codingChallengeService.getCodingChallenge(challengeId);
    }


    @GetMapping("/getCodingChallengeAuthorAndCategory/{challengeId}")
    public CodingChallengeAuthorCategoryResponse getCodingChallengeAuthorAndCategory(@PathVariable int challengeId){
        return codingChallengeService.getAuthorandCategoryofCodingChallenge(challengeId);
    }



}
