package cs353.group14.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCompanyResponse {
    private int userId;
    private String username;
    private String mail;
    private String name;
    private String information;
    private String foto;
    private String location;
    private String webpagelink;

    public UserCompanyResponse(int userId, String username, String mail, String name, String information, String foto, String location, String webpagelink) {
        this.userId = userId;
        this.username = username;
        this.mail = mail;
        this.name = name;
        this.information = information;
        this.foto = foto;
        this.location = location;
        this.webpagelink = webpagelink;
    }
}
