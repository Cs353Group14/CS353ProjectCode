package cs353.group14;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Interview {

    private int user_id;
    private int interview_id;
    private int duration;
    private String position;

    public Interview(int user_id, int interview_id, int duration, String position) {
        this.user_id = user_id;
        this.interview_id = interview_id;
        this.duration = duration;
        this.position = position;
    }
}
