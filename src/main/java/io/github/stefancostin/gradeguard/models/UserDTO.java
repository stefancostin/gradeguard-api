package io.github.stefancostin.gradeguard.models;

import io.github.stefancostin.gradeguard.entities.Subject;
import io.github.stefancostin.gradeguard.entities.User;
import io.github.stefancostin.gradeguard.utils.Role;
import io.github.stefancostin.gradeguard.utils.YearOfStudy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private YearOfStudy yearOfStudy;
    private List<SubjectDTO> subjectsTaught;

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.yearOfStudy = user.getYearOfStudy();
        this.subjectsTaught = convertFromModelToDTO(user.getSubjectsTaught());
    }

    public UserDTO(int id, String firstName, String lastName, String email, Role role, YearOfStudy yearOfStudy) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.yearOfStudy = yearOfStudy;
    }

    public UserDTO() { }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

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

    public String getPassword() { return this.password; }

    public void setPassword(String password) { this.password = password; }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public YearOfStudy getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(YearOfStudy yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    private List<SubjectDTO> convertFromModelToDTO(Set<Subject> subjectsModelSet) {
        List<SubjectDTO> subjectsList = new ArrayList<>();
        for (Subject subjectModel : subjectsModelSet) {
            SubjectDTO subject = new SubjectDTO();
            subject.setId(subjectModel.getId());
            subject.setName(subjectModel.getName());
            subject.setAcronym(subjectModel.getAcronym());
            subject.setYearOfStudy(subjectModel.getYearOfStudy());
            subject.setSemester(subjectModel.getSemester());
            subjectsList.add(subject);
        }
        return subjectsList;
    }

}
