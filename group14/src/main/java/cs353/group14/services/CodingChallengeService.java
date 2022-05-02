package cs353.group14.services;

import cs353.group14.CodingChallenge;
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

    public void updateDifficultyCodingChallenge( int challenge_id, String difficulty)
    {
        codingChallengeRepository.updateDifficultyCodingChallenge(challenge_id,difficulty);
    }

    public void addCategoryCodingChallenge( int challenge_id, String category)
    {
        codingChallengeRepository.addCategoryCodingChallenge(challenge_id,category);
    }

    public void removeCategoryCodingChallenge( int challenge_id, String category)
    {
        codingChallengeRepository.removeCategoryFromChallenge(challenge_id,category);
    }

    public void addTestCaseForCodingChallenge( int challenge_id, String input, String output)
    {
        codingChallengeRepository.addTestCaseForCodingChallenge(challenge_id,input,output);
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

    public CodingChallenge getCodingChallenge(int challengeId){
        return codingChallengeRepository.getCodingChallenge(challengeId);
    }


    public List<CodingChallengeQueryResponse> getAllPublicCodingChallengesFiltered(List<String> filters){
        return codingChallengeRepository.getAllPublicCodingChallengesWithFiltered(filters);
    }


    public CodingChallengeAuthorCategoryResponse getAuthorandCategoryofCodingChallenge(int challengeId){
        return codingChallengeRepository.getCodingChallengeAuthorResponse(challengeId);
    }

    public void makeCompanySponsorToContest( int contestId, int companyId)
    {
         codingChallengeRepository.makeCompanySponsorToContest(contestId,companyId);
    }

}
