package cs353.group14.services;

import cs353.group14.repositories.NonCodingChallengeRepository;
import cs353.group14.responses.NonCodingChallengeQueryResponse;

import java.util.List;

public class NonCodingChallengeService {

    private final NonCodingChallengeRepository noncodingChallengeRepository;

    public NonCodingChallengeService(NonCodingChallengeRepository noncodingChallengeRepository) {
        this.noncodingChallengeRepository = noncodingChallengeRepository;
    }

    public List<NonCodingChallengeQueryResponse> getAllPublicCodingChallengesFiltered(List<String> filters){
        return noncodingChallengeRepository.getAllPublicNonCodingChallengesWithFiltered(filters);
    }
}
