package cs353.group14;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
public abstract class User {
    private int userId;
    private String username;
    private String mail;
    private String password;
    private UserType userType;
    private String name;
    private String information;
    private String foto; // düzeltilecek

    public User(int userId, String username, String mail, String password, UserType userType, String name, String information, String foto) {
        this.userId = userId;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.userType = userType;
        this.name = name;
        this.information = information;
        this.foto = foto;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
