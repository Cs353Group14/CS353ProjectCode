package cs353.group14;

public class Company extends User{
    String location;
    String webPageLink;

    public Company(int userId, String username, String mail, String userType, String name, String information, String foto, String location, String webPageLink) {
        super(userId, username, mail, userType, name, information, foto);
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
                ", userType='" + userType + '\'' +
                ", name='" + name + '\'' +
                ", information='" + information + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}
