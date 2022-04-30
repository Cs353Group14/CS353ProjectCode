package cs353.group14.services;

import cs353.group14.Submission;
import cs353.group14.repositories.SubmissionRepository;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Random;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    public void submitSolution(int userId, int challengeId, Submission submission){
        submission.setSubmission_time(new Timestamp(System.currentTimeMillis()));

        Random rn = new Random();
        int fail = rn.nextInt(2) ;
        int pass = rn.nextInt(15) ;

        submission.setPass_result(pass);
        submission.setFail_result(fail);

        submissionRepository.submitQuestion(userId,challengeId,submission);
    }
}
