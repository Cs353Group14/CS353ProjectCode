package cs353.group14;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodingChallenge {

    private int challenge_id;
    private String question;
    private int points;
    private String difficulty;
    private int solved_number;
    private int attempt_number;
    private String title;
    private String solution;
    private int publicity;
    // category ???

    public CodingChallenge(int challenge_id, String question, int points, String difficulty, int solved_number, int attempt_number, String title, String solution, int publicity) {
        this.challenge_id = challenge_id;
        this.question = question;
        this.points = points;
        this.difficulty = difficulty;
        this.solved_number = solved_number;
        this.attempt_number = attempt_number;
        this.title = title;
        this.solution = solution;
        this.publicity = publicity;
        // category ???
    }
}
