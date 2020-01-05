package io.github.stefancostin.gradeguard.models;

import io.github.stefancostin.gradeguard.utils.Role;

import java.util.List;

public class ProfessorSubjectsDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private List<Integer> subjectsIdList;

    public ProfessorSubjectsDTO(int id, String firstName, String lastName, String email, String password, Role role, List<Integer> subjectsIdList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.subjectsIdList = subjectsIdList;
    }

    public ProfessorSubjectsDTO() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Integer> getSubjectsIdList() {
        return subjectsIdList;
    }

    public void setSubjectsIdList(List<Integer> subjectsIdList) {
        this.subjectsIdList = subjectsIdList;
    }
}
