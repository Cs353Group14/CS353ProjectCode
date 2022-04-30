package cs353.group14;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Reply {
    int nonChallengeId;
    int userId;
    String answer;
    String theResult;
    Timestamp replyTime;

    public Reply(int nonChallengeId, int userId, String answer, String theResult, Timestamp replyTime) {
        this.nonChallengeId = nonChallengeId;
        this.userId = userId;
        this.answer = answer;
        this.theResult = theResult;
        this.replyTime = replyTime;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "nonChallengeId=" + nonChallengeId +
                ", userId=" + userId +
                ", answer='" + answer + '\'' +
                ", theResult='" + theResult + '\'' +
                ", replyTime=" + replyTime +
                '}';
    }
}
