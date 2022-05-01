package cs353.group14.controllers;

import cs353.group14.responses.CodingChallengeQueryResponse;
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

}
