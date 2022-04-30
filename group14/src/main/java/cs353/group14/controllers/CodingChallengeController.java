package cs353.group14.controllers;

import cs353.group14.CodingChallenge;
import cs353.group14.requests.LoginRequest;
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

    @PutMapping("/createCodingChallenge/{editorId}")
    public void createCodingChallenge(@RequestBody CodingChallenge codingChallenge, @PathVariable int editorId){
         codingChallengeService.createAtCodingChallenge(editorId,codingChallenge);
    }

    @GetMapping("/getCodingChallenge/{challengeId}")
    public CodingChallenge getCodingChallenge(@PathVariable int challengeId){
        return codingChallengeService.getCodingChallenge(challengeId);
    }


}
