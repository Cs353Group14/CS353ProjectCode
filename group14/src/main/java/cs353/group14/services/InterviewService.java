package cs353.group14.services;

import cs353.group14.*;
import cs353.group14.common.MessageResponse;
import cs353.group14.repositories.InterviewRepository;
import cs353.group14.responses.InterviewResponse;
import cs353.group14.responses.UserNameAndInterviewResultResponse;
import cs353.group14.responses.UserNameandNameResponse;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    public MessageResponse createAttend(Attend attend){
        return interviewRepository.insertAttend(attend);
    }

    public MessageResponse changeAttendResult(int interviewId, String result, int userId){
        return interviewRepository.changeAttendResult(interviewId,result,userId);
    }


    public MessageResponse addCodingQuestionToInterview(int interview_id,int challenge_id, int company_id, int time_limit)
    {
        return interviewRepository.addCodingQuestionToInterview(interview_id,challenge_id,company_id,time_limit);
    }

    public MessageResponse addNonCodingQuestionToInterview(int interview_id, int challenge_id, int company_id)
    {
        return interviewRepository.addNonCodingQuestionToInterview(interview_id,challenge_id,company_id);
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

    public Interview getInterview( int interviewId)
    {
        return interviewRepository.getInterview(  interviewId);
    }

    public List<NonCodingChallenge> getNonCodingChallengesOfInterview( int interviewId)
    {
        return interviewRepository.getNonCodingChallengesOfInterview(  interviewId);
    }

    public List<CodingChallenge> getCodingChallengesOfInterview(int interviewId)
    {
        return interviewRepository.getCodingChallengesOfInterview(  interviewId);
    }

    public Company getCompanyofInterview( int interview_id)
    {
        return interviewRepository.getCompanyofInterview( interview_id);
    }

    public List<UserNameandNameResponse>  getUsersAttendingToInterview( int interview_id)
    {
        return interviewRepository.getUsersAttendingToInterview( interview_id);
    }

    public List<InterviewResponse> getPastInterviewsForCoder(int coder_id){
        return interviewRepository.getPastInterviewsForCoder(coder_id);
    }

    public List<InterviewResponse> getFutureInterviewsForCoder(int coder_id){
        return interviewRepository.getFutureInterviewsForCoder(coder_id);
    }

    public List<Interview> getInterviewsOfCompanyNew(int user_id){
        return interviewRepository.getInterviewsOfCompanyNew(user_id);
    }

    public List<InterviewResponse> getInterviewsForCoderWithRange(int coder_id, Timestamp rangeEarly, Timestamp rangeLate)
    {
        return interviewRepository.getInterviewsForCoderWithRange(coder_id, rangeEarly, rangeLate);
    }
    public List<InterviewResponse> getInterviewsForCoderWithPosition(int coder_id, String positionLike)
    {
        return interviewRepository.getInterviewsForCoderWithPosition( coder_id,  positionLike);
    }

    public Attend getAttend(int user_id, int interview_id) {
        return interviewRepository.getAttend( user_id,  interview_id);
    }

    public int getNumberOfContestAttended(int coder_id){
        return interviewRepository.getNumberOfContestAttended(coder_id);
    }

    public int getSumSolvedNumberOfQuestion(int coder_id) {
        return interviewRepository.getSumSolvedNumberOfQuestion(coder_id);
    }

    public int getInterviewStatus(int interview_id,int coder_id){
        return interviewRepository.getInterviewStatus(interview_id,coder_id);
    }
}
