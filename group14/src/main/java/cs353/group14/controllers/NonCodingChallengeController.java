package cs353.group14.controllers;

import cs353.group14.responses.CodingChallengeQueryResponse;
import cs353.group14.responses.NonCodingChallengeQueryResponse;
import cs353.group14.services.NonCodingChallengeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class NonCodingChallengeController {
    private final NonCodingChallengeService nonCodingChallengeService;


    public NonCodingChallengeController(NonCodingChallengeService nonCodingChallengeService) {
        this.nonCodingChallengeService = nonCodingChallengeService;
    }




    @GetMapping("/NonCodingChallengesFiltered")
    public List<NonCodingChallengeQueryResponse> getPublicCodingChallengesFiltered(@RequestBody List<String> filters){
        return nonCodingChallengeService.getAllPublicCodingChallengesFiltered(filters);
    }
}
