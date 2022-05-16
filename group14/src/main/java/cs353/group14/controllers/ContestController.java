package cs353.group14.controllers;

import cs353.group14.CodingChallenge;
import cs353.group14.ContestAuthor;
import cs353.group14.NonCodingChallenge;
import cs353.group14.common.MessageResponse;
import cs353.group14.responses.*;
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
    public MessageResponse  createContest (@RequestBody Contest contest, @PathVariable int editorId){
        return contestService.createContest(editorId,contest);
    }


    @PutMapping("/addQuestionsToContest/{contestId}")
    public MessageResponse addQuestionsToContest (@RequestBody List<Integer> contest, @PathVariable int contestId){
        return contestService.addQuestionsToContest(contestId,contest);
    }


    @GetMapping("/getContest/{contestId}")
    public Contest getContest ( @PathVariable int contestId){
        return contestService.getContest(contestId);
    }

    @GetMapping("/getContestWithAuthor/{contest_id}")
    public ContestAuthor getContestWithAuthor(@PathVariable int contest_id)
    {
        return contestService.getContestWithAuthor(contest_id);
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
    public MessageResponse addCoderToContest (@PathVariable int userId, @PathVariable int contestId){
        return contestService.addCoderToContest(userId,contestId);
    }

    @PutMapping("/cancelContestParticipation/{userId}/{contestId}")
    public MessageResponse cancelContestParticipation (@PathVariable int userId, @PathVariable int contestId){
       return contestService.cancelContestParticipation(userId,contestId);
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
    public MessageResponse startContest (@PathVariable int userId, @PathVariable int contestId){
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

    @GetMapping("/getAllContests")
    public List<ContestDeadlineResponse> getAllContests(){
        return contestService.getAllContests();
    }

    @GetMapping("/getSponsoredContests/{companyId}")
    public List<ContestDeadlineResponse> getSponsoredContests(@PathVariable int companyId){
        return contestService.getSponsoredContests(companyId);
    }

    @GetMapping("/getContestsForEditor/{editorId}")
    public List<ContestDeadlineResponse> getContestsForEditor(@PathVariable int editorId){
        return contestService.getContestsForEditor(editorId);
    }

    @GetMapping("/getContestStatistic/{contest_id}")
    public ContestStatisticResponse getContestStatistic(@PathVariable int contest_id){
        return contestService.getContestStatistic(contest_id);
    }

    @GetMapping("/getContestSponsors/{contest_id}")
    public List<String>  getContestSponsors(@PathVariable int contest_id){
        return contestService.getContestSponsors(contest_id);
    }

}
