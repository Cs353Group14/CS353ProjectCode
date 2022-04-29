package cs353.group14;


import lombok.experimental.Accessors;

@Accessors(fluent = true)
public abstract class User {
    public int userId;
    public String username;
    public String mail;
    public String password;
    public UserType userType;
    public String name;
    public String information;
    public String foto; // düzeltilecek

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
