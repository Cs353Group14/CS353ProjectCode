package cs353.group14.services;

import cs353.group14.NonCodingChallenge;
import cs353.group14.Reply;
import cs353.group14.repositories.NonCodingChallengeRepository;
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


    public void createNonCodingChallenge(int editorId, NonCodingChallenge nonCodingChallenge){
        noncodingChallengeRepository.createNonCodingChallenge(editorId,nonCodingChallenge);
    }

    public List<NonCodingChallengeQueryResponse> getAllPublicNonCodingChallengesFiltered(List<String> filters){
        return noncodingChallengeRepository.getAllPublicNonCodingChallengesWithFiltered(filters);
    }

    public List<NonCodingChallengeQueryResponse> getAllNonCodingChallenges(){
        return noncodingChallengeRepository.getAllNonCodingChallenges();
    }





    public NonCodingChallenge getNonCodingChallenge(int noncodingChallengeId){
        return noncodingChallengeRepository.getNonCodingChallenge(noncodingChallengeId);
    }

    public void replyQuestion(Reply reply){
        reply.setReplyTime(new Timestamp(System.currentTimeMillis()));
        noncodingChallengeRepository.replyQuestion(reply);
    }
}
