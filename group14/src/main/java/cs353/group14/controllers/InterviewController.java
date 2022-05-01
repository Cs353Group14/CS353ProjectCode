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
        interviewService.changeAttendResult(updateAttendRequest.getInterviewId(),updateAttendRequest.getInterviewResult());
    }

}
