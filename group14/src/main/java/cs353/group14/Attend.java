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


}


