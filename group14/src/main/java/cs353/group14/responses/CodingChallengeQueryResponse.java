package cs353.group14.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodingChallengeQueryResponse {
    private int challenge_id;
    private String title;
    private String difficulty;
    private int points;
    private int solved_number;
    private int attempt_number;


    public CodingChallengeQueryResponse( int challenge_id, String title, String difficulty, int points, int solved_number, int attempt_number) {
        this.challenge_id = challenge_id;
        this.title = title;
        this.difficulty = difficulty;
        this.points = points;
        this.solved_number = solved_number;
        this.attempt_number = attempt_number;
    }
}
