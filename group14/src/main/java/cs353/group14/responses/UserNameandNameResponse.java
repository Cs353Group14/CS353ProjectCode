package cs353.group14.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNameandNameResponse {
    private String userName;
    private String name;

    public UserNameandNameResponse(String userName, String name) {
        this.userName = userName;
        this.name = name;
    }
}
