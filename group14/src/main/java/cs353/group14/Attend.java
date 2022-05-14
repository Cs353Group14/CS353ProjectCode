package cs353.group14;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Attend {

    private int interviewId;
    private int coderId;
    private int companyId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String interviewResult;
    private String invitationCode;

    public Attend(int interviewId, int coderId, int companyId, Timestamp startTime, Timestamp endTime, String interviewResult, String invitationCode) {
        this.interviewId = interviewId;
        this.coderId = coderId;
        this.companyId = companyId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.interviewResult = interviewResult;
        this.invitationCode = invitationCode;
    }
}


