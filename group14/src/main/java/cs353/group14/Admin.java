package cs353.group14;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Admin extends User{

    public Admin(int userId, String username, String mail, String password, UserType userType, String name, String information, String foto) {
        super(userId, username, mail, password, userType, name, information, foto);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "userId=" + getUserId() +
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
