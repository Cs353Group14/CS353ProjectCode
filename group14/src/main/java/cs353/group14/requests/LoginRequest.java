package cs353.group14.requests;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class LoginRequest {
    @NotEmpty
    private final String username;
    @NotEmpty
    private final String password;

    public LoginRequest(final String username, final String password) {
        this.username = username;
        this.password = password;
    }
}