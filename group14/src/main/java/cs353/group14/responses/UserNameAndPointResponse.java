package cs353.group14.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNameAndPointResponse {
    int id;
    int  point;
    String userName;

    public UserNameAndPointResponse(int id, int point, String userName) {
        this.id = id;
        this.point = point;
        this.userName = userName;
    }

}
