package cs353.group14;

public abstract class User {
    int userId;
    String username;
    String mail;
    String userType;
    String name;
    String information;
    String foto; // düzeltilecek

    public User(int userId, String username, String mail, String userType, String name, String information, String foto) {
        this.userId = userId;
        this.username = username;
        this.mail = mail;
        this.userType = userType;
        this.name = name;
        this.information = information;
        this.foto = foto;
    }
}
