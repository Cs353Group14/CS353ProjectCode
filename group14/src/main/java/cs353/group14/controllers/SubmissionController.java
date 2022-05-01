package cs353.group14.controllers;

import cs353.group14.CodingChallenge;
import cs353.group14.Submission;
import cs353.group14.services.SubmissionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PutMapping("/submitSolution/{userId}/{challengeId}")
    public void submitSolution( @PathVariable int userId,@PathVariable int challengeId, @RequestBody Submission submission){
        submissionService.submitSolution(userId,challengeId,submission );
    }


    @PutMapping("/submitSolution/{userId}/{challengeId}/{contestId}")
    public void submitSolutionForContest( @PathVariable int userId,@PathVariable int challengeId,
                                          @PathVariable int contestId, @RequestBody Submission submission){
        submissionService.submitSolutionForContest(userId,challengeId,contestId,submission );
    }


}
