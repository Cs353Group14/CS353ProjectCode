package cs353.group14.responses;

public class NonCodingChallengeQueryResponse {
    private String title;
    private String difficulty;
    private int non_challenge_id;

    public NonCodingChallengeQueryResponse(String title, String difficulty, int non_challenge_id) {
        this.title = title;
        this.difficulty = difficulty;
        this.non_challenge_id = non_challenge_id;
    }

    public int getNon_challenge_id() {
        return non_challenge_id;
    }

    public void setNon_challenge_id(int non_challenge_id) {
        this.non_challenge_id = non_challenge_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
