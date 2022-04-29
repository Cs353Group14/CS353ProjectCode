package cs353.group14;

public class Editor extends User{

    String position;
    String place;

    public Editor(int userId, String username, String mail, String password, UserType userType, String name, String information, String foto, String position, String place) {
        super(userId, username, mail, password, userType, name, information, foto);
        this.position = position;
        this.place = place;
    }

    @Override
    public String toString() {
        return "Editor{" +
                "position='" + position + '\'' +
                ", place='" + place + '\'' +
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
