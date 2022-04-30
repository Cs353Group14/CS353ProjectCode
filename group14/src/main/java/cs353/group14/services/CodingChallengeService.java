package cs353.group14.services;

import cs353.group14.CodingChallenge;
import cs353.group14.repositories.CodingChallengeRepository;
import cs353.group14.repositories.UserRepository;
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

    public void createAtCodingChallenge(int editorId, CodingChallenge codingChallenge){
        codingChallengeRepository.createCodingChallenge(editorId,codingChallenge);
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



    public List<CodingChallengeQueryResponse> getAllPublicChallenges(){
        return codingChallengeRepository.getAllPublicChallenges();
    }

    public CodingChallenge getCodingChallenge(int editorId){
        return codingChallengeRepository.getCodingChallenge(editorId);
    }


    public List<CodingChallengeQueryResponse> getAllPublicCodingChallengesFiltered(List<String> filters){
        return codingChallengeRepository.getAllPublicCodingChallengesWithFiltered(filters);
    }


    public CodingChallengeAuthorCategoryResponse getAuthorandCategoryofCodingChallenge(int challengeId){
        return codingChallengeRepository.getCodingChallengeAuthorResponse(challengeId);
    }



}
