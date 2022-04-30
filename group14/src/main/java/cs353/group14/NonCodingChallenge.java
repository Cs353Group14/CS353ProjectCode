package cs353.group14;

import lombok.Getter;
import lombok.Setter;

@Getter

public class NonCodingChallenge {
    private int non_challenge_id;
    private String question;

    private String difficulty;

    private String title;

    private int publicity;


    public NonCodingChallenge(int non_challenge_id, String question, String difficulty, String title, int publicity) {
        this.non_challenge_id = non_challenge_id;
        this.question = question;
        this.difficulty = difficulty;
        this.title = title;
        this.publicity = publicity;
    }

    @Override
    public String toString() {
        return "NonCodingChallenge{" +
                "challenge_id=" + non_challenge_id +
                ", question='" + question + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", title='" + title + '\'' +
                ", publicity=" + publicity +
                '}';
    }
}
