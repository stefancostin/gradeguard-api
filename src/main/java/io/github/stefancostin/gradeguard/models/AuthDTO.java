package io.github.stefancostin.gradeguard.models;

import io.github.stefancostin.gradeguard.utils.Role;

public class AuthDTO {
    private int userId;
    private String userName;
    private Role userRole;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }
}
