package cs353.group14;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company extends User{
    private String location;
    private String webPageLink;

    public Company(int userId, String username, String mail, String password, UserType userType, String name, String information, String foto, String location, String webPageLink) {
        super(userId, username, mail, password, userType, name, information, foto);
        this.location = location;
        this.webPageLink = webPageLink;
    }

    @Override
    public String toString() {
        return "Company{" +
                "location='" + location + '\'' +
                ", webPageLink='" + webPageLink + '\'' +
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
