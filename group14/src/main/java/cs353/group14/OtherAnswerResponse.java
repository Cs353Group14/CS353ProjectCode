package cs353.group14;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtherAnswerResponse {
    private String username;
    private String answer;

    public OtherAnswerResponse(String username, String answer) {
        this.username = username;
        this.answer = answer;
    }
}
