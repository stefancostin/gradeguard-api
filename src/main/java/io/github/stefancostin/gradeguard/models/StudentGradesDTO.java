package io.github.stefancostin.gradeguard.models;

import io.github.stefancostin.gradeguard.entities.User;
import io.github.stefancostin.gradeguard.utils.Role;

public class StudentGradesDTO extends GradeCollectionDTO {
    private int id;
    private String firstName;
    private String lastName;
    private Role role;

    public StudentGradesDTO(User student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.role = student.getRole();
        this.setGrades(student.getStudentGrades());
    }

    public StudentGradesDTO() { }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
