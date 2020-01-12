package io.github.stefancostin.gradeguard.models;

import io.github.stefancostin.gradeguard.utils.Role;

public class AuthDTO {
    private int id;
    private String name;
    private Role role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
