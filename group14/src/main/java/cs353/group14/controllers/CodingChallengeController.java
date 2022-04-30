package cs353.group14.controllers;

import cs353.group14.responses.CodingChallengeQueryResponse;
import cs353.group14.services.CodingChallengeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
