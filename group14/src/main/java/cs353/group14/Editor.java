package cs353.group14;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Editor extends User{

    private String position;
    private String place;

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
