package cs353.group14.services;

import cs353.group14.CodingChallenge;
import cs353.group14.Contest;
import cs353.group14.ContestAuthor;
import cs353.group14.NonCodingChallenge;
import cs353.group14.common.MessageResponse;
import cs353.group14.repositories.ContestRepository;
import cs353.group14.responses.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ContestService {

    private final ContestRepository contestRepository;

    public ContestService(ContestRepository contestRepository) {
        this.contestRepository = contestRepository;
    }

    public int createContest(int editorId, Contest contest)
    {
        return contestRepository.createContest(editorId,contest.getStart_time(), contest.getDescription(),
                contest.getTitle(), contest.getDifficulty(), contest.getDuration(), contest.getDeadline());
    }

    public MessageResponse addQuestionsToContest(int contest_id, List<Integer> questions)
    {
        return contestRepository.addQuestionToContest(contest_id,questions);
    }
    public Contest getContest( int contest_id)
    {
        return contestRepository.getContest(contest_id);
    }

    public ContestAuthor getContestWithAuthor(int contest_id)
    {
        return contestRepository.getContestWithAuthor(contest_id);
    }

    public MessageResponse addCoderToContest( int user_id, int contest_id)
    {
        return contestRepository.addCoderToContest(user_id,contest_id);
    }

    public MessageResponse cancelContestParticipation(int userId, int contestId) {
        return contestRepository.cancelContestParticipation(userId,contestId);
    }

    public List<UserNameAndPointResponse> getOrder(int contestId)
    {
        return contestRepository.getOrder(contestId);
    }


    public List<Contest> getFutureContestsRegistered( int userId)
    {
        return contestRepository.getFutureContestsRegistered(userId);
    }

    public List<Contest> getFutureContestsNotRegistered( int userId)
    {
        return contestRepository.getFutureContestsNotRegistered(userId);
    }

    public List<CodingChallengeQueryResponse> getCodingChallengesOfContest(int contestId)
    {
        return contestRepository.getCodingChallengesOfContest(  contestId);
    }

    public int startContest(int userId, int contestId) {
        return contestRepository.startContest(userId,contestId);
    }
    public int getContestStatus(int userId, int contestId) {
        return contestRepository.getContestStatus(userId,contestId);
    }

    public List<Contest> getAvailableRegisteredContests(int userId){
        return contestRepository.getAvailableRegisteredContests(userId);
    }

    public List<ContestResponse> getOldRegisteredContests(int userId) {
        return contestRepository.getOldRegisteredContests(userId);
    }

    public List<ContestDeadlineResponse> getAllContests() {
        return contestRepository.getAllContests();
    }

    public List<ContestDeadlineResponse> getSponsoredContests(int companyId){
        return contestRepository.getSponsoredContests(companyId);
    }

    public List<ContestDeadlineResponse> getContestsForEditor(int editorId){
        return contestRepository.getContestsForEditor(editorId);
    }

    public ContestStatisticResponse getContestStatistic(int contest_id)
    {
        return contestRepository.getContestStatistic(  contest_id);
    }
}
