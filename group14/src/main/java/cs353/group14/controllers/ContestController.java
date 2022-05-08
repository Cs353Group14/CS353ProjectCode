package cs353.group14.controllers;

import cs353.group14.CodingChallenge;
import cs353.group14.NonCodingChallenge;
import cs353.group14.responses.CodingChallengeQueryResponse;
import cs353.group14.responses.ContestResponse;
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
    public int  createContest (@RequestBody Contest contest, @PathVariable int editorId){
        return contestService.createContest(editorId,contest);
    }


    @PutMapping("/addQuestionsToContest/{contestId}")
    public void addQuestionsToContest (@RequestBody List<Integer> contest, @PathVariable int contestId){
        contestService.addQuestionsToContest(contestId,contest);
    }


    @GetMapping("/getContest/{contestId}")
    public Contest getContest ( @PathVariable int contestId){
        return contestService.getContest(contestId);
    }

    @GetMapping("/getFutureContestsRegistered/{userId}")
    public List<Contest> getFutureContestsRegistered ( @PathVariable int userId) {
        return contestService.getFutureContestsRegistered(userId);
    }

    @GetMapping("/getFutureContestsNotRegistered/{userId}")
    public List<Contest> getFutureContestsNotRegistered ( @PathVariable int userId) {
        return contestService.getFutureContestsNotRegistered(userId);
    }


    @PutMapping("/addCoderToContest/{userId}/{contestId}")
    public void addCoderToContest (@PathVariable int userId, @PathVariable int contestId){
        contestService.addCoderToContest(userId,contestId);
    }

    @PutMapping("/cancelContestParticipation/{userId}/{contestId}")
    public void cancelContestParticipation (@PathVariable int userId, @PathVariable int contestId){
        contestService.cancelContestParticipation(userId,contestId);
    }

    @GetMapping("/getContestOrder/{contestId}")
    public List<UserNameAndPointResponse> getContestOrder ( @PathVariable int contestId){
        return contestService.getOrder(contestId);
    }


    @GetMapping("/getContestCodingQuestions/{contestId}")
    public  List<CodingChallengeQueryResponse> getContestCodingQuestions(@PathVariable int contestId){
        return contestService.getCodingChallengesOfContest(contestId);
    }

    @PutMapping("/participateContest/{userId}/{contestId}")
    public int startContest (@PathVariable int userId, @PathVariable int contestId){
        return contestService.startContest(userId,contestId);
    }

    @GetMapping("/getContestStatus/{userId}/{contestId}")
    public int getContestStatus (@PathVariable int userId, @PathVariable int contestId){
        return contestService.getContestStatus(userId,contestId);
    }

    @GetMapping("/getAvailableRegisteredContests/{userId}")
    public List<Contest> getAvailableRegisteredContests(@PathVariable int userId){
        return contestService.getAvailableRegisteredContests(userId);
    }

    @GetMapping("/getOldRegisteredContests/{userId}")
    public List<ContestResponse> getOldRegisteredContests(@PathVariable int userId){
        return contestService.getOldRegisteredContests(userId);
    }




}
