package cs353.group14.controllers;

import cs353.group14.CodingChallenge;
import cs353.group14.common.MessageResponse;
import cs353.group14.common.MessageType;
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


    @GetMapping("/getChallengesOfEditor/{editorId}")
    public List<CodingChallengeQueryResponse> getChallengesOfEditor(@PathVariable int editorId){
        return codingChallengeService.getChallengesOfEditor(editorId);
    }

    @PostMapping("/publicCodingChallengesFiltered")
    public List<CodingChallengeQueryResponse> getPublicCodingChallengesFiltered( @RequestBody List<String> filters){
        return codingChallengeService.getAllPublicCodingChallengesFiltered(filters);
    }

    @PutMapping("/createCodingChallenge/{editorId}")
    public int createCodingChallenge(@RequestBody CodingChallenge codingChallenge, @PathVariable int editorId){
         return codingChallengeService.createAtCodingChallenge(editorId,codingChallenge);
    }

    @PutMapping("/createAndAddQuestionToInterview/{interviewId}/{company_id}/{time_limit}")
    public int createAndAddQuestionToInterview (@PathVariable int interviewId, @PathVariable int company_id,@PathVariable int time_limit,@RequestBody  CodingChallenge codingChallenge){
        return codingChallengeService.createAndAddQuestionToInterview(interviewId, company_id,  time_limit,  codingChallenge);
    }

    @GetMapping("/getCodingChallenge/{challengeId}")
    public CodingChallenge getCodingChallenge(@PathVariable int challengeId){
        return codingChallengeService.getCodingChallenge(challengeId);
    }


    @GetMapping("/getCodingChallengeAuthorAndCategory/{challengeId}")
    public CodingChallengeAuthorCategoryResponse getCodingChallengeAuthorAndCategory(@PathVariable int challengeId){
        return codingChallengeService.getAuthorandCategoryofCodingChallenge(challengeId);
    }


    @PutMapping("/updateDifficultyCodingChallenge/{challengeId}")
    public MessageResponse updateDifficultyCodingChallenge(@RequestBody String difficulty, @PathVariable int challengeId){
        return codingChallengeService.updateDifficultyCodingChallenge(challengeId,difficulty);
    }


    @PutMapping("/addCategoryCodingChallenge/{challengeId}")
    public MessageResponse addCategoryCodingChallenge(@RequestBody List<String> categories, @PathVariable int challengeId){
        List<MessageResponse> responses = categories.stream()
                .map(c -> codingChallengeService.addCategoryCodingChallenge(challengeId, c))
                .filter(mr-> mr.getMessageType().equals(MessageType.ERROR))
                .toList();

        if(responses.size() > 0) {
            return responses.get(0);
        } else
            return new MessageResponse(MessageType.SUCCESS, "All categories are added successfully");

    }


    @PutMapping("/removeCategoryCodingChallenge/{challengeId}")
    public MessageResponse removeCategoryCodingChallenge(@RequestBody String category, @PathVariable int challengeId){
        return codingChallengeService.removeCategoryCodingChallenge(challengeId,category);
    }


    @PutMapping("/addTestCaseForCodingChallenge/{challengeId}")
    public MessageResponse addTestCaseForCodingChallenge(@RequestBody List<String> inputsoutputs,  @PathVariable int challengeId){
        return codingChallengeService.addTestCaseForCodingChallenge(challengeId,inputsoutputs.get(0),inputsoutputs.get(1));
    }


    @GetMapping("/getInputsForCodingChallenge/{challengeId}")
    public List<String> getInputsForCodingChallenge(  @PathVariable int challengeId){
        return codingChallengeService.getInputsForCodingChallenge(challengeId);
    }


    @GetMapping("/getOutputsForCodingChallenge/{challengeId}")
    public List<String> getOutputsForCodingChallenge(  @PathVariable int challengeId){
        return codingChallengeService.getOutputsForCodingChallenge(challengeId);
    }

    @PutMapping("/makeCompanySponsorToContest/{companyId}/{contestId}")
    public MessageResponse makeCompanySponsorToContest(@PathVariable int companyId,  @PathVariable int contestId){
        return codingChallengeService.makeCompanySponsorToContest(contestId,companyId);
    }


}
