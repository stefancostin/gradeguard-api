package io.github.stefancostin.gradeguard.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.stefancostin.gradeguard.models.SubjectDTO;
import io.github.stefancostin.gradeguard.utils.Semester;
import io.github.stefancostin.gradeguard.utils.YearOfStudy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "acronym", nullable = false)
    private String acronym;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "year_of_study", nullable = false)
    private YearOfStudy yearOfStudy;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "semester", nullable = false)
    private Semester semester;

    @JsonManagedReference
    @ManyToMany(mappedBy = "subjectsTaught", fetch = FetchType.LAZY)
    private Set<User> professors = new HashSet<User>();

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Grade> grades = new HashSet<>();

    public Subject(String name, String acronym, YearOfStudy yearOfStudy, Semester semester) {
        this.name = name;
        this.acronym = acronym;
        this.yearOfStudy = yearOfStudy;
        this.semester = semester;
    }

    public Subject(SubjectDTO subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.acronym = subject.getAcronym();
        this.yearOfStudy = subject.getYearOfStudy();
        this.semester = subject.getSemester();
    }

    public Subject() { }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public YearOfStudy getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(YearOfStudy yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Set<User> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<User> professors) {
        this.professors = professors;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    public void addProfessor(User professor) {
//        this.professors.add(professor);
//        professor.getSubjectsTaught().add(this);
    }

    public void removeProfessor(User professor) {
//        this.professors.remove(professor);
//        professor.getSubjectsTaught().remove(this);
    }

}
