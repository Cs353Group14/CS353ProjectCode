package cs353.group14;

public class Admin extends User{

    public Admin(int userId, String username, String mail, String userType, String name, String information, String foto) {
        super(userId, username, mail, userType, name, information, foto);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", userType='" + userType + '\'' +
                ", name='" + name + '\'' +
                ", information='" + information + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}
