package cs353.group14.services;

import cs353.group14.CodingChallenge;
import cs353.group14.NonCodingChallenge;
import cs353.group14.repositories.NonCodingChallengeRepository;
import cs353.group14.responses.NonCodingChallengeQueryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NonCodingChallengeService {

    private final NonCodingChallengeRepository noncodingChallengeRepository;

    public NonCodingChallengeService(NonCodingChallengeRepository noncodingChallengeRepository) {
        this.noncodingChallengeRepository = noncodingChallengeRepository;
    }


    public void createNonCodingChallenge(int editorId, NonCodingChallenge nonCodingChallenge){
        noncodingChallengeRepository.createNonCodingChallenge(editorId,nonCodingChallenge);
    }

    public List<NonCodingChallengeQueryResponse> getAllPublicNonCodingChallengesFiltered(List<String> filters){
        return noncodingChallengeRepository.getAllPublicNonCodingChallengesWithFiltered(filters);
    }

    public List<NonCodingChallengeQueryResponse> getAllNonCodingChallenges(){
        return noncodingChallengeRepository.getAllNonCodingChallenges();
    }




}
