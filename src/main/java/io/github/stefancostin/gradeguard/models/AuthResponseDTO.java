package io.github.stefancostin.gradeguard.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponseDTO {
    private boolean isAuthenticated;
    private AuthDTO authenticatedUser;

    @JsonProperty(value = "isAuthenticated")
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public AuthDTO getAuthenticatedUser() {
        return authenticatedUser;
    }

    public void setAuthenticatedUser(AuthDTO authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }
}
