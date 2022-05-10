package cs353.group14.responses;

import cs353.group14.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCoderResponse {
    private int rating;
    private int points;
    private String position;
    private String place;
    private int birthYear;
    private int userId;
    private String username;
    private String mail;
    private String name;
    private String information;
    private String foto;

    public UserCoderResponse(int rating, int points, String position, String place, int birthYear, int userId, String username, String mail, String name, String information, String foto) {
        this.rating = rating;
        this.points = points;
        this.position = position;
        this.place = place;
        this.birthYear = birthYear;
        this.userId = userId;
        this.username = username;
        this.mail = mail;

        this.name = name;
        this.information = information;
        this.foto = foto;
    }
}
