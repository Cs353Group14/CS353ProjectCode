package cs353.group14.requests;

import javax.validation.constraints.NotEmpty;

public class LoginRequest {
    @NotEmpty
    private final String username;
    @NotEmpty
    private final String password;

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public LoginRequest(final String username, final String password) {
        this.username = username;
        this.password = password;
    }
}