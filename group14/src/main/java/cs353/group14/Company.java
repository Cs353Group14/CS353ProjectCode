package cs353.group14;

import lombok.Getter;

@Getter
public class Company extends User{
    public String location;
    public String webPageLink;

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
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", name='" + name + '\'' +
                ", information='" + information + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}
