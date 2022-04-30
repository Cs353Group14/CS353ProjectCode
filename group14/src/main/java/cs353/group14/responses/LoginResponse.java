package cs353.group14.responses;


public class LoginResponse {
    // username userid user type
    private String username;
    private int userId;
    private int usertype;

    public LoginResponse(String username, int userId, int usertype) {
        this.username = username;
        this.userId = userId;
        this.usertype = usertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
}
