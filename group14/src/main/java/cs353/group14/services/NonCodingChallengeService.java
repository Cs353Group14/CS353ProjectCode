package cs353.group14.services;

import cs353.group14.NonCodingChallenge;
import cs353.group14.OtherAnswerResponse;
import cs353.group14.Reply;
import cs353.group14.common.MessageResponse;
import cs353.group14.repositories.NonCodingChallengeRepository;
import cs353.group14.responses.CategoryandNumberResponse;
import cs353.group14.responses.NonCodingChallengeAuthorCategoryResponse;
import cs353.group14.responses.NonCodingChallengeQueryResponse;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class NonCodingChallengeService {

    private final NonCodingChallengeRepository noncodingChallengeRepository;

    public NonCodingChallengeService(NonCodingChallengeRepository noncodingChallengeRepository) {
        this.noncodingChallengeRepository = noncodingChallengeRepository;
    }


    public int createNonCodingChallenge(int editorId, NonCodingChallenge nonCodingChallenge){
        return noncodingChallengeRepository.createNonCodingChallenge(editorId,nonCodingChallenge);
    }

    public List<NonCodingChallengeQueryResponse> getAllPublicNonCodingChallengesFiltered(List<String> filters){
        return noncodingChallengeRepository.getAllPublicNonCodingChallengesWithFiltered(filters);
    }

    public List<NonCodingChallengeQueryResponse> getAllNonCodingChallenges(){
        return noncodingChallengeRepository.getAllNonCodingChallenges();
    }

    public MessageResponse updateDifficultyNonCodingChallenge( int challenge_id, String difficulty)
    {
        return noncodingChallengeRepository.updateDifficultyNonCodingChallenge(challenge_id,difficulty);
    }

    public MessageResponse addCategoryNonCodingChallenge( int challenge_id, String category)
    {
       return noncodingChallengeRepository.addCategoryNonCodingChallenge(challenge_id,category);
    }

    public MessageResponse removeCategoryNonCodingChallenge(int challenge_id, String category)
    {
        return noncodingChallengeRepository.removeCategoryNonCodingChallenge(challenge_id,category);
    }



    public NonCodingChallenge getNonCodingChallenge(int noncodingChallengeId){
        return noncodingChallengeRepository.getNonCodingChallenge(noncodingChallengeId);
    }

    public MessageResponse replyQuestion(Reply reply){
        reply.setReplyTime(new Timestamp(System.currentTimeMillis()));
        return noncodingChallengeRepository.replyQuestion(reply);
    }

    public Reply seeReply(int userId, int nonChallengeId) {
        return noncodingChallengeRepository.seeReply(userId,nonChallengeId);
    }

    public List<OtherAnswerResponse> seeOtherCodersAnswers(int userId, int nonChallengeId){
        return noncodingChallengeRepository.seeOtherCodersAnswers(userId,nonChallengeId);
    }

    public NonCodingChallengeAuthorCategoryResponse getNonCodingChallengeAuthorResponse(int challengeId)
    {
        return noncodingChallengeRepository.getNonCodingChallengeAuthorResponse(challengeId);
    }

    public int createAndAddNonQuestionToInterview(int interviewId, int companyId, NonCodingChallenge noncodingChallenge){
        return noncodingChallengeRepository.createAndAddNonQuestionToInterview(interviewId,companyId, noncodingChallenge);
    }

    public List<CategoryandNumberResponse> getCategoriesAndNumbers()
    {
        return noncodingChallengeRepository.getCategoriesAndNumbers();
    }
}
