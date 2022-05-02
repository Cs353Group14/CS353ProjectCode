package cs353.group14.responses;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
public class InterviewResponse {

    public InterviewResponse(String companyName, int duration, String position, int interviewId, Timestamp startTime, Timestamp endTime, String interviewResult, String invitationCode) {
        this.companyName = companyName;
        this.duration = duration;
        this.position = position;
        this.interviewId = interviewId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.interviewResult = interviewResult;
        this.invitationCode = invitationCode;
    }

    private String companyName;
    private int duration;
    private String position;
    private int interviewId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String interviewResult;
    private String invitationCode;
}
