package cs353.group14.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNameAndPointResponse {
    int  point;
    String userName;

    public UserNameAndPointResponse(int point, String userName) {
        this.point = point;
        this.userName = userName;
    }
}
