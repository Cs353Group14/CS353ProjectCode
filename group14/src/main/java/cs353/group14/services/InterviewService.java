package cs353.group14.services;

import cs353.group14.Attend;
import cs353.group14.Interview;
import cs353.group14.Notification;
import cs353.group14.repositories.InterviewRepository;
import cs353.group14.responses.InterviewResponse;
import cs353.group14.responses.UserNameAndInterviewResultResponse;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Notification> seeNotifications(int userId){
        return interviewRepository.seeNotifications(userId);
    }


    public List<UserNameAndInterviewResultResponse> getInterviewsForCompany(int companyId)
    {
        return interviewRepository.getInterviewsForCompany(companyId);
    }

    public List<InterviewResponse> getInterviewsForCoder(int userId)
    {
        return interviewRepository.getInterviewsForCoder(userId);
    }
}
