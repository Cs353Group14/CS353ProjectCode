package cs353.group14.controllers;

import cs353.group14.Interview;
import cs353.group14.services.InterviewService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
