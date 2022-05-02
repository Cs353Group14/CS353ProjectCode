package cs353.group14.responses;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UserNameAndInterviewResultResponse {
    private String username;
    private int interviewId;
    private int duration;
    private String position;
    private Timestamp startTime;
    private Timestamp endTime;
    private String interviewResult;
    private String invitationCode;

    public UserNameAndInterviewResultResponse(String username, int interviewId, int duration, String position, Timestamp startTime, Timestamp endTime, String interviewResult, String invitationCode) {
        this.username = username;
        this.interviewId = interviewId;
        this.duration = duration;
        this.position = position;
        this.startTime = startTime;
        this.endTime = endTime;
        this.interviewResult = interviewResult;
        this.invitationCode = invitationCode;
    }
}
