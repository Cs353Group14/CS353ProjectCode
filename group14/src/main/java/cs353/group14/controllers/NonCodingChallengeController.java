package cs353.group14.controllers;


import cs353.group14.CodingChallenge;
import cs353.group14.NonCodingChallenge;
import cs353.group14.OtherAnswerResponse;
import cs353.group14.Reply;
import cs353.group14.common.MessageResponse;
import cs353.group14.responses.NonCodingChallengeAuthorCategoryResponse;
import cs353.group14.responses.NonCodingChallengeQueryResponse;
import cs353.group14.services.NonCodingChallengeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping
public class NonCodingChallengeController {
    private final NonCodingChallengeService nonCodingChallengeService;


    public NonCodingChallengeController(NonCodingChallengeService nonCodingChallengeService) {
        this.nonCodingChallengeService = nonCodingChallengeService;
    }


    @PutMapping("/createNonCodingChallenge/{userId}")
    public int createNonCodingChallenge(@RequestBody NonCodingChallenge nonCodingChallenge, @PathVariable int userId){
        return  nonCodingChallengeService.createNonCodingChallenge(userId,nonCodingChallenge);
    }


    @GetMapping("/NonCodingChallengesFiltered")
    public List<NonCodingChallengeQueryResponse> getPublicNonCodingChallengesFiltered(@RequestBody List<String> filters){
        return nonCodingChallengeService.getAllPublicNonCodingChallengesFiltered(filters);
    }


    @GetMapping("/NonCodingChallenges")
    public List<NonCodingChallengeQueryResponse> getAllNonCodingChallenges(){
        return nonCodingChallengeService.getAllNonCodingChallenges();
    }

    @GetMapping("/getNonCodingChallenge/{noncodingChallengeId}")
    public NonCodingChallenge getNonCodingChallenge(@PathVariable int noncodingChallengeId){
        return nonCodingChallengeService.getNonCodingChallenge(noncodingChallengeId);
    }


    @PutMapping("/replyQuestion")
    public MessageResponse replyQuestion(@RequestBody Reply reply){
        return nonCodingChallengeService.replyQuestion(reply);
    }

    @GetMapping("/seeReply/{userId}/{nonChallengeId}")
    public Reply seeReply(@PathVariable int userId,@PathVariable int nonChallengeId){
        return nonCodingChallengeService.seeReply(userId,nonChallengeId);
    }


    @PutMapping("/updateDifficultyNonCodingChallenge/{challengeId}")
    public MessageResponse updateDifficultyCodingChallenge(@RequestBody String difficulty, @PathVariable int challengeId){
        return nonCodingChallengeService.updateDifficultyNonCodingChallenge(challengeId,difficulty);
    }


    @PutMapping("/addCategoryNonCodingChallenge/{challengeId}")
    public MessageResponse addCategoryCodingChallenge(@RequestBody String category, @PathVariable int challengeId){
        return nonCodingChallengeService.addCategoryNonCodingChallenge(challengeId,category);
    }


    @PutMapping("/removeCategoryNonCodingChallenge/{challengeId}")
    public MessageResponse removeCategoryCodingChallenge(@RequestBody String category, @PathVariable int challengeId){
        return nonCodingChallengeService.removeCategoryNonCodingChallenge(challengeId,category);
    }

    @GetMapping("/seeOtherCodersAnswers/{userId}/{nonChallengeId}")
    public List<OtherAnswerResponse> seeOtherCodersAnswers(@PathVariable int userId,@PathVariable int nonChallengeId){
        return nonCodingChallengeService.seeOtherCodersAnswers(userId,nonChallengeId);
    }


    @GetMapping("/getNonCodingChallengeAuthorCategory/{nonChallengeId}")
    public NonCodingChallengeAuthorCategoryResponse getNonCodingChallengeAuthorResponse(@PathVariable int nonChallengeId){
        return nonCodingChallengeService.getNonCodingChallengeAuthorResponse(nonChallengeId);
    }

    @PutMapping("/createAndAddNonQuestionToInterview/{interviewId}/{companyId}")
    public int createAndAddNonQuestionToInterview (@PathVariable int interviewId, @PathVariable int companyId,@RequestBody NonCodingChallenge noncodingChallenge){
        return nonCodingChallengeService.createAndAddNonQuestionToInterview(interviewId, companyId, noncodingChallenge);
    }


}
