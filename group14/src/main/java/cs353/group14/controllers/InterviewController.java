package cs353.group14.controllers;

import cs353.group14.*;
import cs353.group14.common.MessageResponse;
import cs353.group14.requests.UpdateAttendRequest;
import cs353.group14.responses.InterviewResponse;
import cs353.group14.responses.UserNameAndInterviewResultResponse;
import cs353.group14.responses.UserNameandNameResponse;
import cs353.group14.services.InterviewService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping
public class InterviewController {
    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PutMapping("/createInterview")
    public int createInterview(@RequestBody Interview interview){
        return interviewService.createInterview(interview);
    }

    @PutMapping("/createAttend")
    public MessageResponse createAttend(@RequestBody Attend attend){
        return interviewService.createAttend(attend);
    }

    @PutMapping("/updateAttend")
    public MessageResponse changeAttendResult(@RequestBody UpdateAttendRequest updateAttendRequest){
        return interviewService.changeAttendResult(updateAttendRequest.getInterviewId(),updateAttendRequest.getInterviewResult(), updateAttendRequest.getUserId());
    }

    @PutMapping("/addCodingQuestionToInterview/{interview_id}/{challenge_id}/{company_id}/{time_limit}")
    public MessageResponse addCodingQuestionToInterview(@PathVariable int interview_id, @PathVariable int challenge_id, @PathVariable int company_id, @PathVariable int time_limit)
    {
        return interviewService.addCodingQuestionToInterview(interview_id,challenge_id,company_id,time_limit);
    }

    @PutMapping("/addNonCodingQuestionToInterview/{interview_id}/{non_challenge_id}/{company_id}")
    public MessageResponse addNonCodingQuestionToInterview(@PathVariable int interview_id, @PathVariable int non_challenge_id, @PathVariable int company_id)
    {
        return interviewService.addNonCodingQuestionToInterview(interview_id,non_challenge_id,company_id);
    }

    @GetMapping("/seeNotifications/{userId}")
    public List<Notification> seeNotifications(@PathVariable int userId){
        return interviewService.seeNotifications(userId);
    }

    @GetMapping("/getInterviewsForCompany/{companyId}")
    public  List<UserNameAndInterviewResultResponse> getInterviewsForCompany(@PathVariable int companyId){
        return interviewService.getInterviewsForCompany(companyId);
    }

    @GetMapping("/getInterviewsForCoder/{coderId}")
    public  List<InterviewResponse> getInterviewsForCoder(@PathVariable int coderId){
        return interviewService.getInterviewsForCoder(coderId);
    }


    @GetMapping("/getInterview/{interviewId}")
    public  Interview getInterview(@PathVariable int interviewId){
        return interviewService.getInterview(interviewId);
    }


    @GetMapping("/getInterviewCodingQuestions/{interviewId}")
    public  List<CodingChallenge> getInterviewCodingQuestions(@PathVariable int interviewId){
        return interviewService.getCodingChallengesOfInterview(interviewId);
    }


    @GetMapping("/getInterviewNonCodingQuestions/{interviewId}")
    public  List<NonCodingChallenge> getInterviewNonCodingQuetions(@PathVariable int interviewId){
        return interviewService.getNonCodingChallengesOfInterview(interviewId);
    }

    @GetMapping("/getCompanyofInterview/{interviewId}")
    public  Company getCompanyofInterview(@PathVariable int interviewId){
        return interviewService.getCompanyofInterview(interviewId);
    }


    @GetMapping("/getUsersAttendingToInterview/{interviewId}")
    public  List<UserNameandNameResponse>  getUsersAttendingToInterview(@PathVariable int interviewId){
        return interviewService.getUsersAttendingToInterview(interviewId);
    }


    @GetMapping("/getPastInterviewsForCoder/{coder_id}")
    public List<InterviewResponse> getPastInterviewsForCoder(@PathVariable int coder_id){
        return interviewService.getPastInterviewsForCoder(coder_id);
    }

    @GetMapping("/getFutureInterviewsForCoder/{coder_id}")
    public List<InterviewResponse> getFutureInterviewsForCoder(@PathVariable  int coder_id){
        return interviewService.getFutureInterviewsForCoder(coder_id);
    }


    @GetMapping("/getInterviewsOfCompanyNew/{user_id}")
    public List<Interview> getInterviewsOfCompanyNew(@PathVariable int user_id){
        return interviewService.getInterviewsOfCompanyNew(user_id);
    }


    @GetMapping("/getInterviewsForCoderWithRange/{user_id}/{rangeEarly}/{rangeLate}")
    public List<InterviewResponse> getInterviewsForCoderWithRange(@PathVariable int user_id,@PathVariable String rangeEarly,@PathVariable String rangeLate){
        // Format of strings should be like 2018-09-01 09:01:15

        // example request localhost:8080/getInterviewsForCoderWithRange/6/2018-09-01 09:01:15/2028-09-01 09:01:15
        Timestamp timestampearly= Timestamp.valueOf(rangeEarly);
        Timestamp timestamplate= Timestamp.valueOf(rangeLate);
        return interviewService.getInterviewsForCoderWithRange(user_id,  timestampearly,  timestamplate);
    }


    @GetMapping("/getInterviewsForCoderWithPosition/{user_id}/{positionLike}")
    public List<InterviewResponse> getInterviewsForCoderWithPosition(@PathVariable int user_id,@PathVariable String positionLike){

        // example request localhost:8080/getInterviewsForCoderWithPosition/6/inte

        return interviewService.getInterviewsForCoderWithPosition(user_id,  positionLike);
    }

    @GetMapping("/getAttend/{user_id}/{interview_id}")
    public Attend getAttend(@PathVariable int user_id,@PathVariable int interview_id){
        return interviewService.getAttend(user_id,interview_id);
    }

    @GetMapping("/getNumberOfContestAttended/{coder_id}")
    public int getNumberOfContestAttended(@PathVariable int coder_id){
        return interviewService.getNumberOfContestAttended(coder_id);
    }

    @GetMapping("/getSumSolvedNumberOfQuestion/{coder_id}")
    public int getSumSolvedNumberOfQuestion(@PathVariable int coder_id){
        return interviewService.getSumSolvedNumberOfQuestion(coder_id);
    }

    @GetMapping("/getInterviewStatus/{interview_id}/{coder_id}")
    public int getInterviewStatus(@PathVariable int interview_id, @PathVariable int coder_id){
        return interviewService.getInterviewStatus(interview_id,coder_id);
    }


}
