package cs353.group14;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReferCoder {
    private int referredId;
    private String username;
    private String position;
    private String referReason;

    public ReferCoder(int referredId, String username, String position, String referReason) {
        this.referredId = referredId;
        this.username = username;
        this.position = position;
        this.referReason = referReason;
    }
}
