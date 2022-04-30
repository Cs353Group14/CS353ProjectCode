package cs353.group14.services;

import cs353.group14.CodingChallenge;
import cs353.group14.repositories.CodingChallengeRepository;
import cs353.group14.repositories.UserRepository;
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

    public List<CodingChallengeQueryResponse> getAllPublicChallenges(){
        return codingChallengeRepository.getAllPublicChallenges();
    }

    public CodingChallenge getCodingChallenge(int editorId){
        return codingChallengeRepository.getCodingChallenge(editorId);
    }


    public List<CodingChallengeQueryResponse> getAllPublicCodingChallengesFiltered(List<String> filters){
        return codingChallengeRepository.getAllPublicCodingChallengesWithFiltered(filters);
    }
}
