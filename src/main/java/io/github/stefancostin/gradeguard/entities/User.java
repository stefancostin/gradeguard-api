package io.github.stefancostin.gradeguard.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.stefancostin.gradeguard.models.UserDTO;
import io.github.stefancostin.gradeguard.utils.Role;
import io.github.stefancostin.gradeguard.utils.YearOfStudy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role", nullable = false)
    private Role role;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "year_of_study", nullable = false)
    private YearOfStudy yearOfStudy;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "professors_subjects",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id"))
    private Set<Subject> subjectsTaught = new HashSet<Subject>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Grade> studentGrades = new HashSet<>();

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private Set<Grade> professorGrades = new HashSet<>();

    public User(int id, String firstName, String lastName, String email, String password, Role role, YearOfStudy yearOfStudy) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.yearOfStudy = yearOfStudy;
    }

    public User(UserDTO user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.yearOfStudy = user.getYearOfStudy();
    }

    public User() { }

    public int getId() {
        return id;
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

    public YearOfStudy getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(YearOfStudy yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public Set<Subject> getSubjectsTaught() {
        return subjectsTaught;
    }

    public void addSubjectTaught(Subject subject) {
//        this.subjectsTaught.add(subject);
//        subject.getProfessors().add(this);
    }

    public void removeSubjectTaught(Subject subject) {
//        this.subjectsTaught.remove(subject);
//        subject.getProfessors().remove(this);
    }

}
