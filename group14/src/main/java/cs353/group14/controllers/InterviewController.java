package cs353.group14.controllers;

import cs353.group14.Attend;
import cs353.group14.Interview;
import cs353.group14.requests.UpdateAttendRequest;
import cs353.group14.services.InterviewService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public void createAttend(@RequestBody Attend attend){
        interviewService.createAttend(attend);
    }

    @PutMapping("/updateAttend")
    public void changeAttendResult(@RequestBody UpdateAttendRequest updateAttendRequest){
        interviewService.changeAttendResult(updateAttendRequest.getInterviewId(),updateAttendRequest.getInterviewResult(), updateAttendRequest.getUserId());
    }

    @PutMapping("/addCodingQuestionToInterview/{interview_id}/{challenge_id}/{company_id}/{time_limit}")
    public void addCodingQuestionToInterview(@PathVariable int interview_id, @PathVariable int challenge_id, @PathVariable int company_id, @PathVariable int time_limit)
    {
        interviewService.addCodingQuestionToInterview(interview_id,challenge_id,company_id,time_limit);
    }

    @PutMapping("/addNonCodingQuestionToInterview/{interview_id}/{non_challenge_id}/{company_id}")
    public void addNonCodingQuestionToInterview(@PathVariable int interview_id, @PathVariable int non_challenge_id, @PathVariable int company_id)
    {
        interviewService.addNonCodingQuestionToInterview(interview_id,non_challenge_id,company_id);
    }

}
