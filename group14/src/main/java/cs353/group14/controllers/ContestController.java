package cs353.group14.controllers;

import cs353.group14.CodingChallenge;
import cs353.group14.NonCodingChallenge;
import cs353.group14.responses.CodingChallengeQueryResponse;
import cs353.group14.responses.UserNameAndPointResponse;
import cs353.group14.services.ContestService;
import org.springframework.web.bind.annotation.*;
import cs353.group14.Contest;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping
public class ContestController {
    private final ContestService contestService;

    public ContestController(ContestService contestService) {
        this.contestService = contestService;
    }

    @PutMapping("/createContest/{editorId}")
    public void createContest (@RequestBody Contest contest, @PathVariable int editorId){
        contestService.createContest(editorId,contest);
    }


    @PutMapping("/addQuestonsToContest/{contestId}")
    public void addQuestionsToContest (@RequestBody List<Integer> contest, @PathVariable int contestId){
        contestService.addQuestionsToContest(contestId,contest);
    }


    @GetMapping("/getContest/{contestId}")
    public Contest getContest ( @PathVariable int contestId){
        return contestService.getContest(contestId);
    }

    @PutMapping("/addCoderToContest/{contestId}")
    public void addCoderToContest (@RequestBody int userId, @PathVariable int contestId){
        contestService.addCoderToContest(userId,contestId);
    }

    @GetMapping("/getContestOrder/{contestId}")
    public List<UserNameAndPointResponse> getContestOrder ( @PathVariable int contestId){
        return contestService.getOrder(contestId);
    }


    @GetMapping("/getContestCodingQuestions/{contestId}")
    public  List<CodingChallenge> getContestCodingQuestions(@PathVariable int contestId){
        return contestService.getCodingChallengesOfContest(contestId);
    }



}
