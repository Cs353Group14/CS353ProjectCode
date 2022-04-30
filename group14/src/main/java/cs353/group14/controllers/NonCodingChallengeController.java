package cs353.group14.controllers;

import cs353.group14.CodingChallenge;
import cs353.group14.NonCodingChallenge;
import cs353.group14.responses.CodingChallengeQueryResponse;
import cs353.group14.responses.NonCodingChallengeQueryResponse;
import cs353.group14.services.NonCodingChallengeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class NonCodingChallengeController {
    private final NonCodingChallengeService nonCodingChallengeService;


    public NonCodingChallengeController(NonCodingChallengeService nonCodingChallengeService) {
        this.nonCodingChallengeService = nonCodingChallengeService;
    }


    @PutMapping("/createNonCodingChallenge/{userId}")
    public void createNonCodingChallenge(@RequestBody NonCodingChallenge nonCodingChallenge, @PathVariable int userId){
        nonCodingChallengeService.createNonCodingChallenge(userId,nonCodingChallenge);
    }


    @GetMapping("/NonCodingChallengesFiltered")
    public List<NonCodingChallengeQueryResponse> getPublicNonCodingChallengesFiltered(@RequestBody List<String> filters){
        return nonCodingChallengeService.getAllPublicNonCodingChallengesFiltered(filters);
    }


    @GetMapping("/NonCodingChallenges")
    public List<NonCodingChallengeQueryResponse> getAllNonCodingChallenges(){
        return nonCodingChallengeService.getAllNonCodingChallenges();
    }
}
