package io.github.stefancostin.gradeguard.models;

import io.github.stefancostin.gradeguard.entities.Subject;
import io.github.stefancostin.gradeguard.utils.Semester;
import io.github.stefancostin.gradeguard.utils.YearOfStudy;

public class SubjectGradesDTO extends GradeCollectionDTO {
    private int id;
    private String name;
    private String acronym;
    private YearOfStudy yearOfStudy;
    private Semester semester;

    public SubjectGradesDTO(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.acronym = subject.getAcronym();
        this.yearOfStudy = subject.getYearOfStudy();
        this.semester = subject.getSemester();
        this.setGrades(subject.getGrades());
    }

    public SubjectGradesDTO() { }

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
