package io.github.stefancostin.gradeguard.entities;

import io.github.stefancostin.gradeguard.utils.Semester;
import io.github.stefancostin.gradeguard.utils.YearOfStudy;

import javax.persistence.*;

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

    public Subject(String name, String acronym, YearOfStudy yearOfStudy, Semester semester) {
        this.name = name;
        this.acronym = acronym;
        this.yearOfStudy = yearOfStudy;
        this.semester = semester;
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
}
