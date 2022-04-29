package cs353.group14;

public class Coder extends User{
    int rating;
    int points;
    String position;
    String place;
    int birthYear;

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
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", userType='" + userType + '\'' +
                ", name='" + name + '\'' +
                ", information='" + information + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}
