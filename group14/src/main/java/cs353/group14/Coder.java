package cs353.group14;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coder extends User{
    private int rating;
    private int points;
    private String position;
    private String place;
    private int birthYear;

    public Coder(int userId, String username, String mail, String password, UserType userType, String name, String information, String foto, int rating, int points, String position, String place, int birthYear) {
        super(userId, username, mail, password, userType, name, information, foto);
        this.rating = rating;
        this.points = points;
        this.position = position;
        this.place = place;
        this.birthYear = birthYear;
    }

    @Override
    public String toString() {
        return "Coder{" +
                "rating=" + rating +
                ", points=" + points +
                ", position='" + position + '\'' +
                ", place='" + place + '\'' +
                ", birthYear=" + birthYear +
                ", userId=" + getUserId() +
                ", username='" + getUsername() + '\'' +
                ", mail='" + getMail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", userType=" + getUserType() +
                ", name='" + getName() + '\'' +
                ", information='" + getInformation() + '\'' +
                ", foto='" + getFoto() + '\'' +
                '}';
    }
}
