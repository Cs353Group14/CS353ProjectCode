package cs353.group14.services;

import cs353.group14.CodingChallenge;
import cs353.group14.repositories.CodingChallengeRepository;
import cs353.group14.repositories.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class CodingChallengeService {

    private final CodingChallengeRepository codingChallengeRepository;

    public CodingChallengeService(CodingChallengeRepository codingChallengeRepository) {
        this.codingChallengeRepository = codingChallengeRepository;
    }

    public void createAtCodingChallenge(int editorId, CodingChallenge codingChallenge){
        codingChallengeRepository.createCodingChallenge(editorId,codingChallenge);
    }
}
