package cs353.group14.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdUserNameandNameResponse {
    private int id;
    private String userName;
    private String name;

    public IdUserNameandNameResponse(int id, String userName, String name) {
        this.id = id;
        this.userName = userName;
        this.name = name;
    }
}
