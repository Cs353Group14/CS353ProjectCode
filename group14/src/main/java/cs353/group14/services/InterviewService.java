package cs353.group14.services;

import cs353.group14.Interview;
import cs353.group14.repositories.InterviewRepository;
import org.springframework.stereotype.Service;

@Service
public class InterviewService {
    private final InterviewRepository interviewRepository;

    public InterviewService(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

    public int createInterview(Interview interview){
        return interviewRepository.createInterview(interview);
    }
}
