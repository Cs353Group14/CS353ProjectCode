package cs353.group14.services;

import cs353.group14.CodingChallenge;
import cs353.group14.common.MessageResponse;
import cs353.group14.repositories.CodingChallengeRepository;
import cs353.group14.responses.CodingChallengeAuthorCategoryResponse;
import cs353.group14.responses.CodingChallengeQueryResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CodingChallengeService {

    private final CodingChallengeRepository codingChallengeRepository;

    public CodingChallengeService(CodingChallengeRepository codingChallengeRepository) {
        this.codingChallengeRepository = codingChallengeRepository;
    }

    public int createAtCodingChallenge(int editorId, CodingChallenge codingChallenge){
        return codingChallengeRepository.createCodingChallenge(editorId,codingChallenge);
    }

    public int createAndAddQuestionToInterview(int interviewId,int company_id, int time_limit, CodingChallenge codingChallenge){
        return codingChallengeRepository.createAndAddQuestionToInterview( interviewId, company_id,  time_limit,  codingChallenge);
    }

    public MessageResponse updateDifficultyCodingChallenge(int challenge_id, String difficulty)
    {
        return codingChallengeRepository.updateDifficultyCodingChallenge(challenge_id,difficulty);
    }

    public MessageResponse addCategoryCodingChallenge( int challenge_id, String category)
    {
        return codingChallengeRepository.addCategoryCodingChallenge(challenge_id,category);
    }

    public MessageResponse removeCategoryCodingChallenge( int challenge_id, String category)
    {
        return codingChallengeRepository.removeCategoryFromChallenge(challenge_id,category);
    }

    public MessageResponse addTestCaseForCodingChallenge( int challenge_id, String input, String output)
    {
        return codingChallengeRepository.addTestCaseForCodingChallenge(challenge_id,input,output);
    }


    public  List<String> getInputsForCodingChallenge( int challenge_id)
    {
       return codingChallengeRepository.getInputsForCodingChallenge(challenge_id);
    }

    public List<String>  getOutputsForCodingChallenge( int challenge_id)
    {
        return codingChallengeRepository.getOutputsForCodingChallenge(challenge_id);
    }

    public List<CodingChallengeQueryResponse> getAllPublicChallenges(){
        return codingChallengeRepository.getAllPublicChallenges();
    }


    public List<CodingChallengeQueryResponse> getChallengesOfEditor( int editorId){
        return codingChallengeRepository.getChallengesOfEditor(editorId);
    }


    public CodingChallenge getCodingChallenge(int challengeId){
        return codingChallengeRepository.getCodingChallenge(challengeId);
    }


    public List<CodingChallengeQueryResponse> getAllPublicCodingChallengesFiltered(List<String> filters){
        return codingChallengeRepository.getAllPublicCodingChallengesWithFiltered(filters);
    }


    public CodingChallengeAuthorCategoryResponse getAuthorandCategoryofCodingChallenge(int challengeId){
        return codingChallengeRepository.getCodingChallengeAuthorResponse(challengeId);
    }

    public MessageResponse makeCompanySponsorToContest( int contestId, int companyId)
    {
        return codingChallengeRepository.makeCompanySponsorToContest(contestId,companyId);
    }

}
