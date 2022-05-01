package cs353.group14;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Notification {
    int nId;
    String nInfo;
    Timestamp notifDate;
    String type;

    public Notification(int nId, String nInfo, Timestamp notifDate, String type) {
        this.nId = nId;
        this.nInfo = nInfo;
        this.notifDate = notifDate;
        this.type = type;
    }
}
