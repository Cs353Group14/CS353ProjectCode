package cs353.group14.services;

import cs353.group14.Attend;
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

    public void createAttend(Attend attend){
        interviewRepository.insertAttend(attend);
    }

    public void changeAttendResult(int interviewId, String result, int userId){
        interviewRepository.changeAttendResult(interviewId,result,userId);
    }


    public void addCodingQuestionToInterview(int interview_id,int challenge_id, int company_id, int time_limit)
    {
        interviewRepository.addCodingQuestionToInterview(interview_id,challenge_id,company_id,time_limit);
    }

    public void addNonCodingQuestionToInterview(int interview_id,int challenge_id, int company_id)
    {
        interviewRepository.addNonCodingQuestionToInterview(interview_id,challenge_id,company_id);
    }
}
